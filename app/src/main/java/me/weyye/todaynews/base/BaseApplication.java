package me.weyye.todaynews.base;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import me.weyye.todaynews.BuildConfig;
import me.weyye.todaynews.theme.colorUi.util.SharedPreferencesMgr;

/**
 * Created by RayYeung on 2016/8/8.
 */
public class BaseApplication extends Application {
    //private UserInfo userInfo;
    private static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        SharedPreferencesMgr.init(this, "weyye");
        initImageLoader();
    }

    private void initImageLoader() {
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(
                instance);
        config.memoryCacheExtraOptions(480, 800);
        config.diskCacheExtraOptions(480, 800, null);
        config.diskCacheSize(100 * 1024 * 1024); // 100 MiB
        if (BuildConfig.DEBUG) {
            config.writeDebugLogs(); // Remove for release app
        }
        ImageLoader.getInstance().init(config.build());
    }

    //    public UserInfo getUserInfo() {
//        return userInfo;
//    }
//
//    public void setUserInfo(UserInfo userInfo) {
//        this.userInfo = userInfo;
//    }
    public static BaseApplication getInstance() {
        return instance;
    }
}
