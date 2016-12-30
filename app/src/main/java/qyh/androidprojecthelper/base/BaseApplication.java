package qyh.androidprojecthelper.base;


import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.zhy.autolayout.config.AutoLayoutConifg;

/**
 * 描述：MultiDexApplication防止方法数过多
 * Created by qyh on 2016/12/6.
 */
public class BaseApplication extends MultiDexApplication {
    private static BaseApplication baseApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
        AutoLayoutConifg.getInstance().useDeviceSize();
        ZXingLibrary.initDisplayOpinion(this);
    }
    public static Context getAppContext() {
        return baseApplication;
    }
}
