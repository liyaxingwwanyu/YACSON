package view;

import android.app.Application;
import android.content.Context;

import util.ImageLoaderUtill;


public class Location extends Application {
    public static Location instance;
//    private IUmengForPush mUmengForPush;
    public static Context mcontext;

    public void onCreate() {
        super.onCreate();
        this.mcontext = getApplicationContext();
//        RussiaUtil russiaUtil = new RussiaUtil(getApplicationContext());

        instance = this;
        ImageLoaderUtill.init(this);
        //雅虎广告初始化
//        BSModelForAD.initYahooForApplication(this);
//        try {
//            Class<?> lClass = Class.forName(CLReflection.UMENG_PUSH);
//            mUmengForPush = (IUmengForPush) lClass.newInstance();
//            mUmengForPush.setContext(this);
//            mUmengForPush.bindCallBack();
//        } catch (Exception e) {
//            ExceptionUtil.handle(e);
//        }

//        vkAccessTokenTracker.startTracking();
//        VKSdk.initialize(this);
//
//        UnCeHandler catchExcep = new UnCeHandler(this);
//        Thread.setDefaultUncaughtExceptionHandler(catchExcep);


    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        MultiDex.install(this);
    }

//    VKAccessTokenTracker vkAccessTokenTracker = new VKAccessTokenTracker() {
//        @Override
//        public void onVKAccessTokenChanged(VKAccessToken oldToken, VKAccessToken newToken) {
//            if (newToken == null) {
//                Toast.makeText(Location.this, "AccessToken invalidated", Toast.LENGTH_LONG).show();
//                /*Intent intent = new Intent(Location.this, ActivityConfigForLoginRegister.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);*/
//            }
//        }
//    };
}
