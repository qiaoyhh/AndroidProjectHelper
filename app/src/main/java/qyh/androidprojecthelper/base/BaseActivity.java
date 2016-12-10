package qyh.androidprojecthelper.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.ButterKnife;


/**
 *  类描述：
 *            1.所有的Activity都需要继承BaseActivity获取Presenter实例对象
 *            2.BaseActivity类对Activity一些基本的生命周期进行控制 --->  范围包含网络,事件(绑定)订阅,取消,DB,以及涉及到的广播等
 *            3.此类可以进行接入友盟页面统计,以及BUG统计等!
 *            4.根据项目需求 待扩展。。。
 *      AutoLayoutActivity  屏幕适配
 *  Created by qyh on 2016/12/6.
 **/
public abstract class BaseActivity<M extends  BaseModel , P extends BasePresenter> extends AutoLayoutActivity {

//    定义Presenter
    protected  P mPresenter;

//    获取布局资源文件
    protected  abstract  int getLayoutId();

//    初始化数据

    protected  abstract void onInitView(Bundle bundle);

//    初始化事件Event

    protected  abstract  void onEvent();

    //   获取抽取View对象
    protected   abstract BaseView getView();
    //    获得抽取接口Model对象
    protected   Class getModelClazz()  {
        return (Class<M>)ContractProxy.getModelClazz(getClass(), 0);
    }
    //    获得抽取接口Presenter对象
    protected    Class getPresenterClazz()  {
        return (Class<P>)ContractProxy.getPresnterClazz(getClass(), 1);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getLayoutId()!=0)
        {
//            设置布局资源文件
             setContentView(getLayoutId());
            bindMVP();
            //        注解绑定
            ButterKnife.inject(this);
            onInitView(savedInstanceState);
            onEvent();
        }
    }

    /**
     *  获取presenter 实例
     */
    private  void bindMVP()
    {
          if(getPresenterClazz()!=null)
          {
               mPresenter=getPresenterImpl();
               mPresenter.mContext=this;
               bindVM();
          }
    }
    private <T> T getPresenterImpl()
    {
        return ContractProxy.getInstance().presenter(getPresenterClazz());
    }
    @Override
    protected void onStart() {
        if(mPresenter==null)
        {
            bindMVP();
        }
        super.onStart();
    }
    private  void bindVM()
    {
        if(mPresenter!=null&&!mPresenter.isViewBind()&&getModelClazz()!=null&&getView()!=null)
        {
            ContractProxy.getInstance().bindModel(getModelClazz(),mPresenter);
            ContractProxy.getInstance().bindView(getView(),mPresenter);
            mPresenter.mContext=this;
        }
    }

    /**
     *  activity摧毁
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
       ButterKnife.reset(this);
        if(mPresenter!=null)
        {
            ContractProxy.getInstance().unbindView(getView(),mPresenter);
            ContractProxy.getInstance().unbindModel(getModelClazz(),mPresenter);
        }
    }
}
