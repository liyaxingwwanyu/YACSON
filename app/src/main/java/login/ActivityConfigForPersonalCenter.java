package login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
import util.CtlUtil;


/**
 * Created by Administrator on 2016/5/28.
 */
public abstract class ActivityConfigForPersonalCenter extends Activity implements IObserver {
    protected View mView;
    private Integer mToIDs[] = {R.id.personal_center_exit_login_to_view, R.id.personal_center_title_bar_to_view,
            R.id.personal_center_head_portrait_to_view, R.id.personal_center_user_info_to_view, R.id.personal_center_modify_password_to_view};
    private Integer mChangeToIDs[] = {null, null, null, null};
    private Integer mMasterIDs[] = {R.layout.personal_center_exit_login_add_view, R.layout.personal_center_title_bar_add_view,
            R.layout.personal_center_head_portrait_add_view, R.layout.personal_center_user_info_add_view, R.layout.personal_center_modify_password_add_view};
    private Integer mDetailViewIDs[][] = {
            {R.id.personal_center_exit_login_exit_btn},
            {R.id.personal_center_title_bar_back_btn, R.id.personal_center_title_bar_content_tv},
            {R.id.personal_center_head_portrait_iv},
            {R.id.personal_center_user_info_nickname_rl, R.id.personal_center_user_info_nickname_tv, R.id.personal_center_user_info_nickname_iv, R.id.personal_center_user_info_nickname_content_tv,
                    R.id.personal_center_user_info_gender_rl, R.id.personal_center_user_info_gender_tv, R.id.personal_center_user_info_gender_rg, R.id.personal_center_user_info_gender_boy_rb, R.id.personal_center_user_info_gender_girl_rb,
                    R.id.personal_center_user_info_account_rl, R.id.personal_center_user_info_account_tv, R.id.personal_center_user_info_account_iv, R.id.personal_center_user_info_account_content_tv,
                    R.id.personal_center_user_info_modify_password_rl, R.id.personal_center_user_info_modify_password_tv, R.id.personal_center_user_info_modify_password_iv},
            {R.id.personal_center_modify_password_original_password_ev, R.id.personal_center_modify_password_new_password_ev, R.id.personal_center_modify_password_determine_btn}
    };
    private Integer mDetailDrawableIDs[][] = {{}, {},
            {R.drawable.wf_menu_version_one_user_head_login_click},
            {R.drawable.personal_center_user_info_account_fb_icon, R.drawable.personal_center_user_info_account_tw_icon, R.drawable.personal_center_user_info_account_gp_icon,
                    R.drawable.personal_center_user_info_account_lock_icon, R.drawable.personal_center_user_info_account_unlock_icon},
            {}};
    private Integer mDetailStateDrawableIDs[][] = {{}, {}, {}, {}, {}};
    private String mStrings[][] = {
            {Consts.CONFIRM_EXIT, Consts.CONFIRM_EXIT_HIT},
            {Consts.PERSONAL_CENTER, Consts.MODIFY_PASSWORD},
            {}, {Consts.EMAIL_HAS_BEEN_SENT_EXAMINE, Consts.FAILURE},
            {Consts.INPUT_PASSWORD, Consts.PASSWORD_FORMAT, Consts.SETING_SUCCESS, Consts.THERE_IS_NO_ACCOUNT, Consts.OLD_PASSWORD_IS_WRONG, Consts.FAILURE, Consts.OLD_AND_PASSWORD}};
    private Integer mItemForDetailViewsIDs[] = {R.layout.personal_center_modify_password_dialog_item, R.layout.personal_center_exit_login_dialog_item,
            R.layout.login_register_user_info_nickname_dialog_item, R.layout.login_register_head_portrait_dialog_item
    };
    private Integer mItemDetailViewsForDetailViewsIDs[][] = {{R.id.personal_center_modify_password_dialog_item_tv},
            {R.id.personal_center_exit_login_dialog_item_title_tv, R.id.personal_center_exit_login_dialog_item_content_tv,
                    R.id.personal_center_exit_login_dialog_item_cancel_tv, R.id.personal_center_exit_login_dialog_item_complete_tv},
            {R.id.login_register_user_info_nickname_dialog_item_title_tv, R.id.login_register_user_info_nickname_dialog_item_content_et,
                    R.id.login_register_user_info_nickname_dialog_item_content_tv, R.id.login_register_user_info_nickname_dialog_item_cancel_tv, R.id.login_register_user_info_nickname_dialog_item_submit_tv,
                    R.id.login_register_user_info_nickname_dialog_item_ll, R.id.login_register_user_info_email_dialog_item_ll, R.id.login_register_user_info_email_submit_dialog_item_tv, R.id.login_register_user_info_email_content_dialog_item_tv},
            {R.id.login_register_head_portrait_dialog_item_camera_ll, R.id.login_register_head_portrait_dialog_item_camera_tv,
                    R.id.login_register_head_portrait_dialog_item_gallery_ll, R.id.login_register_head_portrait_dialog_item_gallery_tv,
                    R.id.login_register_head_portrait_dialog_item_cancel_ll, R.id.login_register_head_portrait_dialog_item_cancel_tv}
    };
    private Integer mItemDetailViewsForDetailImageIDs[][] = {{R.style.personal_center_modify_password_dialog_item_style},
            {R.style.personal_center_modify_password_dialog_item_style},
            {R.style.personal_center_modify_password_dialog_item_style},
            {R.style.personal_center_modify_password_dialog_item_style}
    };

    private UILayoutConfigForPersonalCenter mUILayoutConfig;
    private UILayoutMrg mUILayoutMrg;
    private BSModelForLogin mBSModelForLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBSModelForLogin = new BSModelForLogin(this);
        mBSModelForLogin.init();
    }

    protected void initUILayoutConfig() {
        mUILayoutConfig = new UILayoutConfigForPersonalCenter();
        mUILayoutConfig.setIDs(mToIDs, mChangeToIDs, mMasterIDs, mDetailViewIDs, mDetailDrawableIDs, mDetailStateDrawableIDs, mStrings);
        mUILayoutConfig.setItemIDs(mItemForDetailViewsIDs, mItemDetailViewsForDetailViewsIDs, mItemDetailViewsForDetailImageIDs);
    }

    protected void initUILayoutMrg() {
        mUILayoutMrg = new UILayoutMrg(mUILayoutConfig, this, mView, this);
    }

    protected void initCreate() {
        Map<String, BSModelDefault> lBSModels = new HashMap<String, BSModelDefault>();
        lBSModels.put(UILayoutConfigForPersonalCenter.mIDs[1], mBSModelForLogin);
        lBSModels.put(UILayoutConfigForPersonalCenter.mIDs[2], mBSModelForLogin);
        lBSModels.put(UILayoutConfigForPersonalCenter.mIDs[3], mBSModelForLogin);
        mUILayoutMrg.create(new String[]{UILayoutConfigForPersonalCenter.mIDs[0], UILayoutConfigForPersonalCenter.mIDs[1],
                UILayoutConfigForPersonalCenter.mIDs[2], UILayoutConfigForPersonalCenter.mIDs[3]}, lBSModels);
    }

    @Override
    public void update(Object aObject) {
        if (aObject instanceof UIStructDefault) {
            UIStructDefault lUIStructDefault = (UIStructDefault) aObject;
            String lCurState = lUIStructDefault.getCurState();
            int lStateIndex = UILayoutConfigForPersonalCenter.mStateForList.indexOf(lCurState);
            String lCurID = lUIStructDefault.getCurID();
            int lPos = UILayoutConfigForPersonalCenter.mIDsForList.indexOf(lCurID);
            switch (lStateIndex) {
                case 0:
                    //System.out.println("sa,ooooooooooo");
                    if (lPos == 0) {  //确定退出登录
                        //System.out.println("a,ooooooooooo");
                        mBSModelForLogin.exitLogin();
                        back();
                    }
                    if (lPos == 1) { //返回键
                        //System.out.println("aa,ooooooooooo");
                        back();
                    }
                    if (lPos == 4) {
                        //System.out.println("aaa,ooooooooooo");
                        mBSModelForLogin.resetPassword();
                    }
                    break;
                case 1:
                    //RussiaUtil.burypoint(631,"修改密码点击数");
                    mBSModelForLogin.setCurPos(4);
                    mUILayoutMrg.visibility(new String[]{UILayoutConfigForPersonalCenter.mIDs[0],
                            UILayoutConfigForPersonalCenter.mIDs[2], UILayoutConfigForPersonalCenter.mIDs[3]}, View.GONE);

                    Map<String, BSModelDefault> lBSModels = new HashMap<String, BSModelDefault>();
                    lBSModels.put(UILayoutConfigForPersonalCenter.mIDs[4], mBSModelForLogin);
                    mUILayoutMrg.create(new String[]{UILayoutConfigForPersonalCenter.mIDs[4]}, lBSModels);
                    mUILayoutMrg.visibility(new String[]{UILayoutConfigForPersonalCenter.mIDs[4]}, View.VISIBLE);
                    break;
                case 2:
                    if (!mBSModelForLogin.reactivationEmailEx()) {
                        ApplicationUtil.showInfo(this, Consts.EMAIL_SENT_LATER);
                    }
                    break;
                case 3:
                   // System.out.println("aaaaaaa,ooooooooooo");
                    mBSModelForLogin.uploadHead();
                    break;
                case 4:
                    ApplicationUtil.takingPictures(this, mBSModelForLogin.getPrepareFile(true), BSModelForLogin.TAKING_PICTURES_CODE);
                    break;
                case 5:
                    ApplicationUtil.readGallery(this, BSModelForLogin.READ_GALLERY_CODE);
                    break;
                case 6:
                    //System.out.println("aaaaaaaaaa,ooooooooooo");
                    toPersonalCenter();
                    break;
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case BSModelForLogin.TAKING_PICTURES_CODE:
                ApplicationUtil.cutImage(this, mBSModelForLogin.getPrepareFile(false), 1, 1, mBSModelForLogin.getPrepareFile(true), BSModelForLogin.CUT_IMAGE_CODE);
                break;
            case BSModelForLogin.READ_GALLERY_CODE:
                if (data != null) {
                    ApplicationUtil.cutImage(this, data.getData(), 1, 1, mBSModelForLogin.getPrepareFile(true), BSModelForLogin.CUT_IMAGE_CODE);
                }
                break;
            case BSModelForLogin.CUT_IMAGE_CODE:
                mBSModelForLogin.handleCutResult();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUILayoutMrg != null) {
            mUILayoutMrg.clear();
            mUILayoutMrg = null;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            back();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void back() {
        if (mBSModelForLogin.getPosForReal() == 4) {
            CtlUtil.hideKeyboard(mView);
            toPersonalCenter();
        } else {
            mBSModelForLogin.uploadUserInfo();
            ApplicationUtil.openActivity(this, BSConfig.mCommandIDs[24], null);
            finish();
        }
    }

    private void toPersonalCenter() {
        mBSModelForLogin.setCurPos(3);
        mUILayoutMrg.visibility(new String[]{UILayoutConfigForPersonalCenter.mIDs[0],
                UILayoutConfigForPersonalCenter.mIDs[2], UILayoutConfigForPersonalCenter.mIDs[3]}, View.VISIBLE);
        mUILayoutMrg.visibility(new String[]{UILayoutConfigForPersonalCenter.mIDs[4]}, View.GONE);
    }

    @Override
    public void clear() {

    }
}
