package login;

import java.util.Arrays;
import java.util.List;

import collection.UILayoutConfigForMiniSite;
import Z_MajorStructure.contrler.UILayoutConfigDefault;


/**
 * Created by Administrator on 2016/5/25.
 */
public class UILayoutConfigForLoginRegister extends UILayoutConfigDefault {

    public static final String mCtlIDs[] = {"login.LoginInputInfoCtl",
            "top.coolook.defaultui.ClickCtl",
            "login.LoginInputTitleBarCtl",
            "login.LoginRegisterThirdLoginCtl"};
    //控件(单元)
    public static final String mIDs[] = {"LoginInputInfo", "ForgetPassword",
            "LoginInputTitleBar",
            "LoginRegisterThirdLogin"};
    public static final List<String> mIDsForList = Arrays.asList(mIDs);
    //子控件
    public static final String mDetailViewIDs[][] = {
            {"login_register_input_msg_info_tv",
                    "login_register_mail_input_iv",
                    "login_register_mail_input_et",
                    "login_register_mail_input_iv_line",
                    "login_register_password_input_iv",
                    "login_register_password_input_et",
                    "login_register_password_input_iv_line",
                    "login_register_login_btn", "login_register_login_btn_wait_iv",
                    "login_register_mail_del", "login_register_password_del"},
            {"login_register_forget_password_tv"},
            {"login_register_title_bar_back_btn", "login_register_title_bar_radio_group",
                    "login_register_title_bar_login_radio_btn", "login_register_title_bar_register_radio_btn",
                    "login_register_title_bar_forget_password_tv"},
            {"login_register_third_login_btn_parent_ll", "login_register_third_login_btn_fb_iv",
                    "login_register_third_login_btn_tw_iv", "login_register_third_login_btn_gp_iv", "login_register_third_login_above_ll"}
    };
    //子控件的图片
    public static final String mDetailDrawableIDs[][] = {
            {"login_register_email_icon", "login_register_password_icon"},
            {}, {}, {}
    };
    //子控件有状态的的图片
    public static final String mDetailStateDrawableIDs[][] = {
            {}, {}, {}, {}
    };
    //子控件的字符串
    public static final String mStringIDs[][] = {
            {"PLEASE_ENTER_EMAIL_ADDRESS", "PLEASE_ENTER_PASSWORD", "EMAIL_FORMAT_ERROR", "PASSWORD_FORMAT_ERROR",
                    "EMAIL_ADDRESS", "PASSWORD", "NEW_PASSWORD", "REGISTER", "EMAIL_EXIST", "ERROR", "LOGIN", "PASSWORD_ERROR",
                    "EMAIL_NOT_EXIST", "SEND_EMAIL"},
            {}, {}, {}
    };

    //单元状态
    public static final String mState[] = {UILayoutConfigForMiniSite.mState[12], "RegisterSuccess", "LoginSuccess", "ToLogin", "ToRegister", "FacebookLogin", "TwitterLogin", "GooglePlusLogin", "ShowThirdAbove", "HindThirdAbove", "Back"};//0-10

    public static final List<String> mStateForList = Arrays.asList(mState);

    @Override
    protected String[] getIDs() {
        return mIDs;
    }

    @Override
    protected String[] getItemForDetailViewsIDsForIndex() {
        return null;
    }

    @Override
    protected List<String> getIDsForList() {
        return mIDsForList;
    }

    @Override
    protected String[][] getDetailViewIDs() {
        return mDetailViewIDs;
    }

    @Override
    protected String[] getCtlIDs() {
        return mCtlIDs;
    }
}