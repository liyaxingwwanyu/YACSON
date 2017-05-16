package Z_MajorStructure.contrler;

import java.util.Arrays;
import java.util.List;

/**
 * Created by sheenly on 16/3/7.
 */
public class UILayoutConfig extends UILayoutConfigDefault{
    public static final String mCtlIDs[] = {"top.coolook.defaultui.TimeCtl", "top.coolook.defaultui.DateCtl",
            "top.coolook.defaultframe.UICtlDefault", "top.coolook.defaultui.NewMsgCtl", "top.coolook.defaultui.NumPwdCtl",
            "top.coolook.ui.cycleslide.CycleSlideCtl", "top.coolook.ui.unlock.UnlockCircleCtl", "top.coolook.ui.unlock.UnlockBarCtl",
            "top.coolook.defaultui.TitleCtl", "top.coolook.defaultui.FuzzyCtl", "top.coolook.ui.ShortCutCircleCtl", "top.coolook.ui.FuzzyCtlForPullUp",
            "top.coolook.ui.ShortCutTianLongCtl", "top.coolook.ui.ShortCutBarCtl","top.coolook.ui.AbstractsCtl",
            "top.coolook.third.patternpwd.PatternPwdCtl", "top.coolook.ui.ScreensaverCtl","top.coolook.defaultui.TitleClickCtl"}; //
    //控件(单元) 0-17
    public static final String mIDs[] = {"Time", "Date", "Icon", "NewMsg", "NumPwd", "CycleSlide", "UnlockCircle", "UnlockBar", "Title", "Fuzzy",
            "SCCircle", "FuzzyForSC", "SCTianLong", "SCBar", "Abstracts", "PatternPwd", "Screensaver", "TitleClick"};
    public static final List<String> mIDsForList = Arrays.asList(mIDs);
    //子控件
    public static final String mDetailViewIDs[][] = {
            {"screen_show_time_image1", "screen_show_time_image2", "screen_show_time_image4", "screen_show_time_image5"},
            {"zuoyoo_date"},
            {"screen_show_short_cut_text_icon_view"},
            {"zuoyoo_shortcut_icon_newmsg_tv", "zuoyoo_shortcut_icon_newmsg_iv"},
            {"tv1", "lock_num_pwd1", "lock_num_pwd2", "lock_num_pwd3", "lock_num_pwd4", "lock_num_0",
                    "lock_num_1", "lock_num_2", "lock_num_3", "lock_num_4",
                    "lock_num_5", "lock_num_6", "lock_num_7", "lock_num_8",
                    "lock_num_9", "lock_num_del"},
            {"screen_show_viewPager", "screen_show_touch_left_arrowhead_iv", "screen_show_touch_right_arrowhead_iv"},
            {"zuoyoo_left_button", "zuoyoo_middle_button", "zuoyoo_right_button", "zuoyoo_screen_show_left_arrow_button_six", "zuoyoo_screen_show_right_arrow_button_six"},
            {"zuoyoo_unlock_item_left_layout", "zuoyoo_unlock_item_left_tv", "zuoyoo_unlock_item_right_layout", "zuoyoo_unlock_item_right_tv"},
            {"screen_show_title"}, {"screen_show_fuzzy_img_six_layout"}, {"FirGridview", "SeGridview"}, {"screen_show_fuzzy_img_two_layout"},
            {"gridview0_for_tl", "screen_show_slidingDrawer_for_tl", "screen_show_handle_for_tl", "screen_show_content_for_tl"},
            {"screen_show_sc_seven_first_grid_view", "screen_show_sc_seven_second_grid_view", "screen_show_sc_seven_third_grid_view"},
            {"screen_show_abstracts_tv", "screen_show_abstracts_rl"},
            {"zuoyoo_pwd","tvInfo","lpv_screenlock","gesture_pwd_show_checkbox","screenShow_cancel"},
            {"main_screensaver_add_view_img"},
            {"screen_show_title"}
    };
    //子控件的图片
    public static final String mDetailDrawableIDs[][] = {
            {"n0w", "n1w", "n2w", "n3w", "n4w", "n5w", "n6w", "n7w", "n8w", "n9w"},
            {},
            {"screen_show_short_cut_text_icon"},
            {"zuoyoo_shortcut_icon_newmsg_n", "zuoyoo_shortcut_icon_newmsg_1",
                    "zuoyoo_shortcut_icon_newmsg_2", "zuoyoo_shortcut_icon_newmsg_3",
                    "zuoyoo_shortcut_icon_newmsg_4", "zuoyoo_shortcut_icon_newmsg_5",
                    "zuoyoo_shortcut_icon_newmsg_6", "zuoyoo_shortcut_icon_newmsg_7",
                    "zuoyoo_shortcut_icon_newmsg_8", "zuoyoo_shortcut_icon_newmsg_9"},
            {"default_point", "point"},
            {"screen_bg"},
            {"international_screen_show_middle_default_button", "international_screen_show_middle_click_button"},
            {}, {}, {}, {
            "screen_show_search_default_flight",
            "screen_show_counter_default_button",
            "screen_show_camera_default_button",
            "screen_show_tel_default_button",
            "screen_show_weixin_default_button",
            "screen_show_search_default_flight_text",
            "screen_show_game_default_button_text",
            "screen_show_search_onclick_flight",
            "screen_show_counter_onclick_button",
            "screen_show_camera_onclick_button",
            "screen_show_tel_onclick_button",
            "screen_show_weixin_onclick_button",
            "screen_show_search_onclick_flight_text",
            "screen_show_game_onclick_button_text",

            "screen_show_wallpager_default_button_text",
            "screen_show_alarm_default_button_text",
            "screen_show_custom_default_button_one",
            "screen_show_custom_default_button_two",
            "screen_show_custom_default_button_three",
            "screen_show_custom_default_button_four",
            "screen_show_custom_default_button_five",
            "screen_show_wallpager_onclick_button_text",
            "screen_show_alarm_onclick_button_text",
            "screen_show_custom_onclick_button_one",
            "screen_show_custom_onclick_button_two",
            "screen_show_custom_onclick_button_three",
            "screen_show_custom_onclick_button_four",
            "screen_show_custom_onclick_button_five",

            "zuoyoo_shortcut_icon_newmsg_n", "zuoyoo_shortcut_icon_newmsg_1",
            "zuoyoo_shortcut_icon_newmsg_2", "zuoyoo_shortcut_icon_newmsg_3",
            "zuoyoo_shortcut_icon_newmsg_4", "zuoyoo_shortcut_icon_newmsg_5",
            "zuoyoo_shortcut_icon_newmsg_6", "zuoyoo_shortcut_icon_newmsg_7",
            "zuoyoo_shortcut_icon_newmsg_8", "zuoyoo_shortcut_icon_newmsg_9"}, {},
            {"screen_set_wallpaper_default_button_for_tl", "screen_show_tel_default_button_for_tl", "screen_show_clock_default_flight_for_tl", "screen_show_camera_default_button_for_tl",
                    "screen_set_wallpaper_default_button_for_tl", "screen_show_tel_default_button_for_tl", "screen_show_clock_default_flight_for_tl",
                    "screen_show_camera_default_button_for_tl", "screen_show_short_cut_open_icon_for_tl", "screen_show_short_cut_close_icon_for_tl"},
            {"screen_show_camera_default_button_seven",
                    "screen_show_counter_default_button_seven",
                    "screen_show_wallpager_default_button_seven",
                    "screen_show_msg_default_button_seven",
                    "screen_show_tel_default_button_seven",
                    "screen_show_camera_onclick_button_seven",
                    "screen_show_counter_onclick_button_seven",
                    "screen_show_wallpager_onclick_button_seven",
                    "screen_show_msg_onclick_button_seven",
                    "screen_show_tel_onclick_button_seven",

                    "screen_show_flight_default_button_seven",
                    "screen_show_alarm_default_button_seven",
                    "screen_show_search_default_button_seven",
                    "screen_show_game_default_button_seven",
                    "screen_show_flight_onclick_button_seven",
                    "screen_show_alarm_onclick_button_seven",
                    "screen_show_search_onclick_button_seven",
                    "screen_show_game_onclick_button_seven",

                    "screen_show_custom_default_button_seven_one",
                    "screen_show_custom_default_button_seven_two",
                    "screen_show_custom_default_button_seven_three",
                    "screen_show_custom_default_button_seven_four",
                    "screen_show_custom_default_button_seven_five",
                    "screen_show_custom_onclick_button_seven_one",
                    "screen_show_custom_onclick_button_seven_two",
                    "screen_show_custom_onclick_button_seven_three",
                    "screen_show_custom_onclick_button_seven_four",
                    "screen_show_custom_onclick_button_seven_five",

                    "zuoyoo_shortcut_icon_newmsg_n", "zuoyoo_shortcut_icon_newmsg_1",
                    "zuoyoo_shortcut_icon_newmsg_2", "zuoyoo_shortcut_icon_newmsg_3",
                    "zuoyoo_shortcut_icon_newmsg_4", "zuoyoo_shortcut_icon_newmsg_5",
                    "zuoyoo_shortcut_icon_newmsg_6", "zuoyoo_shortcut_icon_newmsg_7",
                    "zuoyoo_shortcut_icon_newmsg_8", "zuoyoo_shortcut_icon_newmsg_9"},{},{},{}, {}

    };
    //子控件的字符串
    public static final String mStringIDs[][] = {{}, {}, {}, {},
            {"WRITE_FOURPWD", "FOUR_ENTER_PASSWORD", "PASSWORD_ERROR"},
            {}, {}, {}, {}, {}, {"Flashlight", "Calculator",
            "Camera", "Phone", "Messages", "Search", "Game", "Wallpaper",
            "Clock", "Customize_one", "Customize_two", "Customize_three",
            "Customize_four", "Customize_five"}, {},
            {"Calculator", "Phone", "Clock", "Camera"},
            {"Camera", "Calculator", "Wallpaper", "Messages", "Phone",
                    "Flashlight", "Clock", "Search", "Game",
                    "Customize_one", "Customize_two", "Customize_three", "Customize_four", "Customize_five"},{},
            {"DRAW_PATTERN", "PIC_ERROR", "DRAW_PIC"},{}, {}
    };

    //子控件Item
    private static final String mItemForDetailViewsIDs[] = {
            "screen_show_viewpager_item", "picture_item", "picture_item", "picture_item_for_tl", "picture_item_seven"
    };
    public static final String mItemForDetailViewsIDsForIndex[] = {
            mIDs[5] + mSplit + mDetailViewIDs[5][0], mIDs[10] + mSplit + mDetailViewIDs[10][0], mIDs[10] + mSplit + mDetailViewIDs[10][1],
            mIDs[12] + mSplit + mDetailViewIDs[12][0], mIDs[13] + mSplit + mDetailViewIDs[13][0]
    };
    public static final List<String> mItemForDetailViewsIDsForIndexForList = Arrays.asList(mItemForDetailViewsIDsForIndex);
    public static final String mItemDetailViewsForDetailViewsIDs[][] = {
            {"screen_show_viewpager_item_image"}, {"title", "image", "zuoyoo_shortcut_newmsg_iv_two"}, {"title", "image", "zuoyoo_shortcut_newmsg_iv_two"},
            {"title_for_tl", "image_for_tl"}, {"title_seven", "image_seven", "zuoyoo_shortcut_newmsg_iv"}
    };
    public static final String mItemDetailViewsForDetailImageIDs[][] = {
            {"defaultImgId"}, {}, {}, {}, {}
    };
    //单元状态 0-7
    public static final String mState[] = {"complete", "cancel", "bottom_up", "all_screen_up", "sensor_down", "show", "screen_down", "screen_up", "ToMiniSite"};//0-8

    public static final String mCases[] = {"India", "Circle", "Bar", "TianLong"};
    private String mCurCase = mCases[0];

    public void setCurCase(String aCurCase) {
        mCurCase = aCurCase;
    }

    public String getCurCase() {
        return mCurCase;
    }

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
