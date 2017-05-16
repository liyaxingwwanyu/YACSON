package login;

import java.util.Arrays;
import java.util.List;

import collection.UILayoutConfigForMiniSite;
import Z_MajorStructure.contrler.UILayoutConfigDefault;


/**
 * Created by Administrator on 2016/5/28.
 */
public class UILayoutConfigForPersonalCenter extends UILayoutConfigDefault {

    public static final String mCtlIDs[] = {"login.ClickDialogCtl", "login.PersonalCenterTitleBarCtl",
            "login.PersonalCenterHeadPortraitCtl", "login.PersonalCenterUserInfoCtl",
            "login.PersonalCenterModifyPasswordCtl"};
    //控件(单元)
    public static final String mIDs[] = {"ExitLogin", "PersonalCenterTitleBar", "PersonalCenterHeadPortrait", "PersonalCenterUserInfo",
            "PersonalCenterModifyPassword"};
    public static final List<String> mIDsForList = Arrays.asList(mIDs);
    //子控件
    public static final String mDetailViewIDs[][] = {
            {"personal_center_exit_login_exit_btn"},
            {"personal_center_title_bar_back_btn", "personal_center_title_bar_content_tv"},
            {"personal_center_head_portrait_iv"},
            {"personal_center_user_info_nickname_rl", "personal_center_user_info_nickname_tv", "personal_center_user_info_nickname_iv", "personal_center_user_info_nickname_content_tv",
                    "personal_center_user_info_gender_rl", "personal_center_user_info_gender_tv", "personal_center_user_info_gender_rg", "personal_center_user_info_gender_boy_rb", "personal_center_user_info_gender_girl_rb",
                    "personal_center_user_info_account_rl", "personal_center_user_info_account_tv", "personal_center_user_info_account_iv", "personal_center_user_info_account_content_tv",
                    "personal_center_user_info_modify_password_rl", "personal_center_user_info_modify_password_tv", "personal_center_user_info_modify_password_iv"},
            {"personal_center_modify_password_original_password_et", "personal_center_modify_password_new_password_et", "personal_center_modify_password_determine_btn"}
    };
    //子控件的图片
    public static final String mDetailDrawableIDs[][] = {{}, {},
            {"wf_menu_version_one_user_head_login_click"},
            {"personal_center_user_info_account_fb_icon", "personal_center_user_info_account_tw_icon", "personal_center_user_info_account_gp_icon",
                    "personal_center_user_info_account_lock_icon", "personal_center_user_info_account_unlock_icon"},
            {}
    };
    //子控件有状态的的图片
    public static final String mDetailStateDrawableIDs[][] = {{}, {}, {}, {}, {}};
    //子控件的字符串
    public static final String mStringIDs[][] = {
            {"confirm_exit", "confirm_exit_login_current_account"},
            {"MY_COOLOOK", "MODIFY_PASSWORD"}, {}, {"EMAIL_HAS_BEEN_SENT_EXAMINE", "FAILURE"},
            {"PLEASE_ENTER_PASSWORD", "PASSWORD_FORMAT_ERROR", "SUCCESS", "ACCOUNT_NOT_EXIST", "OLD_PASSWORD_ERROR", "ERROR", "OLD_PASSWORD_CAN_NOT_EQUAL_NEW_PASSWORD"}
    };

    //子控件Item
    private static final String mItemForDetailViewsIDs[] = {
            "personal_center_modify_password_dialog_item", "personal_center_exit_login_dialog_item",
            "login_register_user_info_nickname_dialog_item", "login_register_head_portrait_dialog_item"
    };
    public static final String mItemForDetailViewsIDsForIndex[] = {
            mIDs[4] + mSplit + mDetailViewIDs[4][2], mIDs[0] + mSplit + mDetailViewIDs[0][0],
            mIDs[3] + mSplit + mDetailViewIDs[3][0], mIDs[2] + mSplit + mDetailViewIDs[2][0]
    };
    public static final List<String> mItemForDetailViewsIDsForIndexForList = Arrays.asList(mItemForDetailViewsIDsForIndex);
    public static final String mItemDetailViewsForDetailViewsIDs[][] = {
            {"personal_center_modify_password_dialog_item_tv"},
            {"personal_center_exit_login_dialog_item_title_tv", "personal_center_exit_login_dialog_item_content_tv",
                    "personal_center_exit_login_dialog_item_cancel_tv", "personal_center_exit_login_dialog_item_complete_tv"},
            {"login_register_user_info_nickname_dialog_item_title_tv", "login_register_user_info_nickname_dialog_item_content_et",
                    "login_register_user_info_nickname_dialog_item_content_tv", "login_register_user_info_nickname_dialog_item_cancel_tv",
                    "login_register_user_info_nickname_dialog_item_submit_tv",
                    "login_register_user_info_nickname_dialog_item_ll", "login_register_user_info_email_dialog_item_ll", "login_register_user_info_email_submit_dialog_item_tv", "login_register_user_info_email_content_dialog_item_tv"},
            {"login_register_head_portrait_dialog_item_camera_ll", "login_register_head_portrait_dialog_item_camera_tv",
                    "login_register_head_portrait_dialog_item_gallery_ll", "login_register_head_portrait_dialog_item_gallery_tv",
                    "login_register_head_portrait_dialog_item_cancel_ll", "login_register_head_portrait_dialog_item_cancel_tv"}

    };
    public static final String mItemDetailViewsForDetailImageIDs[][] = {
            {"personal_center_modify_password_dialog_item_style"}, {"personal_center_modify_password_dialog_item_style"},
            {"personal_center_modify_password_dialog_item_style"}, {"personal_center_modify_password_dialog_item_style"}
    };

    //单元状态
    public static final String mState[] = {UILayoutConfigForMiniSite.mState[12], "ToModifyPassword", "SendEmail", "UploadHeadPortrait", "ToTakingPictures", "ToReadGallery", "ToPersonalCenter"};

    public static final List<String> mStateForList = Arrays.asList(mState);

    @Override
    protected String[] getIDs() {
        return mIDs;
    }

    @Override
    protected String[] getItemForDetailViewsIDsForIndex() {
        return mItemForDetailViewsIDsForIndex;
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
