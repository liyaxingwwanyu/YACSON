package login;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import java.io.File;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.BSModelDefault;
import server.AgentVoData;
import server.HttpRequestDefault;
import top.coolook.gather.Gather;
import top.coolook.model.User;
import top.coolook.vo.AgentVo;
import util.ApplicationUtil;
import util.FileUtil;
import util.ImageUtil;
import util.InfoOfApp;
import util.InfoOfPhone;
import util.RegexUtil;
import util.TokenToolkit;


/**
 * Created by Administrator on 2016/5/25.
 */
public class BSModelForLogin extends BSModelDefault {
    public static final List<String> FACEBOOK_PERMISSIONS = Arrays.asList("public_profile", "user_friends", "email");
//    public static final int FACEBOOK_ACTIVITY_RESULT = 64206;
//    public static final int TWITTER_ACTIVITY_RESULT = 140;
//    public static final String TWITTER_KEY = "Gu7tDLpgpY708IipoeTz6Tbhn";
//    public static final String TWITTER_SECRET = "Op6EnqcCeBq5Vx9ZWnioMAaD1dpTshfsvGl01j4HRs2YKCUbpa";
//    public static final int GOOGLE_PLUS_ACTIVITY_RESULT = 9001;
//    public static final String GOOGLE_PLUS_REQUEST_ID = "512601654120-04t6ksqncedcfm46c8kg8c065i5u5i4c.apps.googleusercontent.com";

    public static final String SP_NAME = "LoginInfo";
    public static final String SP_FILED[] = {
            "SelfToken", "LoginPlatform", "ThirdToken", "ThirdIDFlag", "HeadImgUrl",
            "Nickname", "FirstName", "LastName", "Password", "Mail",
            "Mobile", "AgeRange", "TimeZone", "HomeLink", "Sex",
            "ActiveState", "HeadImgPath", "HeadImgPathTmp", "Uuid"};
    public static final String LOGIN_PLATFORMS[] = {"Coolook", "Facebook", "Twitter", "GooglePlus", "vk"};
    public static final String SEX[] = {"1", "2"};//1:男 2:女
    public static final String ACTIVE_STATE[] = {"-1", "0", "1", "3"};//-1:未激活 0:激活 1:禁用  3:删除
    public static final List<String> mLoginPlatformList = Arrays.asList(LOGIN_PLATFORMS);

    protected Context mContext;
    private SharedPreferences mSP;

    private String mCurEmail, mCurPassword, mOldPassword;
    private String mThirdLoginPlatform;
    private String mHeadImageBase64;
    private Map<String, String> mThirdInfo;

    protected HttpRequestDefault mHttpRequestDefault;
    protected AgentVo mAgentVo;

    public static final String mIDs[] = {"InputInfo", "Register", "SwitchPage", "Login", "ForgetPassword", "ResetPassword", "PersonalCenter"};
    public static final String mStates[] = {"EmailNull", "PasswordNull", "EmailFormatError", "PasswordFormatError", "PasswordError", "Success",
            "EmailExist", "Error", "EmailNotExist", "SentEmail", "OldEqualNewPassword", "HeadPortraitUpdate", "ReCheck", "TimeOut"};//0-13
    protected String mCurID;
    protected String mCurState;

    public static final int TAKING_PICTURES_CODE = 0;
    public static final int READ_GALLERY_CODE = 1;
    public static final int CUT_IMAGE_CODE = 2;
    private String mHeadImagePath = "HeadPortrait";
    private File mPrepareFile;

    private TokenToolkit mTokenToolkit;

    public BSModelForLogin(Context aContext) {
        super();
        mContext = aContext;
        mSP = mContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        mHttpRequestDefault = new HttpRequestDefault();
        mHttpRequestDefault.initHttps();
        mTokenToolkit = new TokenToolkit(mContext, mHttpRequestDefault);
    }

    public void init() {
        initUserInfo();
    }

    public void reCheckLogin() {
        initUserInfo();
        mCurID = mIDs[3];
        mCurState = mStates[12];
        updateObserver(this);
    }

    @Override
    public void setCurPos(int aPosForTotal) {//0:注册， 1：登录， 2：忘记密码， 3：个人中心， 4：修改密码
        mPosForReal = aPosForTotal;
        if (mPosForReal == 1) {
            mHandler.removeMessages(9923);
        }
        mCurID = mIDs[2];
        updateObserver(this);
    }

    private void initUserInfo() {
        mAbstractsList.clear();
        for (int i = 0; i < SP_FILED.length; i++) {
            mAbstractsList.add(mSP.getString(SP_FILED[i], null));
        }
    }

    protected void checkToken() {
        mTokenToolkit.checkToken();
//        if (TextUtils.isEmpty(mSP.getString(SP_FILED[0], null))) {
//            initToken();
//        } else {
//            updateToken(true);
//        }
    }

//    private void initToken() {
//        String lAppID = "1";
//        String lSecret = "aefewfrvgerb";
//        long lCurTime = System.currentTimeMillis();
//        String lSign = DES.desCrypto(lAppID + "|" + lSecret + "|" + lCurTime, DES.getPwd());
//        lSign = URLEncoder.encode(lSign);
//        String lUrl = "https://api-center.coolook.org/auth/gettoken" + "?sign=" + lSign + "&subtime=" + lCurTime;
//        System.out.println("BSModelForLogin initToken lUrl = " + lUrl);
//
//        String lJsonData = mHttpRequestDefault.requestSync(lUrl);
//        System.out.println("BSModelForLogin initToken lJsonData = " + lJsonData);
//
//        AgentVo lAgentVo = AgentVoData.resolve(lJsonData);
//        if (lAgentVo != null) {
//            String lSelfToken = lAgentVo.getAccess_token();
//            System.out.println("BSModelForLogin initToken lSelfToken = " + lSelfToken);
//            mSP.edit().putString(SP_FILED[0], lSelfToken).commit();
//        }
//    }
//
//    private void updateToken(boolean aWhile) {
//        String lOldToken = mSP.getString(SP_FILED[0], null);
//        if (!TextUtils.isEmpty(lOldToken)) {
//            String lUrl = "https://api-center.coolook.org/authu/refreshtoken" + "?access_token=" + lOldToken + "&uid=" + Gather.getUUID(mContext);
//            System.out.println("BSModelForLogin updateToken lUrl = " + lUrl);
//            String lJsonData = mHttpRequestDefault.requestSync(lUrl);
//            System.out.println("BSModelForLogin updateToken lJsonData = " + lJsonData);
//            AgentVo lAgentVo = AgentVoData.resolveForOriginal(lJsonData);
//            if (lAgentVo != null) {
//                if (lAgentVo.getStatus() == 0) {
//                    String lSelfToken = lAgentVo.getAccess_token();
//                    System.out.println("BSModelForLogin updateToken lSelfToken = " + lSelfToken);
//                    mSP.edit().putString(SP_FILED[0], lSelfToken).commit();
//                }
//                if (aWhile && lAgentVo.getStatus() != 0) {
//                    initToken();
//                    if (!TextUtils.isEmpty(mSP.getString(SP_FILED[1], null))) {
//                        updateToken(false);
//                    }
//                }
//            }
//        }
//    }

    //头像
    public void inputInfo(Bitmap aHeadImage) {
        mHeadImageBase64 = "";
        if (aHeadImage != null) {
            mHeadImageBase64 = ImageUtil.bitmapToBase64(aHeadImage);
        }
    }

    //第三方信息
    public void inputInfo(String aThirdLoginPlatform, Map<String, String> aThirdInfo) {
        mThirdLoginPlatform = aThirdLoginPlatform;
        mThirdInfo = aThirdInfo;
    }

    //激活
    public void inputInfo(String aEmail) {
        inputInfo(aEmail, true, null, false, null, false);
    }

    //登录注册
    public void inputInfo(String aEmail, String aCurPassword) {
        inputInfo(aEmail, true, aCurPassword, true, null, false);
    }

    //修改密码
    public void inputInfo(String aEmail, String aCurPassword, String aOldPassword) {
        inputInfo(aEmail, false, aCurPassword, true, aOldPassword, true);
    }

    private void inputInfo(String aEmail, boolean aCheckEmail, String aCurPassword, boolean aCheckCurPassword, String aOldPassword, boolean aCheckOldPassword) {
        mCurEmail = null;
        mCurPassword = null;
        mOldPassword = null;
        if (aCheckEmail) {
            if (TextUtils.isEmpty(aEmail)) {
                //邮箱为空
                mCurID = mIDs[0];
                mCurState = mStates[0];
                updateObserver(this);
                return;
            }
            if (!RegexUtil.isEmail(aEmail)) {
                //邮箱格式有误
                mCurID = mIDs[0];
                mCurState = mStates[2];
                updateObserver(this);
                return;
            }
        }
        if (aCheckOldPassword) {
            if (TextUtils.isEmpty(aOldPassword)) {
                //旧密码为空
                mCurID = mIDs[0];
                mCurState = mStates[1];
                updateObserver(this);
                return;
            }
            if (!RegexUtil.checkPassword(aOldPassword)) {
                // 密码格式错误
                mCurID = mIDs[0];
                mCurState = mStates[3];
                updateObserver(this);
                return;
            }
        }
        if (aCheckCurPassword) {
            if (TextUtils.isEmpty(aCurPassword)) {
                //当前密码为空
                mCurID = mIDs[0];
                mCurState = mStates[1];
                updateObserver(this);
                return;
            }
            if (!RegexUtil.checkPassword(aCurPassword)) {
                // 密码格式错误
                mCurID = mIDs[0];
                mCurState = mStates[3];
                updateObserver(this);
                return;
            }
        }
        if (aCheckOldPassword && aCheckCurPassword && aOldPassword.equals(aCurPassword)) {
            // 新旧密码不能相同
            mCurID = mIDs[0];
            mCurState = mStates[10];
            updateObserver(this);
            return;
        }
        mCurEmail = aEmail;
        mCurPassword = aCurPassword;
        mOldPassword = aOldPassword;
    }

    public void register() {
        if (!TextUtils.isEmpty(mCurEmail) && !TextUtils.isEmpty(mCurPassword)) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    checkToken();
                    String lUrl = "https://api-center.coolook.org/authu/register";
                    Map<String, String> lRegisterParam = getRegisterParam();
                    System.out.println("BSModelForLogin register lParam = " + lRegisterParam);
                    String lJsonData = mHttpRequestDefault.requestSyncForPost(lUrl, lRegisterParam);
                    System.out.println("BSModelForLogin register lJsonData = " + lJsonData);
                    mAgentVo = AgentVoData.resolveForOriginal(lJsonData);
                    System.out.println("mAgentVo = " + mAgentVo);

                    if (mAgentVo != null) {
                        switch (mAgentVo.getStatus()) {
                            case 0:
                                updateUserInfo(LOGIN_PLATFORMS[0], false);
                                if (LOGIN_PLATFORMS[0].equals(mSP.getString(SP_FILED[1], null))) {
                                    mHandler.sendEmptyMessage(9907);
                                } else {
                                    mHandler.sendEmptyMessage(9908);
                                }
                                break;
                            case 1:
                                mHandler.sendEmptyMessage(9909);
                                break;
                            case 2:
                                mHandler.sendEmptyMessage(9908);
                                break;
                        }
                    } else {
                        mHandler.sendEmptyMessage(9908);
                    }
                }
            }).start();
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 9907://注册成功
                    mCurID = mIDs[1];
                    mCurState = mStates[5];
                    updateObserver(BSModelForLogin.this);
                    break;
                case 9908://账户添加失败
                    mCurID = mIDs[1];
                    mCurState = mStates[7];
                    updateObserver(BSModelForLogin.this);
                    break;
                case 9909: //邮箱已存在
                    mCurID = mIDs[1];
                    mCurState = mStates[6];
                    updateObserver(BSModelForLogin.this);
                    break;
                case 9910: //登录成功
                    mCurID = mIDs[3];
                    mCurState = mStates[5];
                    updateObserver(BSModelForLogin.this);
                    break;
                case 9911: //登录失败
                    mCurID = mIDs[3];
                    mCurState = mStates[7];
                    updateObserver(BSModelForLogin.this);
                    break;
                case 9912: //登录邮箱不存在
                    mCurID = mIDs[3];
                    mCurState = mStates[8];
                    updateObserver(BSModelForLogin.this);
                    break;
                case 9913: //密码错误
                    mCurID = mIDs[3];
                    mCurState = mStates[4];
                    updateObserver(BSModelForLogin.this);
                    break;
                case 9914: //邮件已发送
                    mCurID = mIDs[4];
                    mCurState = mStates[9];
                    updateObserver(BSModelForLogin.this);
                    break;
                case 9915: //找回的邮箱不存在
                    mCurID = mIDs[4];
                    mCurState = mStates[8];
                    updateObserver(BSModelForLogin.this);
                    break;
                case 9916: //找回失败
                    mCurID = mIDs[4];
                    mCurState = mStates[7];
                    updateObserver(BSModelForLogin.this);
                    break;
                case 9917: //修改成功
                    mCurID = mIDs[5];
                    mCurState = mStates[5];
                    updateObserver(BSModelForLogin.this);
                    break;
                case 9918: //修改账户不存在
                    mCurID = mIDs[5];
                    mCurState = mStates[8];
                    updateObserver(BSModelForLogin.this);
                    break;
                case 9919: //旧密码有误
                    mCurID = mIDs[5];
                    mCurState = mStates[4];
                    updateObserver(BSModelForLogin.this);
                    break;
                case 9920: //密码修改失败
                    mCurID = mIDs[5];
                    mCurState = mStates[7];
                    updateObserver(BSModelForLogin.this);
                    break;
                case 9921://已重新发送邮件
                    mCurID = mIDs[6];
                    mCurState = mStates[9];
                    updateObserver(BSModelForLogin.this);
                    break;
                case 9922://重新发送邮件失败
                    mCurID = mIDs[6];
                    mCurState = mStates[7];
                    updateObserver(BSModelForLogin.this);
                    break;
                case 9923://重新发送邮件过3秒
                    if (mPosForReal == 2) {
                        mCurID = mIDs[4];
                        mCurState = mStates[13];
                        updateObserver(BSModelForLogin.this);
                    }
                    break;
            }
        }
    };

    private Map<String, String> getRegisterParam() {
//        CountryRelevantModel lCountryRelevantModel = new CountryRelevantModel(mContext);
//        String lSelectedLanguageID = lCountryRelevantModel.getSPString(BSConfig.SP_FILED[2][9]);
//        String lCountryCode = lCountryRelevantModel.getSPString(BSConfig.SP_FILED[2][1]);
        InfoOfPhone lInfoOfPhone = new InfoOfPhone(mContext);
        lInfoOfPhone.init();
        lInfoOfPhone.execute();
        Map<String, Object> lPhoneData = lInfoOfPhone.getData();
        InfoOfApp lInfoOfApp = new InfoOfApp(mContext);
        lInfoOfApp.init();
        lInfoOfApp.execute();
        Map<String, Object> lAppData = lInfoOfApp.getData();
        Map<String, String> lParam = new HashMap<String, String>();


        String lSelfToken = mSP.getString(SP_FILED[0], null);
        SharedPreferences.Editor lEditor = mSP.edit();
        lEditor.clear();
        lEditor.putString(SP_FILED[0], lSelfToken);
        lEditor.commit();
        Gather.resetUuid(mContext, lSelfToken);

        lParam.put("uuid", Gather.getUUID(mContext));
        lParam.put("langid", "cn");
        lParam.put("countrycode", "36");
        lParam.put("email", mCurEmail);
        lParam.put("latitude", "");
        lParam.put("longitude", "");
        lParam.put("password", mCurPassword);
        lParam.put("imei", lPhoneData.get(InfoOfPhone.KEYS[9]) + "");
        lParam.put("source", "1");
        lParam.put("changeid", ApplicationUtil.getChannelCode(mContext));
        lParam.put("versioncode", lAppData.get(InfoOfApp.KEYS[0]) + "");
        lParam.put("access_token", mSP.getString(SP_FILED[0], null));
        return lParam;
    }

    private void updateUserInfo(String aLoginPlatforms, boolean aUpdateGatherUUID) {
        User lUser = mAgentVo.getUser();
        if (lUser != null) {
            String lSelfToken = mSP.getString(SP_FILED[0], null);
            SharedPreferences.Editor lEditor = mSP.edit();
            lEditor.clear();
            lEditor.putString(SP_FILED[0], lSelfToken);
            lEditor.putString(SP_FILED[1], aLoginPlatforms);
            lEditor.putString(SP_FILED[4], lUser.getHeadurl());
            lEditor.putString(SP_FILED[5], lUser.getNicename());
            lEditor.putString(SP_FILED[8], lUser.getPassword());
            lEditor.putString(SP_FILED[9], lUser.getEmail());
            lEditor.putString(SP_FILED[10], lUser.getMobile());
            lEditor.putString(SP_FILED[11], lUser.getAge() + "");
            lEditor.putString(SP_FILED[14], lUser.getSex() == null ? "1" : lUser.getSex() + "");
            lEditor.putString(SP_FILED[15], lUser.getStatus() + "");
            lEditor.putString(SP_FILED[18], lUser.getUuid() + "");
            System.out.println("BSModelForLogin updateUserInfo getEmail = " + lUser.getEmail());
            System.out.println("BSModelForLogin updateUserInfo getPassword = " + lUser.getPassword());
            System.out.println("BSModelForLogin updateUserInfo getNickname = " + lUser.getNicename());
            System.out.println("BSModelForLogin updateUserInfo getUuid = " + lUser.getUuid());
            lEditor.commit();
            if (aUpdateGatherUUID) {
                Gather.updateUUID(mContext, lUser.getUuid());
            }
        }
    }

    public void login() {
        if (!TextUtils.isEmpty(mCurEmail) && !TextUtils.isEmpty(mCurPassword)) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    checkToken();
                    String lUrl = "https://api-center.coolook.org/authu/login";
                    Map<String, String> lLoginParam = getLoginParam();
                    System.out.println("BSModelForLogin login lParam = " + lLoginParam);
                    String lJsonData = mHttpRequestDefault.requestSyncForPost(lUrl, lLoginParam);
                    System.out.println("BSModelForLogin login lJsonData = " + lJsonData);
                    mAgentVo = AgentVoData.resolveForOriginal(lJsonData);
                    if (mAgentVo != null) {
                        switch (mAgentVo.getStatus()) {
                            case 0:
                                updateUserInfo(LOGIN_PLATFORMS[0], true);
                                if (LOGIN_PLATFORMS[0].equals(mSP.getString(SP_FILED[1], null))) {
                                    mHandler.sendEmptyMessage(9910);
                                } else {
                                    mHandler.sendEmptyMessage(9911);
                                }
                                break;
                            case 1:
                                //邮箱不存在
                                mHandler.sendEmptyMessage(9912);
                                break;
                            case 2:
                                //邮箱密码不匹配
                                mHandler.sendEmptyMessage(9913);
                                break;
                            case 3:
                                //未授权用户
                                mHandler.sendEmptyMessage(9911);
                                break;
                        }
                    } else {
                        //登录失败
                        mHandler.sendEmptyMessage(9911);
                    }
                }
            }).start();
        }
    }

    private Map<String, String> getLoginParam() {
        Map<String, String> lParam = new HashMap<String, String>();
        lParam.put("email", mCurEmail);
        lParam.put("password", mCurPassword);
        lParam.put("access_token", mSP.getString(SP_FILED[0], null));
        return lParam;
    }

    public void forgetPassword() {
        if (!TextUtils.isEmpty(mCurEmail) && !TextUtils.isEmpty(mCurPassword)) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    checkToken();
                    String lUrl = "https://api-center.coolook.org/authu/forgetpassword";
                    Map<String, String> lForgetPasswordParam = getForgetPasswordParam();
                    System.out.println("BSModelForLogin forgetPassword lParam = " + lForgetPasswordParam);
                    String lJsonData = mHttpRequestDefault.requestSyncForPost(lUrl, lForgetPasswordParam);
                    System.out.println("BSModelForLogin forgetPassword lJsonData = " + lJsonData);
                    mAgentVo = AgentVoData.resolveForOriginal(lJsonData);
                    if (mAgentVo != null) {
                        switch (mAgentVo.getStatus()) {
                            case 0:
                                //已经发送到邮箱
                                mHandler.sendEmptyMessage(9914);
                                mHandler.sendEmptyMessageDelayed(9923, 3000);
                                break;
                            case 1:
                                //账户不存在
                                mHandler.sendEmptyMessage(9915);
                                break;
                        }
                    } else {
                        mHandler.sendEmptyMessage(9916);
                    }
                }
            }).start();
        }
    }

    private Map<String, String> getForgetPasswordParam() {
        Map<String, String> lParam = new HashMap<String, String>();
        lParam.put("email", mCurEmail);
        lParam.put("newpassword", mCurPassword);
        lParam.put("access_token", mSP.getString(SP_FILED[0], null));
        return lParam;
    }

    public void resetPassword() {
        if (!TextUtils.isEmpty(mCurPassword) && !TextUtils.isEmpty(mOldPassword)) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    checkToken();
                    String lUrl = "https://api-center.coolook.org/authu/changepassword";
                    Map<String, String> lResetPasswordParam = getResetPasswordParam();
                    System.out.println("BSModelForLogin resetPassword lParam = " + lResetPasswordParam);
                    String lJsonData = mHttpRequestDefault.requestSyncForPost(lUrl, lResetPasswordParam);
                    System.out.println("BSModelForLogin resetPassword lJsonData = " + lJsonData);
                    mAgentVo = AgentVoData.resolveForOriginal(lJsonData);
                    if (mAgentVo != null) {
                        switch (mAgentVo.getStatus()) {
                            case 0:
                                updateUserInfo(LOGIN_PLATFORMS[0], false);
                                //重置成功
                                mHandler.sendEmptyMessage(9917);
                                break;
                            case 1:
                                //账户不存在
                                mHandler.sendEmptyMessage(9918);
                                break;
                            case 2:
                                //旧密码有误
                                mHandler.sendEmptyMessage(9919);
                                break;
                            case 3:
                                //账户未登录
                                mHandler.sendEmptyMessage(9920);
                                break;
                        }
                    } else {
                        mHandler.sendEmptyMessage(9920);
                    }
                }
            }).start();
        }
    }

    private Map<String, String> getResetPasswordParam() {
        Map<String, String> lParam = new HashMap<String, String>();
        lParam.put("uuid", Gather.getUUID(mContext));
        lParam.put("oldpassword", mOldPassword);
        lParam.put("newpassword", mCurPassword);
        lParam.put("access_token", mSP.getString(SP_FILED[0], null));
        return lParam;
    }

    private void reactivationEmail() {
        if (!TextUtils.isEmpty(mCurEmail)) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    checkToken();
                    String lUrl = "https://api-center.coolook.org/authu/reqactive";
                    Map<String, String> lReactivationEmailParam = getReactivationEmailParam();
                    System.out.println("BSModelForLogin reactivationEmail lParam = " + lReactivationEmailParam);
                    String lJsonData = mHttpRequestDefault.requestSyncForPost(lUrl, lReactivationEmailParam);
                    System.out.println("BSModelForLogin reactivationEmail lJsonData = " + lJsonData);
                    mAgentVo = AgentVoData.resolveForOriginal(lJsonData);
                    if (mAgentVo != null) {
                        switch (mAgentVo.getStatus()) {
                            case 0:
                                //已经发送到邮箱
                                mSP.edit().putLong(SEND_EMAIL_TIME_KEY, System.currentTimeMillis()).commit();
                                mHandler.sendEmptyMessage(9921);
                                break;
                            case 1:
                                //账户不存在
                                mHandler.sendEmptyMessage(9922);
                                break;
                            case 2:
                                //非法账户
                                mHandler.sendEmptyMessage(9922);
                                break;
                        }
                    } else {
                        mHandler.sendEmptyMessage(9922);
                    }
                }
            }).start();
        }
    }

    private final String SEND_EMAIL_TIME_KEY = "SEND_EMAIL_TIME_KEY";

    public boolean reactivationEmailEx() {
        long lOldTime = mSP.getLong(SEND_EMAIL_TIME_KEY, 0);
        long lNewTime = System.currentTimeMillis();
        boolean lIsSend = lNewTime - lOldTime > 300000;
        if (lIsSend) {
            reactivationEmail();
        }
        return lIsSend;
    }

    private Map<String, String> getReactivationEmailParam() {
        Map<String, String> lParam = new HashMap<String, String>();
        lParam.put("email", mCurEmail);
        lParam.put("access_token", mSP.getString(SP_FILED[0], null));
        return lParam;
    }

    public void thirdLogin() {
        if (!TextUtils.isEmpty(mThirdLoginPlatform) && mThirdInfo != null) {
            SharedPreferences.Editor lEditor = mSP.edit();
            lEditor.putString(SP_FILED[1], mThirdLoginPlatform);
            for (Map.Entry<String, String> entry : mThirdInfo.entrySet()) {
                lEditor.putString(entry.getKey(), entry.getValue());
            }
            lEditor.commit();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    checkToken();
                    String lUrl = "https://api-center.coolook.org/authu/loginme";
                    Map<String, String> lThirdLoginParam = getThirdLoginParam();
                    System.out.println("BSModelForLogin thirdLogin lParam = " + lThirdLoginParam);
                    String lJsonData = mHttpRequestDefault.requestSyncForPost(lUrl, lThirdLoginParam);
                    System.out.println("BSModelForLogin thirdLogin lJsonData = " + lJsonData);
                    mAgentVo = AgentVoData.resolveForOriginal(lJsonData);
                    if (mAgentVo != null) {
                        switch (mAgentVo.getStatus()) {
                            case 0:
                                String lLoginPlatform = mSP.getString(SP_FILED[1], null);
                                if (!TextUtils.isEmpty(lLoginPlatform)) {
                                    updateUserInfo(lLoginPlatform, true);
                                }
                                break;
                            case 2:
                                //服务器添加数据失败
                                break;
                        }
                    }
                }
            }).start();
        }
    }

    private Map<String, String> getThirdLoginParam() {
//        CountryRelevantModel lCountryRelevantModel = new CountryRelevantModel(mContext);
//        String lSelectedLanguageID = lCountryRelevantModel.getSPString(BSConfig.SP_FILED[2][9]);
//        String lCountryCode = lCountryRelevantModel.getSPString(BSConfig.SP_FILED[2][1]);
        InfoOfPhone lInfoOfPhone = new InfoOfPhone(mContext);
        lInfoOfPhone.init();
        lInfoOfPhone.execute();
        Map<String, Object> lPhoneData = lInfoOfPhone.getData();
        InfoOfApp lInfoOfApp = new InfoOfApp(mContext);
        lInfoOfApp.init();
        lInfoOfApp.execute();
        Map<String, Object> lAppData = lInfoOfApp.getData();

        Map<String, String> lParam = new HashMap<String, String>();
        lParam.put("uuid", Gather.getUUID(mContext));
        lParam.put("langid", "cn");
        lParam.put("countrycode", "36");
        lParam.put("latitude", "");
        lParam.put("longitude", "");
        lParam.put("location", "");
        lParam.put("imei", lPhoneData.get(InfoOfPhone.KEYS[9]) + "");
        lParam.put("source", "1");
        lParam.put("utype", mLoginPlatformList.indexOf(mThirdLoginPlatform) + "");
        lParam.put("identifyid", mSP.getString(SP_FILED[3], null));
        lParam.put("tokens", mSP.getString(SP_FILED[2], null));
        lParam.put("nicename", mSP.getString(SP_FILED[5], null));
        lParam.put("firstname", mSP.getString(SP_FILED[6], null));
        lParam.put("lastname", mSP.getString(SP_FILED[7], null));
        lParam.put("agerange", mSP.getString(SP_FILED[11], null));
        lParam.put("outemail", mSP.getString(SP_FILED[9], null));
        lParam.put("outmobile", mSP.getString(SP_FILED[10], null));
        lParam.put("homelink", mSP.getString(SP_FILED[13], null));
        lParam.put("timezone", mSP.getString(SP_FILED[12], null));
        lParam.put("headurl", mSP.getString(SP_FILED[4], null));
        lParam.put("changeid", ApplicationUtil.getChannelCode(mContext));
        lParam.put("versioncode", lAppData.get(InfoOfApp.KEYS[0]) + "");
        lParam.put("access_token", mSP.getString(SP_FILED[0], null));
        return lParam;
    }

    public void uploadHead() {
        if (!TextUtils.isEmpty(mHeadImageBase64)) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    checkToken();
                    String lUrl = "https://api-center.coolook.org/authu/uphead";
                    Map<String, String> lUploadHeadParam = getUploadHeadParam();
                    System.out.println("BSModelForLogin uploadHead lParam = " + lUploadHeadParam);
                    String lJsonData = mHttpRequestDefault.requestSyncForPost(lUrl, lUploadHeadParam);
                    System.out.println("BSModelForLogin uploadHead lJsonData = " + lJsonData);
                    mAgentVo = AgentVoData.resolveForOriginal(lJsonData);
                    if (mAgentVo != null) {
                        switch (mAgentVo.getStatus()) {
                            case 0:
                                // 头像上传成功
                                mSP.edit().putString(SP_FILED[4], mAgentVo.getHeadimg()).commit();
                                break;
                            case 1:
                                //账户不存在
                                break;
                        }
                    } else {
                        //头像上传失败
                    }
                }
            }).start();
        }
    }

    private Map<String, String> getUploadHeadParam() {
        Map<String, String> lParam = new HashMap<String, String>();
        lParam.put("uuid", Gather.getUUID(mContext));
        lParam.put("headimg", URLEncoder.encode(mHeadImageBase64));
        lParam.put("access_token", mSP.getString(SP_FILED[0], null));
        return lParam;
    }

    public void uploadUserInfo() {
        if (!TextUtils.isEmpty(mSP.getString(SP_FILED[1], null))) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    checkToken();
                    String lUrl = "https://api-center.coolook.org/authu/update";
                    Map<String, String> lUploadUserInfoParam = getUploadUserInfoParam();
                    System.out.println("BSModelForLogin uploadUserInfo lParam = " + lUploadUserInfoParam);
                    String lJsonData = mHttpRequestDefault.requestSyncForPost(lUrl, lUploadUserInfoParam);
                    System.out.println("BSModelForLogin uploadUserInfo lJsonData = " + lJsonData);
                    mAgentVo = AgentVoData.resolveForOriginal(lJsonData);
                    if (mAgentVo != null) {
                        switch (mAgentVo.getStatus()) {
                            case 0:
                                //上传成功
                                break;
                            case 1:
                                //账户未登录
                                break;
                            case 2:
                                //账户有误
                                break;
                        }
                    } else {
                        //上传信息失败
                    }
                }
            }).start();
        }
    }

    private Map<String, String> getUploadUserInfoParam() {
//        CountryRelevantModel lCountryRelevantModel = new CountryRelevantModel(mContext);
//        String lCountryCode = lCountryRelevantModel.getSPString(BSConfig.SP_FILED[2][1]);
        Map<String, String> lParam = new HashMap<String, String>();
        lParam.put("uuid", Gather.getUUID(mContext));
        lParam.put("access_token", mSP.getString(SP_FILED[0], null));
        lParam.put("countrycode", "36");
        lParam.put("nicename", mSP.getString(SP_FILED[5], null));
        lParam.put("sex", mSP.getString(SP_FILED[14], null));
        return lParam;
    }

    public void exitLogin() {
        //-------- FB login start --------------
//        if (LOGIN_PLATFORMS[1].equals(mSP.getString(SP_FILED[1], null))) {
//            try {
//                LoginManager.getInstance().logOut();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        //-------- FB login end --------------
//
//        //-------- TW login start --------------
//        if (LOGIN_PLATFORMS[2].equals(mSP.getString(SP_FILED[1], null))) {
//            try {
//                TwitterCore.getInstance().logOut();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        //-------- TW login end --------------
//        //-------- G+ login start --------------
//        if (LOGIN_PLATFORMS[3].equals(mSP.getString(SP_FILED[1], null))) {
//            //Auth.GoogleSignInApi.signOut()
//        }
//        //-------- G+ login end --------------

        String lSelfToken = mSP.getString(SP_FILED[0], null);
        String lCurEmail = mSP.getString(SP_FILED[9], null);
        SharedPreferences.Editor lEditor = mSP.edit();
        lEditor.clear();
        lEditor.putString(SP_FILED[0], lSelfToken);
        lEditor.putString(SP_FILED[9], lCurEmail);
        lEditor.commit();
        Gather.resetUuid(mContext, lSelfToken);
    }

    public void updateInfo(String aKey, String aValue) {
        mSP.edit().putString(aKey, aValue).commit();
    }

    public File getPrepareFile(boolean aNew) {
        if (aNew) {
            mPrepareFile = FileUtil.makeJPGFile(mContext, mHeadImagePath);
        }
        return mPrepareFile;
    }

    public void handleCutResult() {
        if (mPrepareFile != null && mPrepareFile.exists()) {
            String lPath = mPrepareFile.getAbsolutePath();
            mSP.edit().putString(SP_FILED[16], lPath).commit();
            initUserInfo();
            mCurID = mIDs[6];
            mCurState = mStates[11];
            updateObserver(BSModelForLogin.this);
        }
    }

    public String getCurID() {
        return mCurID;
    }

    public String getCurState() {
        return mCurState;
    }

    public String getSP(String aKey) {
        return mSP.getString(aKey, null);
    }
}
