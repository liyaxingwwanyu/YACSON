package login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;

import com.newcoolook_0329.R;

import java.util.HashMap;
import java.util.Map;

import contrler.UILayoutMrg;
import contrler.UIStructDefault;
import model.BSModelDefault;
import observer.IObserver;
import util.ApplicationUtil;
import util.BSConfig;
import util.Consts;


/**
 * Created by Administrator on 2016/5/25.
 */
public abstract class ActivityConfigForLoginRegister extends FragmentActivity implements IObserver {

    protected View mView;
    private Integer mToIDs[] = {R.id.login_register_input_info_to_view, R.id.login_register_forget_password_to_view,
            R.id.login_register_title_bar_to_view, R.id.login_register_third_to_view};
    private Integer mChangeToIDs[] = {null, null, null, null};
    private Integer mMasterIDs[] = {R.layout.login_register_input_info_add_view, R.layout.login_register_forget_password_add_view,
            R.layout.login_register_title_bar_add_view, R.layout.login_register_third_login_btn_add_view};
    private Integer mDetailViewIDs[][] = {
            {R.id.login_register_input_msg_info_tv, R.id.login_register_mail_input_iv, R.id.login_register_mail_input_et,
                    R.id.login_register_mail_input_iv_line, R.id.login_register_password_input_iv, R.id.login_register_password_input_et,
                    R.id.login_register_password_input_iv_line, R.id.login_register_login_btn, R.id.login_register_login_btn_wait_iv,
                    R.id.login_register_mail_del, R.id.login_register_password_del},
            {R.id.login_register_forget_password_tv},
            {R.id.login_register_title_bar_back_btn, R.id.login_register_title_bar_radio_group,
                    R.id.login_register_title_bar_login_radio_btn, R.id.login_register_title_bar_register_radio_btn,
                    R.id.login_register_title_bar_forget_password_tv},
            {R.id.login_register_third_login_btn_parent_ll, R.id.login_register_third_login_btn_fb_iv,
                    R.id.login_register_third_login_btn_tw_iv, R.id.login_register_third_login_btn_gp_iv, R.id.login_register_third_login_above_ll}
    };
    private Integer mDetailDrawableIDs[][] = {
            {R.drawable.login_register_email_icon, R.drawable.login_register_password_icon}, {}, {}, {}
    };
    private Integer mDetailStateDrawableIDs[][] = {{}, {}, {}, {}};
    private String mStrings[][] = {
            {Consts.INPUT_EMAIL_ACCOUNTS, Consts.INPUT_PASSWORD, Consts.EMAIL_FORMAT_ERROR, Consts.PASSWORD_FORMAT, Consts.EMAIL_ACCOUNTS,
                    Consts.INPUT_PASSWORD, Consts.NEW_PASSWORD, Consts.REGISTER, Consts.EMAIL_HAS_BEEN_REGISTERED, Consts.FAILURE,
                    Consts.LOGIN, Consts.PASSWORD_MISTAKE, Consts.EMAIL_DOES_NOT_EXIST, Consts.SEND_EMAIL, Consts.EMAIL_HAS_BEEN_SENT},
            {}, {}, {}
    };

    private UILayoutConfigForLoginRegister mUILayoutConfig;
    private UILayoutMrg mUILayoutMrg;
    private BSModelForLogin mBSModelForLogin;

    //-------- FB login start --------------
//    private CallbackManager mCallbackManager;
//    private FacebookProfileTracker mFacebookProfileTracker;
//    //-------- FB login end --------------
//
//    //-------- TW login start --------------
//    private TwitterCallbackDefault mTwitterCallbackDefault;
//    //-------- TW login end --------------
//
//    //-------- G+ login start --------------
//    private GoogleApiClient mGoogleApiClient;

    //-------- G+ login end --------------


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBSModelForLogin = new BSModelForLogin(this);
        mBSModelForLogin.init();
        //-------- FB login start --------------
        initFaceBookLogin();
        //-------- FB login end --------------

        //-------- TW login start --------------
        initTwitterLogin();
        //-------- TW login end --------------

        //-------- G+ login start --------------
        initGooglePlusLogin();
        //-------- G+ login end --------------
    }

    protected void initUILayoutConfig() {
        mUILayoutConfig = new UILayoutConfigForLoginRegister();
        mUILayoutConfig.setIDs(mToIDs, mChangeToIDs, mMasterIDs, mDetailViewIDs, mDetailDrawableIDs, mDetailStateDrawableIDs, mStrings);
    }

    protected void initUILayoutMrg() {
        mUILayoutMrg = new UILayoutMrg(mUILayoutConfig, this, mView, this);
    }

    protected void initCreate() {
        Map<String, BSModelDefault> lBSModels = new HashMap<String, BSModelDefault>();
        lBSModels.put(UILayoutConfigForLoginRegister.mIDs[0], mBSModelForLogin);
        lBSModels.put(UILayoutConfigForLoginRegister.mIDs[2], mBSModelForLogin);
        mUILayoutMrg.create(new String[]{
                UILayoutConfigForLoginRegister.mIDs[3],
                UILayoutConfigForLoginRegister.mIDs[0],
//                UILayoutConfigForLoginRegister.mIDs[1],
//                UILayoutConfigForLoginRegister.mIDs[2]
        }, lBSModels);
        mBSModelForLogin.setCurPos(1);
    }

    @Override
    public void update(Object aObject) {
        if (aObject instanceof UIStructDefault) {
            UIStructDefault lUIStructDefault = (UIStructDefault) aObject;
            String lCurState = lUIStructDefault.getCurState();
            int lStateIndex = UILayoutConfigForLoginRegister.mStateForList.indexOf(lCurState);
            String lCurID = lUIStructDefault.getCurID();
            int lPos = UILayoutConfigForLoginRegister.mIDsForList.indexOf(lCurID);
            switch (lStateIndex) {
                case 0:
                    if (lPos == 0) {

                        if (mBSModelForLogin.getPosForReal() == 0) {

                            mBSModelForLogin.register();
                        }
                        if (mBSModelForLogin.getPosForReal() == 1) {
                            mBSModelForLogin.login();
                        }
                        if (mBSModelForLogin.getPosForReal() == 2) {
                            mBSModelForLogin.forgetPassword();
                        }
                    }
                    if (lPos == 1) {

                        mBSModelForLogin.setCurPos(2);
                        mUILayoutMrg.visibility(new String[]{UILayoutConfigForLoginRegister.mIDs[1]}, View.GONE);
                        mUILayoutMrg.visibilityAnimationForDetailView(UILayoutConfigForLoginRegister.mIDs[2], View.GONE, -1, new Integer[]{1});
                        mUILayoutMrg.visibilityAnimationForDetailView(UILayoutConfigForLoginRegister.mIDs[2], View.VISIBLE, -1, new Integer[]{4});
                        mUILayoutMrg.visibilityAnimationForDetailView(UILayoutConfigForLoginRegister.mIDs[3], View.GONE, -1, new Integer[]{0});
                    }
                    if (lPos == 2) {
                        back();
                    }
                    break;
                case 1:
                    ApplicationUtil.showInfo(this, Consts.REGISTRATION_EMAIL_ACTIVATION);
                    back();
                    break;
                case 2:
                    ApplicationUtil.showInfo(this, Consts.SUCCESS_LOGIN);
                    ApplicationUtil.openActivity(this, BSConfig.mCommandIDs[26], null);

                    break;
                case 3:


                    mBSModelForLogin.setCurPos(1);
                    mUILayoutMrg.visibility(new String[]{UILayoutConfigForLoginRegister.mIDs[1]}, View.VISIBLE);
                    break;
                case 4:
                    mBSModelForLogin.setCurPos(0);
                    mUILayoutMrg.visibility(new String[]{UILayoutConfigForLoginRegister.mIDs[1]}, View.GONE);
                    break;
                case 5:
                    //System.out.println("Facebook登录ooooooooooo");
                    //-------- FB login start --------------
//                    LoginManager.getInstance().logInWithReadPermissions(this, BSModelForLogin.FACEBOOK_PERMISSIONS);


                    //-------- FB login end --------------
                    break;
                case 6:
                    System.out.println("tufeer登录ooooooooooo");
                    //-------- TW login start --------------

                    //-------- TW login end --------------
                    //vk
               /*     String[] fingerprints = VKUtil.getCertificateFingerprint(this, this.getPackageName());
                    System.out.println("aaaa " + fingerprints.toString());
                    // Toast.makeText(ActivityConfigForLoginRegister.this, "aa aa " + Arrays.toString(fingerprints), Toast.LENGTH_SHORT).show();
                    System.out.println("aaaaaa " + Arrays.toString(fingerprints));
                    VKSdk.login(ActivityConfigForLoginRegister.this, sMyScope);*/
                    break;
                case 7:
                    //System.out.println("google登录ooooooooooo");
                    //-------- G+ login start --------------
//                    startActivityForResult(Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient), BSConfig.GOOGLE_PLUS_ACTIVITY_RESULT);


                    //-------- G+ login end --------------
                    break;
                case 8:
                    mUILayoutMrg.visibilityAnimationForDetailView(UILayoutConfigForLoginRegister.mIDs[3], View.VISIBLE, -1, new Integer[]{4});
                    break;
                case 9:
                    mUILayoutMrg.visibilityAnimationForDetailView(UILayoutConfigForLoginRegister.mIDs[3], View.GONE, -1, new Integer[]{4});
                    break;
                case 10:

                    back();
                    break;
            }
        }
        //-------- FB login start --------------
//        if (aObject instanceof Profile) { //Facebook登录
//            if (Profile.getCurrentProfile() != null) { // 登录成功
//                String lName = Profile.getCurrentProfile().getName();
//                Uri lHeadImgUri = Profile.getCurrentProfile().getProfilePictureUri(50, 50);
//                String lHeadImgUrl = (lHeadImgUri == null) ? "" : lHeadImgUri.toString();
//                String lThirdIDFlag = Profile.getCurrentProfile().getId();
//                String lThirdToken = AccessToken.getCurrentAccessToken().getToken();
//                Map<String, String> lParam = new HashMap<String, String>();
//                lParam.put(BSModelForLogin.SP_FILED[2], lThirdToken);
//                lParam.put(BSModelForLogin.SP_FILED[3], lThirdIDFlag);
//                lParam.put(BSModelForLogin.SP_FILED[4], lHeadImgUrl);
//                lParam.put(BSModelForLogin.SP_FILED[5], lName);
//                mBSModelForLogin.inputInfo(BSModelForLogin.LOGIN_PLATFORMS[1], lParam);
//                mBSModelForLogin.thirdLogin();
//                mFacebookProfileTracker.removeObserver(this);
//                mFacebookProfileTracker = null;
//                toWF();
//            }
//        }
        //-------- TW login start --------------
//        if (aObject instanceof Result) {
//            TwitterSession lTwitterSession = TwitterCore.getInstance().getSessionManager().getActiveSession();
//            if (lTwitterSession != null) {
//                String lThirdIDFlag = lTwitterSession.getUserId() + "";
//                String lName = lTwitterSession.getUserName();
//                String lThirdToken = "";
//                TwitterAuthToken lTwitterAuthToken = lTwitterSession.getAuthToken();
//                if (lTwitterAuthToken != null) {
//                    lThirdToken = lTwitterAuthToken.token;
//                }
//                Map<String, String> lParam = new HashMap<String, String>();
//                lParam.put(BSModelForLogin.SP_FILED[2], lThirdToken);
//                lParam.put(BSModelForLogin.SP_FILED[3], lThirdIDFlag);
//                //lParam.put(BSModelForLogin.SP_FILED[4], lHeadImgUrl);
//                lParam.put(BSModelForLogin.SP_FILED[5], lName);
//                mBSModelForLogin.inputInfo(BSModelForLogin.LOGIN_PLATFORMS[2], lParam);
//                mBSModelForLogin.thirdLogin();
//
//                mTwitterCallbackDefault.removeObserver(this);
//                mTwitterCallbackDefault = null;
//                toWF();
//            }
//        }
        //-------- TW login end --------------
    }

    //-------- FB login start --------------


    private void initFaceBookLogin() {
//        FacebookSdk.sdkInitialize(getApplicationContext());
//        mCallbackManager = CallbackManager.Factory.create();
//        LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallbackDefault());
//        mFacebookProfileTracker = FacebookProfileTracker.getInstance();
//        mFacebookProfileTracker.addObserver(this);
    }
    //-------- FB login end --------------

    //-------- TW login start --------------
    private void initTwitterLogin() {
//        mTwitterCallbackDefault = new TwitterCallbackDefault();
//        mTwitterCallbackDefault.addObserver(this);
//        TwitterAuthConfig lTwitterAuthConfig = new TwitterAuthConfig(BSConfig.TWITTER_KEY, BSConfig.TWITTER_SECRET);
//        Fabric.with(this, new TwitterCore(lTwitterAuthConfig), new Digits());
    }
    //-------- TW login end --------------

    //-------- G+ login start --------------
    private void initGooglePlusLogin() {
/*        GoogleSignInOptions lGoogleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail().requestIdToken(BSModelForLogin.GOOGLE_PLUS_REQUEST_ID).build();
        mGoogleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, new
                GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        System.out.println(connectionResult);
                    }
                }).addApi(Auth.GOOGLE_SIGN_IN_API, lGoogleSignInOptions).build();*/

//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail().requestIdToken(BSConfig.GOOGLE_PLUS_REQUEST_ID)
//                .build();
//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
//                    @Override
//                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//                    }
//                })
//                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
//                .build();
    }

    //-------- G+ login end --------------
//    private static final String[] sMyScope = new String[]{
//            VKScope.FRIENDS,
//            VKScope.WALL,
//            VKScope.PHOTOS,
//            VKScope.NOHTTPS,
//            VKScope.MESSAGES,
//            VKScope.DOCS
//    };

//    protected void handleVkResult(String text) {
//        Integer lThirdIDFlag = null;
//        String lName = "";
//        String lHeadImgUrl = "";
//        try {
//            JSONObject jsonObject = new JSONObject(text);
//            JSONArray array = jsonObject.getJSONArray("response");
//            lThirdIDFlag = (Integer) ((JSONObject) array.get(0)).get("id");
//            String name1 = (String) ((JSONObject) array.get(0)).get("first_name");
//            String name2 = (String) ((JSONObject) array.get(0)).get("last_name");
//            lName = name2 + name1;
//            lHeadImgUrl = (String) ((JSONObject) array.get(0)).get("photo_100");
//
//            String lThirdToken = vktoken;
//            Map<String, String> lParam = new HashMap<String, String>();
//            lParam.put(BSModelForLogin.SP_FILED[2], lThirdToken);
//            lParam.put(BSModelForLogin.SP_FILED[3], lThirdIDFlag.toString());
//            lParam.put(BSModelForLogin.SP_FILED[4], lHeadImgUrl);
//            lParam.put(BSModelForLogin.SP_FILED[5], lName);
//            mBSModelForLogin.inputInfo(BSModelForLogin.LOGIN_PLATFORMS[4], lParam);
//            mBSModelForLogin.thirdLogin();
//            toWF();
//        } catch (Exception e) { // 全部捕捉。
//            e.printStackTrace();
//        }
//    }
//
//
//    VKRequest.VKRequestListener mRequestListener = new VKRequest.VKRequestListener() {
//        @Override
//        public void onComplete(VKResponse response) {
//            handleVkResult(response.json.toString());
//        }
//
//        @Override
//        public void onError(VKError error) {
//            System.out.print("aaaaaaaaa" + error.toString());
//        }
//
//        @Override
//        public void onProgress(VKRequest.VKProgressType progressType, long bytesLoaded,
//                               long bytesTotal) {
//            // you can show progress of the request if you want
//        }
//
//        @Override
//        public void attemptFailed(VKRequest request, int attemptNumber, int totalAttempts) {
//            System.out.print("aaaaaaaaa" + String.format("Attempt %d/%d failed\n", attemptNumber, totalAttempts));
//
//        }
//    };
//
//
//    private void startApiCall(VKRequest request) {
//        long requestId = request.registerObject();
//        request = VKRequest.getRegisteredRequest(requestId);
//        if (request != null)
//            request.unregisterObject();
//        if (request == null) return;
//        request.executeWithListener(mRequestListener);
//
//    }
//
//    private String vktoken;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Toast.makeText(ActivityConfigForLoginRegister.this, "aa aa", Toast.LENGTH_SHORT).show();
/*        VKCallback<VKAccessToken> callback = new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {

                // Toast.makeText(ActivityConfigForLoginRegister.this, "aa " + res.accessToken, Toast.LENGTH_SHORT).show();
                vktoken = res.accessToken;
                // User passed Authorization
                VKRequest request = VKApi.users().get(VKParameters.from(VKApiConst.FIELDS,
                        "id,first_name,last_name,photo_100"
                ));
                request.secure = false;
                request.useSystemLanguage = false;
                startApiCall(request);
            }

            @Override
            public void onError(VKError error) {
                // User didn't pass Authorization
            }
        };

        if (!VKSdk.onActivityResult(requestCode, resultCode, data, callback)) {
            // Toast.makeText(ActivityConfigForLoginRegister.this, "aa a", Toast.LENGTH_SHORT).show();
            super.onActivityResult(requestCode, resultCode, data);
        }*/

        switch (requestCode) {
            //-------- FB login start --------------
            case BSConfig.FACEBOOK_ACTIVITY_RESULT:
//                mCallbackManager.onActivityResult(requestCode, resultCode, data);
                break;
            //-------- FB login end --------------
            //-------- TW login start --------------
            case BSConfig.TWITTER_ACTIVITY_RESULT:
//                new TwitterAuthClient().onActivityResult(requestCode, resultCode, data);
                break;
            //-------- TW login end --------------
            //-------- G+ login start --------------
            case BSConfig.GOOGLE_PLUS_ACTIVITY_RESULT:
//                GoogleSignInResult lResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//                System.out.println("onActivityResult lResult = " + lResult);
//                handleGooglePlusResult(lResult);
                break;
            //-------- G+ login end --------------
        }
    }

    //-------- G+ login start --------------
//    private void handleGooglePlusResult(GoogleSignInResult aResult) {
//        System.out.println("aResult " + aResult.getStatus());
//        Toast.makeText(this, "" + aResult.getStatus(), Toast.LENGTH_SHORT).show();
//        if (aResult != null && aResult.isSuccess()) {
//            GoogleSignInAccount lAccount = aResult.getSignInAccount();
//
//            String lThirdIDFlag = lAccount.getId();
//            String lName = lAccount.getDisplayName();
//            String lEmail = lAccount.getEmail();
//            String lThirdToken = lAccount.getIdToken();
//            Uri lHeadImgUri = lAccount.getPhotoUrl();
//            String lHeadImgUrl = (lHeadImgUri == null) ? "" : lHeadImgUri.toString();
//
//            Map<String, String> lParam = new HashMap<String, String>();
//            lParam.put(BSModelForLogin.SP_FILED[2], lThirdToken);
//            lParam.put(BSModelForLogin.SP_FILED[3], lThirdIDFlag);
//            lParam.put(BSModelForLogin.SP_FILED[4], lHeadImgUrl);
//            lParam.put(BSModelForLogin.SP_FILED[5], lName);
//            lParam.put(BSModelForLogin.SP_FILED[9], lEmail);
//            mBSModelForLogin.inputInfo(BSModelForLogin.LOGIN_PLATFORMS[3], lParam);
//            mBSModelForLogin.thirdLogin();
//            mGoogleApiClient = null;
//            toWF();
//        }
//    }
    //-------- G+ login end --------------

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (mFacebookProfileTracker != null) {
//            mFacebookProfileTracker.removeObserver(this);
//            mFacebookProfileTracker = null;
//        }
//        if (mTwitterCallbackDefault != null) {
//            mTwitterCallbackDefault.removeObserver(this);
//            mTwitterCallbackDefault = null;
//        }
        if (mUILayoutMrg != null) {
            mUILayoutMrg.clear();
            mUILayoutMrg = null;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) { //系统返回键的点击事件
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            back();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void back() {
        if (mBSModelForLogin.getPosForReal() == 2) {
            mBSModelForLogin.setCurPos(1);
            mUILayoutMrg.visibility(new String[]{UILayoutConfigForLoginRegister.mIDs[1]}, View.VISIBLE);
            mUILayoutMrg.visibilityAnimationForDetailView(UILayoutConfigForLoginRegister.mIDs[2], View.VISIBLE, -1, new Integer[]{1});
            mUILayoutMrg.visibilityAnimationForDetailView(UILayoutConfigForLoginRegister.mIDs[2], View.GONE, -1, new Integer[]{4});
            mUILayoutMrg.visibilityAnimationForDetailView(UILayoutConfigForLoginRegister.mIDs[3], View.VISIBLE, -1, new Integer[]{0});
        } else {
            toWF();
        }
    }

    private void toWF() {
        if (BSConfig.JUMP_TO_LOGIN_SOURCE[0].equals(getIntent().getStringExtra("From"))) {
            ApplicationUtil.openActivity(this, BSConfig.mCommandIDs[24], null);
        }
        if (BSConfig.JUMP_TO_LOGIN_SOURCE[1].equals(getIntent().getStringExtra("From"))) {
            if (!TextUtils.isEmpty(mBSModelForLogin.getSP(BSModelForLogin.SP_FILED[1]))) {//登陆成功
                ApplicationUtil.openActivity(this, BSConfig.mCommandIDs[26], null);
            } else {//点击返回
                ApplicationUtil.openActivity(this, BSConfig.mCommandIDs[24], null);
            }
        }
        if (BSConfig.JUMP_TO_LOGIN_SOURCE[2].equals(getIntent().getStringExtra("From"))) {
            // 直接finish 跳转 过去无法拿到文章id
        }
        finish();
    }

    @Override
    public void clear() {

    }
}
