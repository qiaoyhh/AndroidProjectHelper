package qyh.androidprojecthelper.base;
/**
 * 描述：
 * Created by qyh on 2016/12/28.
 */
public interface BaseView {
    /*******内嵌加载*******/
    void showLoading(String title);
    void stopLoading();
    void showErrorTip(String msg);
}
