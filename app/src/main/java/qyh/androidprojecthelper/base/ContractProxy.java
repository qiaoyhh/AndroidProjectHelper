package qyh.androidprojecthelper.base;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;


public class ContractProxy {
    private static final ContractProxy m_instance = new ContractProxy();

    public static ContractProxy getInstance() {
        return m_instance;
    }

    private ContractProxy() {
        m_objects = new HashMap<>();
    }

    private Map<Class, Object> m_objects;

//    /**
//     * 进行初始化
//     *
//     * @param clss
//     */
//    public void init(Class... clss) {
//        for (Class cls : clss) {
//            if (cls.isAnnotationPresent(Implement.class)) {
////                list.add(cls);
//                for (Annotation ann : cls.getDeclaredAnnotations()) {
//                    if (ann instanceof Implement) {
//                        try {
//                            m_objects.put(cls, ((Implement) ann).value().newInstance());
//                        } catch (InstantiationException e) {
//                            e.printStackTrace();
//                        } catch (IllegalAccessException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        }
//    }

    /**
     *   Presenter
     * 通过反射, 获得定义Class时声明的父类的泛型参数的类型.
     *
     *@param clazz
     *            clazz The class to introspect
     * @param index
     *            the Index of the generic ddeclaration,start from 0.
     * @return the index generic declaration, or Object.class if cannot be
     *         determined
     */
    @SuppressWarnings("unchecked")
        public static Class<BasePresenter> getPresnterClazz(final Class clazz, final int index) {

        //返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type。
        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType)) {
            return BasePresenter.class;
        }
        //返回表示此类型实际类型参数的 Type 对象的数组。
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0) {
            return BasePresenter.class;
        }
        if (!(params[index] instanceof Class)) {
            return BasePresenter.class;
        }
        return (Class) params[index];
    }
    /**
     *  Model
     * 通过反射, 获得定义Class时声明的父类的泛型参数的类型.
     *
     *@param clazz
     *            clazz The class to introspect
     * @param index
     *            the Index of the generic ddeclaration,start from 0.
     * @return the index generic declaration, or Object.class if cannot be
     *         determined
     */
    @SuppressWarnings("unchecked")
    public static Class<BaseModel> getModelClazz(final Class clazz, final int index) {

        //返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type。
        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType)) {
            return BaseModel.class;
        }
        //返回表示此类型实际类型参数的 Type 对象的数组。
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0) {
            return BaseModel.class;
        }
        if (!(params[index] instanceof Class)) {
            return BaseModel.class;
        }
        return (Class) params[index];
    }
    /**
     *  View
     * 通过反射, 获得定义Class时声明的父类的泛型参数的类型.
     *
     *@param clazz
     *            clazz The class to introspect
     * @param index
     *            the Index of the generic ddeclaration,start from 0.
     * @return the index generic declaration, or Object.class if cannot be
     *         determined
     */
    @SuppressWarnings("unchecked")
    public  static Class<BaseView> getViewClazz(final Class clazz, final int index) {

        //返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type。
        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType)) {
            return BaseView.class;
        }
        //返回表示此类型实际类型参数的 Type 对象的数组。
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0) {
            return BaseView.class;
        }
        if (!(params[index] instanceof Class)) {
            return BaseView.class;
        }
        return (Class) params[index];
    }

//    /**
//     * 获取presenter
//     *
//     * @param clzz
//     * @param <T>
//     * @return
//     */
//    public <T> T getPresenter(Class clzz) {
//        if (!m_objects.containsKey(clzz)) {
//            init(clzz);
//        }
//        BasePresenter presenter = ((BasePresenter) m_objects.get(clzz));
//        return (T) presenter;
//    }


    /**
     * 获取presenter
     *
     * @param clzz
     * @param <T>
     * @return
     */
    public <T> T presenter(Class clzz) {
        if (!m_objects.containsKey(clzz)) {
            initInstance(clzz);
        }
        BasePresenter presenter = ((BasePresenter) m_objects.get(clzz));
        return (T) presenter;
    }
    /**
     * 进行初始化
     *
     * @param clss
     */
    public void initInstance(Class clss) {
                        try {
                            m_objects.put(clss,clss.newInstance());
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
    }


    // 初始化model add map
    public <M> M bindModel(Class clzz,BasePresenter presenter) {
        if (!m_objects.containsKey(clzz)) {
            initInstance(clzz);
        }
        BaseModel model = ((BaseModel) m_objects.get(clzz));
        if (model != presenter.getModel()) {
            if (presenter.getModel() != null) {
                presenter.detachModel();
            }
            presenter.attachModel(model);
        }
        return (M) model;
    }

    /**
     *  绑定 View
     * @param presenter
     * @param <V>
     * @return
     */
    public <V> V bindView(BaseView view,BasePresenter presenter) {

        if (view != presenter.getView()) {
            if (presenter.getView() != null) {
                presenter.detachView();
            }
            presenter.attachView(view);
        }
        return (V) view;
    }

    // 解除绑定 移除map
    public void unbindView(BaseView  view, BasePresenter presenter) {

            if (view != presenter.getView()) {
                if (presenter.getView() != null)
                    presenter.detachView();
            }
    }

    // 解除绑定 移除map
    public void unbindModel(Class clzz, BasePresenter presenter) {
        if (m_objects.containsKey(clzz)) {
            BaseModel model = ((BaseModel) m_objects.get(clzz));
            if (model != presenter.getModel()) {
                if (presenter.getModel() != null)
                    presenter.detachModel();
                m_objects.remove(clzz);
            }
        }
    }

}