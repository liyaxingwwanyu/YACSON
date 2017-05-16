package util;

import android.content.Context;

import com.ya.cson.R;

import view.Location;

/**
 * 常量类 1.0.1
 */
public class Consts extends ConstsFather  {

    public Consts(Context location) {
    }
    /**
     * 快捷键--电话
     */
    public static final int SHORT_KEY_TEL = 34;
    /**
     * 快捷键--短信
     */
    public static final int SHORT_KEY_MSG = 35;
    /**
     * 快捷键--照相机
     */
    public static final int SHORT_KEY_CAMERA = 36;
    /**
     * 快捷键--手电筒
     */
    public static final int SHORT_KEY_FLIGHT = 37;
    /**
     * 快捷键--微信
     */
    public static final int SHORT_KEY_WETXIN = 38;
    /**
     * 快捷键--搜索
     */
    public static final int SHORT_KEY_SEARCH = 39;

    public static final String ROOT_PATH = android.os.Environment
            .getExternalStorageDirectory().toString()
            + "/cn.bechtech.left_right_lock";
    public static final String LEFT_KUO = "(";
    public static final String RIGHT_KUO = ")";
    public static final String INTENT_TEL_START = "Intent.action.my.tel.start";
    public static final String INTENT_TEL_END = "Intent.action.my.tel.end";
    public static final String INTENT_CHANGE_TEL_START = "Intent.action.my.changetel.start";
    public static final String INTENT_BIND_TEL_START = "Intent.action.my.bindtel.start";
    public static final String INTENT_BIND_TEL_END = "Intent.action.my.bindtel.end";
    public static final String INTENT_CHANGE_TEL_END = "Intent.action.my.changetel.end";

    /**
     * 微信分享 如果使用此常量，请设置userComment参数（SHARE_SUCCESS分享成功、或者SHARE_FAILED分享失败、
     * 或者SHARE_DISMISS分享取消）
     */
    public static final int SHARE_WEIXIN = 15;
    /**
     * 新浪微博分享 如果使用此常量，请设置userComment参数（SHARE_SUCCESS分享成功、或者SHARE_FAILED分享失败、
     * 或者SHARE_DISMISS分享取消）
     */
    public static final int SHARE_SINAWEIBO = 16;
    /**
     * 腾讯微博分享 如果使用此常量，请设置userComment参数（SHARE_SUCCESS分享成功、或者SHARE_FAILED分享失败、
     * 或者SHARE_DISMISS分享取消）
     */
    public static final int SHARE_TENCENTWEIBO = 17;
    /**
     * QQ空间分享 如果使用此常量，请设置userComment参数（SHARE_SUCCESS分享成功、或者SHARE_FAILED分享失败、
     * 或者SHARE_DISMISS分享取消）
     */
    /**
     * 朋友圈分享 如果使用此常量，请设置userComment参数（SHARE_SUCCESS分享成功、或者SHARE_FAILED分享失败、
     * 或者SHARE_DISMISS分享取消）
     */
    public static final int SHARE_PENGYOUQUAN = 27;

    public static final String SAVE_PIC = Location.instance.getResources()
            .getString(R.string.save_pic);

    public static final String NO_SDCARD = Location.instance.getResources()
            .getString(R.string.no_sdcard);

    public static final String DOWNLOAD_FAILED = Location.instance
            .getResources().getString(R.string.download_failed);

    public static final String PIC_ERROR = Location.instance.getResources()
            .getString(R.string.pic_error);

    public static final String DRAW_PIC = Location.instance.getResources()
            .getString(R.string.draw_pic);

    public static final String WRITE_FOURPWD = Location.instance.getResources()
            .getString(R.string.input_four_pwd);

    public static final String DRAW_PATTERN = Location.instance.getResources()
            .getString(R.string.draw_pattern);

    public static final String NO_NETWORK = Location.instance.getResources()
            .getString(R.string.no_network);

    public static final String VERSION_NAME = Location.instance.getResources()
            .getString(R.string.version_name);

    public static final String LOCK_WAY_STATE = "lockstate";
    public static final String CONFIRM_OLD_PIC = Location.instance
            .getResources().getString(R.string.confirm_old_pic);
    public static final String CONFIRM_AGAIN = Location.instance.getResources()
            .getString(R.string.confirm_again);

    public static final String DRAW_INCONSIST = Location.instance
            .getResources().getString(R.string.draw_inconsist);
    public static final String GEST_CLEAR = Location.instance.getResources()
            .getString(R.string.gest_clear);
    public static final String ANSWER_DRAWPOINTS = Location.instance
            .getResources().getString(R.string.answer_drawpoints);
    public static final String MAKESURE_DRAWPOINTS = Location.instance
            .getResources().getString(R.string.makesure_drawpoints);
    public static final String NEW_DRAWPOINTS = Location.instance
            .getResources().getString(R.string.new_drawpoints);

    public static final String IDENT_FAILED = Location.instance.getResources()
            .getString(R.string.ident_failed);
    public static final String KEEPIN_MIND = Location.instance.getResources()
            .getString(R.string.keepin_mind);

    public static final String REPLACE_PIC = Location.instance.getResources()
            .getString(R.string.replace_pic);
    public static final String UNLOCK_PIC = Location.instance.getResources()
            .getString(R.string.unlock_pic);
    public static final String UNLOCK_PASSWORD = Location.instance
            .getResources().getString(R.string.unlock_password);
    public static final String MODIFIED_PASSWORD = Location.instance
            .getResources().getString(R.string.modified_password);
    public static final String FOUR_ENTER_PASSWORD = Location.instance
            .getResources().getString(R.string.input_four_pwd);

    public static final String PASSWORD_ERROR = Location.instance
            .getResources().getString(R.string.password_error);
    public static final String SET_PASSWORD_SUCCESS = Location.instance
            .getResources().getString(R.string.set_password_success);
    public static final String SET_INCONSIST = Location.instance.getResources()
            .getString(R.string.set_inconsist);
    public static final String MAKE_SURE_OLD = Location.instance.getResources()
            .getString(R.string.make_sure_old);

    public static final String IS_ON_DOWNLOADING = Location.instance
            .getResources().getString(R.string.is_on_downloading);
    public static final String IS_TITLER = Location.instance.getResources()
            .getString(R.string.is_titler);
    public static final String IS_CATEGORY = Location.instance.getResources()
            .getString(R.string.is_category);
    public static final String RIGHT_SLIDE_TO_UNLOCK = Location.instance
            .getResources().getString(R.string.right_slide_to_unlock);
    public static final String LEFT_SLIDE_TO_UNLOCK = Location.instance
            .getResources().getString(R.string.left_slide_to_unlock);

    public static final String REFRESH_DATA_FAILED = Location.instance
            .getResources().getString(R.string.refresh_data_failed);
    public static final String SUBMIT = Location.instance.getResources()
            .getString(R.string.submit);

    public static final String IS_APP_NOT_STALL = Location.instance
            .getResources().getString(R.string.is_app_not_stall);
    public static final String NEED_RESTALL = Location.instance.getResources()
            .getString(R.string.need_restall);

    public static final String DRAW_POINTS = Location.instance.getResources()
            .getString(R.string.draw_points);

    public static final String APP_STATES_DOWN = Location.instance
            .getResources().getString(R.string.download);
    public static final String APP_STATES_DOWNLOADING = Location.instance
            .getResources().getString(R.string.app_states_downloading);
    public static final String APP_STATES_OPEN = Location.instance
            .getResources().getString(R.string.app_states_open);
    public static final String LOCKING_MODE = Location.instance.getResources()
            .getString(R.string.locking_mode);
    public static final String LOCK_STYLE_SCHEME_ONE = Location.instance
            .getResources().getString(R.string.lock_style_scheme_one);
    public static final String LOCK_STYLE_SCHEME_TWO = Location.instance
            .getResources().getString(R.string.lock_style_scheme_two);
    public static final String LOCK_STYLE_USED = Location.instance
            .getResources().getString(R.string.lock_style_used);
    public static final String LOCK_STYLE_USE = Location.instance
            .getResources().getString(R.string.lock_style_use);
    public static final String SEARCH_CLEAR_HISTORY = Location.instance
            .getResources().getString(R.string.search_clear_history);
    public static final String SEARCH_ALL = Location.instance.getResources()
            .getString(R.string.search_all);
    public static final String SEARCH_NETWORK = Location.instance
            .getResources().getString(R.string.search_network);
    public static final String SEARCH_APP = Location.instance.getResources()
            .getString(R.string.search_app);
    public static final String MORE_RECOMMENDATION = Location.instance
            .getResources().getString(R.string.more_recommendation);
    public static final String KUAIJIEJIAN_TITLE = Location.instance
            .getResources().getString(R.string.kuaijiejian_title);
    public static final String GAME_TITLE = Location.instance.getResources()
            .getString(R.string.game_title);
    public static final String TOPGAME_TITLE = Location.instance.getResources()
            .getString(R.string.topgame_title);
    public static final String BEHINDGAME_TITLE = Location.instance
            .getResources().getString(R.string.behindgame_title);
    public static final String INDIA_THIRD_TITLE = Location.instance
            .getResources().getString(R.string.india_third_title);
    public static final String KUAIJIEJIAN = Location.instance.getResources()
            .getString(R.string.kuaijiejian);
    public static final String SETTING_CUSTOM_SC_EDIT = Location.instance
            .getResources().getString(R.string.setting_custom_sc_edit);
    public static final String COUNTRY_SELECT_TITLE = Location.instance
            .getResources().getString(R.string.country_select_title);
    public static final String TOPICS_SELECT_TITLE = Location.instance
            .getResources().getString(R.string.topics_select_title);
    public static final String COOLOOK_TITLE_PREFIX = Location.instance
            .getResources().getString(R.string.coolook_title_prefix);
    public static final String INFORMATION = Location.instance.getResources()
            .getString(R.string.information);
    public static final String ACTION_SETTINGS = Location.instance
            .getResources().getString(R.string.action_settings);

    public static final String FEEDBACK = Location.instance.getResources()
            .getString(R.string.feedback);
    public static final String SETTINGS = Location.instance.getResources()
            .getString(R.string.action_settings);

    public static final String BUSINESS_COOPERATION = Location.instance
            .getResources().getString(R.string.business_cooperation);
    public static final String DETECTION_FOR_UPDATE = Location.instance
            .getResources().getString(R.string.detection_for_update);
    public static final String DIGITAL_PASSWORD = Location.instance
            .getResources().getString(R.string.digital_password);
    public static final String GRAPHICS_PASSWORD = Location.instance
            .getResources().getString(R.string.graphics_password);
    public static final String SET_SHORTCUTS = Location.instance.getResources()
            .getString(R.string.set_shortcuts);
    public static final String SCREEN_SAVER = Location.instance.getResources()
            .getString(R.string.screen_saver);
    public static final String FLASHLIGHT = Location.instance.getResources()
            .getString(R.string.flashlight);
    public static final String CALCULATOR = Location.instance.getResources()
            .getString(R.string.calculator);
    public static final String CAMERA = Location.instance.getResources()
            .getString(R.string.camera);
    public static final String PHONE = Location.instance.getResources()
            .getString(R.string.phone);
    public static final String MESSAGES = Location.instance.getResources()
            .getString(R.string.messages);
    public static final String SEARCH = Location.instance.getResources()
            .getString(R.string.search);
    public static final String WALLPAPER = Location.instance.getResources()
            .getString(R.string.wallpaper);
    public static final String CLOCK = Location.instance.getResources()
            .getString(R.string.clock);
    public static final String CUSTOMIZE = Location.instance.getResources()
            .getString(R.string.customize);

    //login   start
    public static final String LOGIN = Location.instance.getResources()
            .getString(R.string.login);//登录
    public static final String INPUT_EMAIL_ACCOUNTS = Location.instance.getResources()
            .getString(R.string.input_email_accounts);// 请输入邮箱地址
    public static final String INPUT_PASSWORD = Location.instance.getResources()
            .getString(R.string.input_password);//请输入密码
    public static final String EMAIL_FORMAT_ERROR = Location.instance.getResources()
            .getString(R.string.email_format_error);// 邮箱格式错误
    public static final String PASSWORD_FORMAT = Location.instance.getResources()
            .getString(R.string.password_format);//请输入6到15位数字或字母的组合密码
    public static final String EMAIL_ACCOUNTS = Location.instance.getResources()
            .getString(R.string.email_accounts);//邮箱地址
    public static final String NEW_PASSWORD = Location.instance.getResources()
            .getString(R.string.new_password);//请输入新密码
    public static final String REGISTER = Location.instance.getResources()
            .getString(R.string.register);//注册
    public static final String EMAIL_HAS_BEEN_REGISTERED = Location.instance.getResources()
            .getString(R.string.email_has_been_registered);//邮箱已被注册
    public static final String FAILURE = Location.instance.getResources()
            .getString(R.string.failure);//失败
    public static final String PASSWORD_MISTAKE = Location.instance.getResources()
            .getString(R.string.password_mistake);//密码错误
    public static final String EMAIL_DOES_NOT_EXIST = Location.instance.getResources()
            .getString(R.string.email_does_not_exist);//邮箱不存在
    public static final String HAS_RETURNED_TO_EMAIL = Location.instance.getResources()
            .getString(R.string.has_returned_to_email);//已重发邮件
    public static final String SEND_EMAIL = Location.instance.getResources()
            .getString(R.string.send_email);//发送邮件
    public static final String EMAIL_HAS_BEEN_SENT = Location.instance.getResources()
            .getString(R.string.email_has_been_sent);//邮件已发送！请到邮箱确认密码，确认后新密码生效。
    public static final String SUCCESS_LOGIN = Location.instance.getResources()
            .getString(R.string.success_login);//登录成功
    public static final String REGISTRATION_EMAIL_ACTIVATION = Location.instance.getResources()
            .getString(R.string.registration_email_activation);//注册成功，已登录，请到邮箱激活账户
    public static final String CONFIRM_EXIT = Location.instance.getResources()
            .getString(R.string.confirm_exit);//退出登录
    public static final String CONFIRM_EXIT_HIT = Location.instance.getResources()
            .getString(R.string.confirm_exit_hit);//退出登录提示语
    public static final String PERSONAL_CENTER = Location.instance.getResources()
            .getString(R.string.personal_center);//个人中心
    public static final String MODIFY_PASSWORD = Location.instance.getResources()
            .getString(R.string.modify_password);//修改密码
    public static final String SETING_SUCCESS = Location.instance.getResources()
            .getString(R.string.seting_success);//设置成功
    public static final String THERE_IS_NO_ACCOUNT = Location.instance.getResources()
            .getString(R.string.there_is_no_account);//账户不存在
    public static final String OLD_PASSWORD_IS_WRONG = Location.instance.getResources()
            .getString(R.string.old_password_is_wrong);//旧密码有误
    public static final String OLD_AND_PASSWORD = Location.instance.getResources()
            .getString(R.string.old_and_password);//新旧密码不能一致
    public static final String EDIT = Location.instance.getResources()
            .getString(R.string.edit);//编辑
    public static final String CANCEL = Location.instance.getResources()
            .getString(R.string.cancel);//取消
    public static final String DELETE = Location.instance.getResources()
            .getString(R.string.delete);//删除
    public static final String NOT_LOGIN = Location.instance.getResources()
            .getString(R.string.not_login);//未登录
    public static final String INFORMATION_IS_EMPTY = Location.instance.getResources()
            .getString(R.string.information_is_empty);//信息为空
    public static final String COMMENT_ON_SUCCESS = Location.instance.getResources()
            .getString(R.string.comment_on_success);//评论成功
    public static final String COMMENT_ON_FAILURE = Location.instance.getResources()
            .getString(R.string.comment_on_failure);//评论失败
    public static final String THUMB_UP_SUCCESS = Location.instance.getResources()
            .getString(R.string.thumb_up_success);//点赞成功
    public static final String CANCEL_THUMB_UP_SUCCESS = Location.instance.getResources()
            .getString(R.string.cancel_thumb_up_success);//取消点赞成功
    public static final String CANCEL_THUMB_UP_FAILURE = Location.instance.getResources()
            .getString(R.string.cancel_thumb_up_failure);//取消点赞失败
    public static final String THUMB_UP_FAILURE = Location.instance.getResources()
            .getString(R.string.thumb_up_failure);//点赞失败
    public static final String COLLECTION_SUCCESS = Location.instance.getResources()
            .getString(R.string.collection_success);//收藏成功
    public static final String COLLECTION_FAILURE = Location.instance.getResources()
            .getString(R.string.collection_failure);//收藏失败
    public static final String CANCEL_COLLECTION_SUCCESS = Location.instance.getResources()
            .getString(R.string.cancel_collection_success);//取消收藏成功
    public static final String  CANCEL_COLLECTION_FAILURE = Location.instance.getResources()
            .getString(R.string.cancel_collection_failure);//取消收藏失败
    public static final String DELETE_SUCCESS = Location.instance.getResources()
            .getString(R.string.delete_success);//点击评论
    public static final String DELETE_FAILURE = Location.instance.getResources()
            .getString(R.string.delete_failure);//点击评论
    public static final String MORE = Location.instance.getResources()
            .getString(R.string.more);//加载更多
    public static final String NOMORE = Location.instance.getResources()
            .getString(R.string.nomore);//没有更多了
    public static final String COMMENT = Location.instance.getResources()
            .getString(R.string.comment);//点击评论
    public static final String MY_CK = Location.instance
            .getResources().getString(R.string.my_ck);
    public static final String MESSAGE = Location.instance
            .getResources().getString(R.string.message);
    //end

    // temp
    public static final String COUNTRY_SELECT_SUBTITLE = Location.instance
            .getResources().getString(R.string.country_select_subtitle);
    public static final String AD_BOTTOMTITLE = Location.instance
            .getResources().getString(R.string.ad_BottomTitle);
    public static final String LANGUAGE_AND_COUNTRY = Location.instance
            .getResources().getString(R.string.language_and_country);
    public static final String DOUBLE_CLICK_EXIT = Location.instance
            .getResources().getString(R.string.double_click_exit);
    public static final String INDIA_PLEASE_LASTEST = Location.instance
            .getResources().getString(R.string.india_please_lastest);
    public static final String RECOMMEND = Location.instance
            .getResources().getString(R.string.recommend);
    public static final String LOCK_SCREEN_ACTIVITY_PASSWORD_OR_PICUN_LOCK = Location.instance
            .getResources().getString(R.string.lockScreenActivity_passWordOrPicunLock);
    public static final String NUM_PASSWORD_CLEAR_SUCCESS = Location.instance
            .getResources().getString(R.string.num_password_clear_success);
    public static final String SHARE_NO_SET_IMG = Location.instance
            .getResources().getString(R.string.share_no_set_img);
    public static final String CLEAR_CACHE = Location.instance
            .getResources().getString(R.string.clear_cache);
    public static final String INFORMATION_INCOMPLETE = Location.instance
            .getResources().getString(R.string.information_incomplete);//输入信息不完整
    public static final String BACKSTAGE_UPLOAD = Location.instance
            .getResources().getString(R.string.backstage_upload);//上传中
    public static final String START_TIME_TODAY = Location.instance
            .getResources().getString(R.string.start_time_today);//开始时间不能晚于现在时间
    public static final String END_TIME_LATE = Location.instance
            .getResources().getString(R.string.end_time_late);//竞猜的前后时间不得大于60天
    public static final String END_AFTER_START_TIME = Location.instance
            .getResources().getString(R.string.end_after_start_time);//结束时间不能早于开始时间
    public static final String EDIT_IS_SAVE = Location.instance
            .getResources().getString(R.string.edit_is_save);//本次编辑内容是否存为草稿
    public static final String IS_SAVE = Location.instance
            .getResources().getString(R.string.is_save);//保存
    public static final String NOT_SAVE = Location.instance
            .getResources().getString(R.string.not_save);//不保存
    public static final String EMAIL_HAS_BEEN_SENT_EXAMINE = Location.instance
            .getResources().getString(R.string.email_has_been_sent_examine);
    public static final String EMAIL_SENT_LATER = Location.instance
            .getResources().getString(R.string.email_sent_later);
    // temp
    //关闭activity的广播(action)
    public static final String CLOSE_ACTIVITY = "closeActivity";


    public static final String ABOUT_US1 = Location.instance
            .getResources().getString(R.string.aboutus1);
    public static final String ABOUT_US2 = Location.instance
            .getResources().getString(R.string.aboutus2);
    public static final String ABOUT_US3 = Location.instance
            .getResources().getString(R.string.aboutus3);
    public static final String ABOUT_US4 = Location.instance
            .getResources().getString(R.string.aboutus4);
    public static final String ABOUT_US5 = Location.instance
            .getResources().getString(R.string.aboutus5);

    public static final String FAVORITE = Location.instance
            .getResources().getString(R.string.favorite);
    public static final String SELECT_COOLOOK_HINT = Location.instance
            .getResources().getString(R.string.select_coolook_hint);
    public static final String WF_BROWSE_SELECT = Location.instance
            .getResources().getString(R.string.wf_browse_select);


    //雅虎广告
    public final static int mMinisiteShowADForYahoo = 4;
    public final static int mMinisiteShowADForYahooRequest = 5;
    public final static int mMinisiteShowADForFB = 3;
}
