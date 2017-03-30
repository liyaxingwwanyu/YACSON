package util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by sheenly on 16/3/30.
 */
public class BSConfig {
    public static final String mADUrls[] = {"http://api.c.avazunativeads.com/c2s?", "facebook", "coolooknews", "http://api.w.inmobi.com/showad/v2",
            "ADFlash"};
    public static final String mADKeyValues[] = {"17598", "143472196021779_211732532529078", "4WKS7WTJXTXQPPY2888B", "97eb0852a5fc435fa7a04ef857eee153",
            "Statistic-Online-303-beijing7890-yuansheng-170208-20160318", "143472196021779_181574195544912", "241648152918543_242579729492052", "303e6a5d8e46461f9102b8e5a351026f"};//7   7:Amazon Mobile Analy
    public static final String mCommandIDs[] = {"top.coolook.toolkits.functions.FlashLightCommand", "top.coolook.toolkits.functions.Calculator",
            "top.coolook.toolkits.functions.WallPager", "top.coolook.toolkits.functions.CustomApp", "top.coolook.toolkits.functions.Camera",
            "top.coolook.toolkits.functions.Alarm", "top.coolook.toolkits.functions.SMS", "top.coolook.activity.search.SearchActivity",
            "top.coolook.toolkits.functions.Search", "top.coolook.activity.SettingActivity", "top.coolook.inmobigoogleplay.InmobiAppActivity",
            "top.coolook.toolkits.functions.Game", "top.coolook.toolkits.functions.CallPhone", "top.coolook.toolkits.functions.CallRecord",
            "top.coolook.activity.MinisiteWebView", "top.coolook.toolkits.functions.MinisiteCommand", "top.coolook.coolookscreen.screen.MainActivity",
            "top.coolook.coolookscreen.screen.ScreenShowTestActivty", "top.coolook.country.TopicSelectActivity",
            "top.coolook.country.CountrySelectActivity", "top.coolook.coolookscreen.setting.LSSettingActivityEx",
            "top.coolook.umengfeedBack.UmengForFeedbackEx", "top.coolook.bll.CheckVersionEx", "top.coolook.login.LoginRegisterActivity",
            "top.coolook.activity.MasterActivityEX", "login.PersonalCenterActivity", "collection.CollectionActivity",
            "top.coolook.publishedarticles.PublishedArticlesActivity", "top.coolook.activity.StieActivity", "top.coolook.sidefeedback.SideFeedbackActivity", "top.coolook.activity.SettingsActivity"
            , "top.coolook.activity.ShowKeyValueActivity"};//0-31

    public static final String DEVELOPER_KEY = "58595654803-h3v06lkh2rs6a9v02pu5j9po3c7bb52f.apps.googleusercontent.com";
    public static final String DEVELOPER_KEY2 = "AIzaSyBJoOfw2Cbi18l0U-cF3t0b1sDdizGHkik";


    public static final String YOUTUBE_URL = "https://www.googleapis.com/youtube/v3/videos?part=snippet&chart=mostPopular&regionCode=cn&key=" + DEVELOPER_KEY2;

    public static final String YOUTUBE_PAGETOKEN = "&pageToken=";
    public static final String mBaseUrl = "https://www.youtube.com/watch?v=";
    public static final String YOUTUBE_PAGETOKEN_END = "&regionCode=jp&key=" + DEVELOPER_KEY2;
    public static String YOUTUBE_URL2 = "https://www.googleapis.com/youtube/v3/videos?part=snippet&chart=mostPopular" + YOUTUBE_PAGETOKEN;


    public static final String DIRS[] = {"ScreenImgs", "WelcomeImg", "WelcomeAD", "ProphetImg"};
    public static final String SCREEN_IMAGE_DIR = DIRS[0];
    public static final String SP_NAME[] = {"top.coolook", "MiniSiteData", "CountryRelevant"};
    public static final String SP_FILED[][] = {{"SCPos", "SPSC", "CurPackage"}, {"mCurPath", "mCurContentUrl", "mCurISGame", "mCurTitle"},
            {"LocalCountryID", "LocalCountryCode", "LocalTopicsID", "LocalTopicsName", "UpdateTime",//4
                    "CountryState", "TopicsState", "LanguageState", "ClickTopicID", "SelectedLanguageID", "SelectedLanguageCode",//10
                    "SelectedCountryID", "SelectedCountryCode", "SelectedTopicsID", "SelectedTopicsName", "LocalIP", "LocalCountryName", "SelectedCountryName",
                    "SelectedLanguageName", "LocalSearchKeywords", "SelectedSearchKeywords", "ContinentID", "WelcomeImgUrl", "WelcomeImgPath",//23
                    "ShowPermission"}};
    public static final String mCaseCustomPosKey[][] = {{null, null, null, null, null, null, null}, {null, null, "0", "1", "2", "3", "4"}};
    public static final String mCaseCustomPosKeyForBar[][] = {{null, null, null, null, null}, {null, null, null, null}, {"0", "1", "2", "3", "4"}};

    public static final String ALL_FUNCTIONS[] = {"LockScreen", "TianLong", "ADFlashOut", "FeedBack", "ShareSDK", "russia"};
    public static final String OPEN_FUNCTIONS[] = {ALL_FUNCTIONS[5]};
    public static final List<String> mFunctionsForList = Arrays.asList(OPEN_FUNCTIONS);
    //广告显示的位置
    public static final String mModuleIDs[] = {"Waterfall", "MoreRecommend", "MiniSiteWebView", "LockScreen", "Welcome"};
    //广告模块开关
    public static final String mOperationTypes[] = {"coolook", "inmobi", "avazu", "facebook", "yahoo", "ADFlash", "gmobi", "firebase", "admob", "mytarget"};
//    public static NativeAppInstallAd BSNativeAdOKOK;

    public static int[] slotID = {65969, 65972, 65966};


    //登录相关key值
    public static final int TWITTER_ACTIVITY_RESULT = 140;
    public static final int FACEBOOK_ACTIVITY_RESULT = 64206;
    public static final String TWITTER_KEY = "Gu7tDLpgpY708IipoeTz6Tbhn";
    public static final String TWITTER_SECRET = "Op6EnqcCeBq5Vx9ZWnioMAaD1dpTshfsvGl01j4HRs2YKCUbpa";
    public static final int GOOGLE_PLUS_ACTIVITY_RESULT = 9001;
    public static final String GOOGLE_PLUS_REQUEST_ID = "58595654803-h3v06lkh2rs6a9v02pu5j9po3c7bb52f.apps.googleusercontent.com";

    public static int mUIVersion = 1;
    public static int mWFPageType = 1;//0-单屏浏览，1-多屏缓存浏览
    public static final String JUMP_TO_LOGIN_SOURCE[] = {"SidebarHead", "SidebarCollection", "MinSite"};//跳转页面的来源

    public static boolean getSwitchForFunction(String aFunction) {
        return mFunctionsForList.contains(aFunction);
    }

    public static void updateMiniSiteData(Context aContext, Map<String, String> aData) {
        SharedPreferences lSP = aContext.getSharedPreferences(SP_NAME[1], Context.MODE_PRIVATE);
        SharedPreferences.Editor lEditor = lSP.edit();
        lEditor.clear();
        for (Map.Entry<String, String> lData : aData.entrySet()) {
            lEditor.putString(lData.getKey(), lData.getValue());
        }
        lEditor.commit();
    }

    public static String getMiniSiteData(Context aContext, String aKey) {
        SharedPreferences lSP = aContext.getSharedPreferences(SP_NAME[1], Context.MODE_PRIVATE);
        return lSP.getString(aKey, null);
    }
}
