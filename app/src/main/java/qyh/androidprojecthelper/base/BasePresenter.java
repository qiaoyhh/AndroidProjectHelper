package qyh.androidprojecthelper.base;

import android.content.Context;

import qyh.androidprojecthelper.baserx.RxManager;


/**
 * 描述:基类presenter
 * Created by qyh on 2016/12/6.
 *
 */
public abstract class BasePresenter<T,E>{
    public Context mContext;
    public E mModel;
    public T mView;
    public RxManager mRxManage = new RxManager();

    public void setVM(T v, E m) {
        this.mView = v;
        this.mModel = m;
        this.onStart();
    }
    public void onStart(){
    };
    public void onDestroy() {
        mRxManage.clear();
    }
}
