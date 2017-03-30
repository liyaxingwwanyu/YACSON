package util;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.webkit.WebView;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class InfoOfPhone {
    public static final String KEYS[] = {"androidId", "deviceLanguage", "languageType", "networkOperator", "deviceModel", "phoneType"
            , "manufacturer", "systemVersion", "macAddr", "imei", "screenHeight", "screenWidth", "resolution", "imsi", "ua", "locale"};//0-15

    private Context mContext;
    private Map<String, Object> mDataMap;

    private DisplayMetrics mDisplayMetrics;
    private int mWidthPixels;
    private int mHeightPixels;

    private TelephonyManager mTelephonyMgr;
    private WifiInfo mWifiInfo;

    public InfoOfPhone(Context aContext) {
        mContext = aContext;
        mDataMap = new HashMap<String, Object>();
        mDisplayMetrics = new DisplayMetrics();
    }

    public Map<String, Object> getData() {
        return mDataMap;
    }

    public void init() {
        try {
            mTelephonyMgr = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
            if (mContext instanceof Activity) {
                ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
                mWidthPixels = mDisplayMetrics.widthPixels;
                mHeightPixels = mDisplayMetrics.heightPixels;
            }
            WifiManager mWifiManager = (WifiManager) mContext.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            mWifiInfo = mWifiManager.getConnectionInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void execute() {
        getAndroidID();
        getDeviceLanguage();
        getNetworkOperator();
        getPhoneType();
        getManufacturer();
        getSystemVersion();
        getLocalMacAddress();
        getImei();
        getScreenHeight();
        getScreenWidth();
        getImsi();
        getResolution();
        getLanguageType();
        getUa();
        getLocale();
    }

    // Android ID
    private void getAndroidID() {
        String ANDROID_ID = Settings.System.getString(mContext.getContentResolver(), Settings.System.ANDROID_ID);
        mDataMap.put(KEYS[0], ANDROID_ID);
    }

    // 设备语言
    private void getDeviceLanguage() {
        String lDeviceLanguage = Locale.getDefault().getLanguage();
        mDataMap.put(KEYS[1], lDeviceLanguage);
    }

    // 得到语言类型 用 1中文，2英文，3印度
    private void getLanguageType() {
        String lCountry = mContext.getResources().getConfiguration().locale.getCountry();
        String lLanguageType = "2";
        if (lCountry.equals("CN")) {
            lLanguageType = "1";
        } else if (lCountry.equals("UK")) {
            lLanguageType = "2";
        } else if (lCountry.equals("US")) {
            lLanguageType = "2";
        } else if (lCountry.equals("IN")) {
            lLanguageType = "3";
        }
        mDataMap.put(KEYS[2], lLanguageType);
    }

    // 获取 运营商
    private void getNetworkOperator() {
        String lNetworkOperator = mTelephonyMgr.getNetworkOperator();
        mDataMap.put(KEYS[3], lNetworkOperator);
    }

    // 手机型号(设备类型)
    private void getPhoneType() {
        String lPhoneType = android.os.Build.MODEL;
        mDataMap.put(KEYS[4], lPhoneType);
        mDataMap.put(KEYS[5], lPhoneType);
    }

    // 制造厂商
    private void getManufacturer() {
        String lManufacturer = android.os.Build.MANUFACTURER;
        mDataMap.put(KEYS[6], lManufacturer);
    }

    // 系统版本(Android版本)
    private void getSystemVersion() {
        String lSystemVersion = android.os.Build.VERSION.RELEASE;
        mDataMap.put(KEYS[7], lSystemVersion);
    }

    // Mac地址
    private void getLocalMacAddress() {
        String lMacAddress = "";
        try {
            lMacAddress = mWifiInfo.getMacAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mDataMap.put(KEYS[8], lMacAddress);
    }

    // Imei
    private void getImei() {
        String lImei = "";
        try {
            lImei = mTelephonyMgr.getDeviceId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mDataMap.put(KEYS[9], lImei);
    }

    // 屏幕高
    private void getScreenHeight() {
        mDataMap.put(KEYS[10], mHeightPixels);
    }

    // 屏幕宽
    private void getScreenWidth() {
        mDataMap.put(KEYS[11], mWidthPixels);
    }

    // 屏幕分辨率
    private void getResolution() {
        String lResolution = mWidthPixels + "*" + mHeightPixels;
        mDataMap.put(KEYS[12], lResolution);
    }

    // Imsi
    private void getImsi() {
        String lImsi = "";
        try {
            lImsi = mTelephonyMgr.getSubscriberId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mDataMap.put(KEYS[13], lImsi);
    }

    // ua
    private void getUa() {
        String lPhoneUa = "Mozilla/5.0(Linux;U;Android4.0;en-us;GT-I9300Build/IMM76D)AppleWebKit/534.30(KHTML,likeGecko)Version/4.0MobileSafari/534.30";
        if(ApplicationUtil.isMainThread()){
            try {
                WebView lWebView = new WebView(mContext);
                lPhoneUa = lWebView.getSettings().getUserAgentString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mDataMap.put(KEYS[14], lPhoneUa);
    }

    private void getLocale() {
        String lLocale = Locale.getDefault().toString();
        mDataMap.put(KEYS[15], lLocale);
    }
}
