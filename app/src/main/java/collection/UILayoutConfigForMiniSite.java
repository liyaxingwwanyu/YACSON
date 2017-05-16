package collection;

import java.util.Arrays;
import java.util.List;

import Z_MajorStructure.contrler.UILayoutConfig;
import Z_MajorStructure.contrler.UILayoutConfigDefault;

/**
 * Created by Administrator on 2016/4/17.
 */
public class UILayoutConfigForMiniSite extends UILayoutConfigDefault {
    public static final String mCtlIDs[] = {"top.coolook.defaultui.FontSizeCtl", "top.coolook.defaultui.MiniSiteCtl", "top.coolook.defaultui.ClickCtl", "top.coolook.defaultui.ClickCtl",
            "top.coolook.defaultui.DefaultImgCtl", "top.coolook.admanager.MiniSiteADCtl", "top.coolook.comments.MiniSiteShardCtl", "top.coolook.comments.MiniSiteRelatedArticlesCtl",
            "top.coolook.comments.MiniSiteCommentsLoadMoreCtl", "top.coolook.comments.MinSiteOperatingBoxCtl", "top.coolook.comments.MiniSiteCommentsNewestCtl"
            , "top.coolook.comments.MiniSiteCommentsHeadCtl", "top.coolook.comments.MiniSiteCommentsGroupCtl", "top.coolook.defaultui.MiniSiteTitleCtl",
            "top.coolook.comments.MiniSiteTitleSelectBrowseCtl", "top.coolook.comments.MiniSiteSourceBrowseCtl", "top.coolook.defaultui.MiniSiteBottomFunctionCtl",
            "top.coolook.defaultui.MiniSiteQuizCtl", "top.coolook.defaultui.MinisiteLoadCtl"
    };//0-19
    //控件(单元)
    public static final String mIDs[] = {"FontSize", "MiniSite", "BackLockScreenBtn", "BottomBtnCtl", "DefaultImgCtl", "MiniSiteAD", "MiniSiteShard", "MiniSiteRelatedArticles", "MiniSiteCommentsLoadMore",//8
            "MinSiteOperatingBox", "MiniSiteCommentsNewest", "MiniSiteCommentsHead", "MiniSiteCommentsGroup", "MiniSiteTitle", "MiniSiteTitleSelectBrowse", "MiniSiteSourceBrowse", "MiniSiteBottomFunction",//15
            "MiniSiteQuiz", "minisiteJumpHide"
    };
    public static final List<String> mIDsForList = Arrays.asList(mIDs);
    //子控件
    public static final String mDetailViewIDs[][] = {
            {"smallfont_iv", "middlefont_iv", "bigfont_iv", "minisite_bg", "titlebar_to_fontSize"},
            {"wv_minisite", "webview_conver_layers"},
            {"R.id.wf_scroll_top_refresh_btn_iv"},
            {"zuoyoo_minisite_bottom_btn_game_rl"},
            {"zuoyoo_minisite_default_iv"},
            {"ad_img", "ad_text", "ad_tip", "ad_promotion_iv", "ad_install_iv", "ad_choices_iv", "wf_ad_top_line"},
            {"mini_site_third_share_facebook_iv", "mini_site_third_share_twitter_iv", "mini_site_third_share_googleplus_iv", "mini_site_third_share_system_iv"},
            {"minisite_item_for_list_type_ll"},
            {"mini_site_articles_comments_load_more"},
            {"mini_site_operating_box_input_tv", "mini_site_operating_box_comments_iv", "mini_site_operating_box_comments_number", "mini_site_operating_box_thumb_up", "mini_site_operating_box_collection_iv", "mini_site_operating_box_home_iv"},
            {"mini_site_articles_comments_newest_item_add_layout", "mini_site_articles_comments_newest_add_layout","mini_site_articles_comments_newest_item_add_bottom_tv"},
            {"mini_site_articles_comments_head_title", "mini_site_articles_comments_radio_group", "mini_site_articles_comments_hot_radio", "mini_site_articles_comments_news_radio"},
            {"mini_site_group_scroll_view"},
            {"mini_site_title_bar_bg_iv", "iv_minisiteBack", "minisite_title_share_icon_more_iv", "minisite_title_share_icon_sys_iv", "minisite_title_quiz_iv"},
            {"mini_site_title_back_iv", "mini_site_title_select_checkbox","mini_site_title_select_tv", "mini_site_title_function_iv", "mini_site_title_select_rl"},
            {"mini_site_source_web_view", "mini_site_source_web_loading_to_coolook_ll", "mini_site_source_web_loading_ll"},
            {"mini_site_bottom_function_quiz_rl", "mini_site_bottom_function_font_rl", "mini_site_bottom_function_shard_rl", "mini_site_bottom_function_cancel_bt", "mini_site_bottom_function_other_ll", "mini_site_bottom_function_rl", "mini_site_bottom_function_look_quiz_rl"},
            {"mini_site_quiz_btn", "mini_site_look_quiz_btn"},
            {"mini_site_error_layout", "mini_site_error_img", "mini_site_error_text", "mini_site_error_bar"}
    };
    //子控件的图片
    public static final String mDetailDrawableIDs[][] = {
            {}, {}, {}, {}, {"minisite_default_img", "networkerror"}, {}, {},
            {"zuoyoo_wf_recycerview_item_video_icon", "wf_recycerview_item_game_icon", "wf_recycerview_item_h5_icon", "wf_recycerview_item_predictions_icon"}, {},
            {"mini_site_operating_box_thumb_up_default", "mini_site_operating_box_thumb_up_click", "mini_site_operating_box_collection_default", "mini_site_operating_box_collection_click", "mini_site_operating_box_comments_success"},
            {}, {}, {}, {}, {}, {}, {}, {}
    };
    //子控件的字符串
    public static final String mStringIDs[][] = {
            {}, {"NO_NETWORK"}, {}, {}, {}, {}, {}, {}, {"More", "NoMore"},
            {"NOT_LOGIN", "INFORMATION_IS_EMPTY", "COMMENT_ON_FAILURE", "THUMB_UP_SUCCESS", "CANCEL_THUMB_UP_SUCCESS", "CANCEL_THUMB_UP_FAILURE",
                    "THUMB_UP_FAILURE", "COLLECTION_SUCCESS", "COLLECTION_FAILURE", "COMMENT_ON_SUCCESS","CANCEL_COLLECTION_SUCCESS","CANCEL_COLLECTION_FAILURE"},
            {}, {}, {}, {}, {}, {}, {}, {}, {}
    };
    //单元状态
    public static final String mState[] = {"HideFontSize", "ChangeWebViewFontSize", "LoadError", "HideTitleBar", "ShowTitleBar", "PauseVideo", "Back", "Complete",//7
            "NewIntentLoad", "ReloadUrl", "ShowAD", "HideAD", UILayoutConfig.mState[0], "ShardFacebook", "ShardTwitter", "ShardGooglePlus", "ShardSystem", "LoadRelatedUrl",//17
            "SubmitComment", "ToComment", "ThumbUp", "collection", "NewComment", "HotComment", "ScrollBottom", "ToQuiz", "ClickFount", "DeleteComment", "ToWeb", "ToCoolook",//29
            "SourceWebFinished", "SwitchFunction", "HideFunction", "ToLookQuiz", "MoveUp", "MoveDown","ToHome","NoComment","CommentSuccess "};//0-38
    public static final List<String> mStateForList = Arrays.asList(mState);

    //子控件Item
    private static final String mItemForDetailViewsIDs[] = {
            "wf_item_for_list_type_version_one",
            "login_register_user_info_nickname_dialog_item",
            "mini_site_articles_comments_newest_item",
            "mini_site_articles_comments_delete_dialog_item"
    };
    public static final String mItemForDetailViewsIDsForIndex[] = {
            mIDs[7] + mSplit + mDetailViewIDs[7][0],
            mIDs[9] + mSplit + mDetailViewIDs[9][0],
            mIDs[10] + mSplit + mDetailViewIDs[10][0],
            mIDs[10] + mSplit + mDetailViewIDs[10][1]
    };
    public static final List<String> mItemForDetailViewsIDsForIndexForList = Arrays.asList(mItemForDetailViewsIDsForIndex);
    public static final String mItemDetailViewsForDetailViewsIDs[][] = {
            {"wf_item_for_list_type_iv", "wf_item_for_list_type_tv", "zuoyoo_wf_recycerview_item_abstracts", "zuoyoo_wf_recycerview_item_publication_name", "wf_item_for_list_group_ll", "wf_item_for_list_group_one_iv", "wf_item_for_list_group_two_iv", "wf_item_for_list_group_three_iv", "zuoyoo_wf_recycerview_item_video_icon_iv", "zuoyoo_wf_recycerview_item_abstracts_divider"},
            {"login_register_user_info_nickname_dialog_item_title_tv", "login_register_user_info_nickname_dialog_item_content_et",
                    "login_register_user_info_nickname_dialog_item_content_tv", "login_register_user_info_nickname_dialog_item_cancel_tv",
                    "login_register_user_info_nickname_dialog_item_submit_tv"},
            {"mini_site_articles_comments_head_iv", "mini_site_articles_comments_nickname_tv", "mini_site_articles_comments_time_tv", "mini_site_articles_comments_content_tv", "mini_site_articles_comments_delete_tv"},
            {"mini_site_articles_comments_delete_content_tv", "mini_site_articles_comments_delete_cancel_tv", "mini_site_articles_comments_delete_determine_tv"}
    };
    public static final String mItemDetailViewsForDetailImageIDs[][] = {
            {}, {"personal_center_modify_password_dialog_item_style"}, {}, {"personal_center_modify_password_dialog_item_style"}
    };

    @Override
    public String[] getIDs() {
        return mIDs;
    }

    @Override
    public String[] getItemForDetailViewsIDsForIndex() {
        return mItemForDetailViewsIDsForIndex;
    }

    @Override
    public List<String> getIDsForList() {
        return mIDsForList;
    }

    @Override
    public String[][] getDetailViewIDs() {
        return mDetailViewIDs;
    }

    @Override
    public String[] getCtlIDs() {
        return mCtlIDs;
    }
}
