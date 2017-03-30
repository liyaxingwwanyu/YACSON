package login;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import contrler.ClickCtl;
import contrler.NumPwdListener;
import contrler.UIDataDefault;
import contrler.UIStructDefault;
import util.ApplicationUtil;
import util.CtlUtil;
import util.MyDialog;


/**
 * Created by Administrator on 2016/5/29.
 */
public class PersonalCenterUserInfoCtl extends ClickCtl {

    private MyDialog mDialog, mDialogEmail;
    private TextView okBtn;
    private EditText lEditText;
    private int mIndex = -1;

    public PersonalCenterUserInfoCtl(UIStructDefault aUIStructDefault) {
        super(aUIStructDefault);
    }

    @Override
    protected void initShow() {
        super.initShow();
        getUIStructDefault().getBSModel().addObserver(this);
        String lNickname = getUIStructDefault().getBSModel().getAbstracts(5);
        if (TextUtils.isEmpty(lNickname)) {
            lNickname = "";
        }
        ((TextView) getUIStructDefault().getDetailView(3)).setText(lNickname);

        String lSex = getUIStructDefault().getBSModel().getAbstracts(14);
        if (BSModelForLogin.SEX[1].equals(lSex)) {
            ((RadioButton) getUIStructDefault().getDetailView(8)).setChecked(true);
        } else {
            ((RadioButton) getUIStructDefault().getDetailView(7)).setChecked(true);
        }

        String lEmail = getUIStructDefault().getBSModel().getAbstracts(9);
        if (TextUtils.isEmpty(lEmail)) {
            lEmail = "";
        }
        ((TextView) getUIStructDefault().getDetailView(12)).setText(lEmail);

        if (BSModelForLogin.LOGIN_PLATFORMS[0].equals(getUIStructDefault().getBSModel().getAbstracts(1))) {//Coolook登录
            if (BSModelForLogin.ACTIVE_STATE[1].equals(getUIStructDefault().getBSModel().getAbstracts(15))) {//已激活
                ((TextView) getUIStructDefault().getDetailView(10)).setTextColor(Color.rgb(0xA3, 0xA3, 0xA3));
                ((ImageView) getUIStructDefault().getDetailView(11)).setImageBitmap(getUIStructDefault().getDetailBitmap(3));
            } else {
                ((ImageView) getUIStructDefault().getDetailView(11)).setImageBitmap(getUIStructDefault().getDetailBitmap(4));
            }
        } else {//第三方登录
            ((TextView) getUIStructDefault().getDetailView(10)).setTextColor(Color.rgb(0xA3, 0xA3, 0xA3));
            ((TextView) getUIStructDefault().getDetailView(14)).setTextColor(Color.rgb(0xA3, 0xA3, 0xA3));
            if (BSModelForLogin.LOGIN_PLATFORMS[1].equals(getUIStructDefault().getBSModel().getAbstracts(1))) {
                ((ImageView) getUIStructDefault().getDetailView(11)).setImageBitmap(getUIStructDefault().getDetailBitmap(0));
            }
            if (BSModelForLogin.LOGIN_PLATFORMS[2].equals(getUIStructDefault().getBSModel().getAbstracts(1))) {
                ((ImageView) getUIStructDefault().getDetailView(11)).setImageBitmap(getUIStructDefault().getDetailBitmap(1));
            }
            if (BSModelForLogin.LOGIN_PLATFORMS[3].equals(getUIStructDefault().getBSModel().getAbstracts(1))) {
                ((ImageView) getUIStructDefault().getDetailView(11)).setImageBitmap(getUIStructDefault().getDetailBitmap(2));
            }
        }
    }

    @Override
    protected void initClick() {
        mDetailViewsPos = new Integer[]{0, 7, 8, 9, 13};
        super.initClick();
    }

    @Override
    protected void clickView(int aIndex) {
        if (getUIStructDefault().getBSModel() instanceof BSModelForLogin) {
            BSModelForLogin lBSModelForLogin = (BSModelForLogin) getUIStructDefault().getBSModel();
            if (aIndex == 0) {
                System.out.println("1 , yyyyyyyyyyy");
                mIndex = aIndex;
                showDialog();
            }
            if (aIndex == 1) {

                //System.out.println("2 , yyyyyyyyyyy");
                lBSModelForLogin.updateInfo(BSModelForLogin.SP_FILED[14], BSModelForLogin.SEX[0]);
            }
            if (aIndex == 2) {

                    //System.out.println("3 , yyyyyyyyyyy");
                    lBSModelForLogin.updateInfo(BSModelForLogin.SP_FILED[14], BSModelForLogin.SEX[1]);
                }
                if (BSModelForLogin.LOGIN_PLATFORMS[0].equals(getUIStructDefault().getBSModel().getAbstracts(1))) {
                    // System.out.println("4 , yyyyyyyyyyy");
                    if (aIndex == 3 && !BSModelForLogin.ACTIVE_STATE[1].equals(getUIStructDefault().getBSModel().getAbstracts(15))) {
                        System.out.println("5 , yyyyyyyyyyy");
                        lBSModelForLogin.inputInfo(getUIStructDefault().getBSModel().getAbstracts(9));
                        getUIStructDefault().setCurState(UILayoutConfigForPersonalCenter.mState[2]);
                    updateObserver(getUIStructDefault());;
                }
                if (aIndex == 4) {  //邮箱
                    System.out.println("5 , yyyyyyyyyyy");
                    getUIStructDefault().setCurState(UILayoutConfigForPersonalCenter.mState[1]);
                    updateObserver(getUIStructDefault());
                }
            }
        }
    }

    @Override
    public void update(Object aObject) {
        if (aObject instanceof View) {
            if (mDialog != null && mDialog.isShowing()) {
                boolean lClickOK = mIndex == 0 && ((View) aObject).getId() == getUIStructDefault().getItemDetailViewsForDetailViewsID(0).get(4);

                EditText lEditText = (EditText) mDialog.findViewById(getUIStructDefault().getItemDetailViewsForDetailViewsID(0).get(1));

                String lName = lClickOK ? lEditText.getText().toString() : "";
                boolean lNameOK = lName.length() >= 2 && lName.length() <= 20;
                if (!lClickOK || lNameOK) {
                    CtlUtil.hideKeyboard(lEditText);
                    mDialog.dismiss();
                    mDialog = null;
                    mIndex = -1;
                }
                if (lClickOK && lNameOK) {
                    ((TextView) getUIStructDefault().getDetailView(3)).setText(lName);
                    if (getUIStructDefault().getBSModel() instanceof BSModelForLogin) {
                        ((BSModelForLogin) getUIStructDefault().getBSModel()).updateInfo(BSModelForLogin.SP_FILED[5], lName);
                    }
                }
            } else if (mDialogEmail != null && mDialogEmail.isShowing()) {
                mDialogEmail.dismiss();
                mDialogEmail = null;
            } else {
                super.update(aObject);
            }
        }

        //  控制ok按钮颜色
        if (aObject instanceof UIDataDefault) {
            UIDataDefault lUIDataDefault = (UIDataDefault) aObject;
            if (UIDataDefault.mIDs[15].equals(lUIDataDefault.getCurID())) {
                String nickname = lEditText.getText().toString().trim();
                if (TextUtils.isEmpty(nickname)) {
//                    okBtn.setBackgroundResource(top.coolook.hubii.R.drawable.nickname_corner_tv_default);
                } else {
//                    okBtn.setBackgroundResource(top.coolook.hubii.R.drawable.nickname_corner_tv_click);
                }
            }
        }

        if (aObject instanceof BSModelForLogin) {
            BSModelForLogin lBSModelForLogin = (BSModelForLogin) aObject;
            if (BSModelForLogin.mIDs[6].equals(lBSModelForLogin.getCurID())) {
                if (BSModelForLogin.mStates[9].equals(lBSModelForLogin.getCurState())) {
                    //ApplicationUtil.showInfo(getUIStructDefault().getContext(), getUIStructDefault().getString(0));
                    showDialogEmail();
                }
                if (BSModelForLogin.mStates[7].equals(lBSModelForLogin.getCurState())) {
                    ApplicationUtil.showInfo(getUIStructDefault().getContext(), getUIStructDefault().getString(1));
                }
            }
        }
    }

    private void showDialog() {
        mDialog = new MyDialog(getUIStructDefault().getContext(), getUIStructDefault().getItemDetailViewsForDetailImageID(0).get(0));
        View lView = View.inflate(getUIStructDefault().getContext(), getUIStructDefault().getItemForDetailViewsID(0), null);
        mDialog.setContentView(lView);
        String lNickname = getUIStructDefault().getBSModel().getAbstracts(5);
        String lCurShowNickname = ((TextView) getUIStructDefault().getDetailView(3)).getText().toString();

        okBtn = (TextView) mDialog.findViewById(getUIStructDefault().getItemDetailViewsForDetailViewsID(0).get(4));

        lEditText = (EditText) mDialog.findViewById(getUIStructDefault().getItemDetailViewsForDetailViewsID(0).get(1));
        lEditText.setHint((TextUtils.isEmpty(lCurShowNickname) && lNickname != null) ? lNickname : lCurShowNickname);

        TextWatcherDefault lTextWatcherPasswordET = new TextWatcherDefault();
        lTextWatcherPasswordET.addObserver(this);
        lEditText.addTextChangedListener(lTextWatcherPasswordET);

        NumPwdListener lPwdListener = new NumPwdListener();
        lPwdListener.addObserver(this);
        mDialog.findViewById(getUIStructDefault().getItemDetailViewsForDetailViewsID(0).get(3)).setOnClickListener(lPwdListener);
        mDialog.findViewById(getUIStructDefault().getItemDetailViewsForDetailViewsID(0).get(4)).setOnClickListener(lPwdListener);
        Window lDialogWindow = mDialog.getWindow();
        lDialogWindow.setGravity(Gravity.BOTTOM);

        mDialog.setCanceledOnTouchOutside(true);

        mDialog.show();
        CtlUtil.showKeyboard(lEditText);
    }

    private void showDialogEmail() {
        if (mDialogEmail == null || !mDialogEmail.isShowing()) {
            mDialogEmail = new MyDialog(getUIStructDefault().getContext(), getUIStructDefault().getItemDetailViewsForDetailImageID(0).get(0));
            View lView = View.inflate(getUIStructDefault().getContext(), getUIStructDefault().getItemForDetailViewsID(0), null);
            mDialogEmail.setContentView(lView);
            mDialogEmail.findViewById(getUIStructDefault().getItemDetailViewsForDetailViewsID(0).get(5)).setVisibility(View.GONE);
            mDialogEmail.findViewById(getUIStructDefault().getItemDetailViewsForDetailViewsID(0).get(6)).setVisibility(View.VISIBLE);
            TextView lEmailHint = (TextView) mDialogEmail.findViewById(getUIStructDefault().getItemDetailViewsForDetailViewsID(0).get(8));
            String lDialogInfo = getUIStructDefault().getString(0) + getUIStructDefault().getBSModel().getAbstracts(9);
            lEmailHint.setText(lDialogInfo);
            NumPwdListener lPwdListener = new NumPwdListener();
            lPwdListener.addObserver(this);
            mDialogEmail.findViewById(getUIStructDefault().getItemDetailViewsForDetailViewsID(0).get(7)).setOnClickListener(lPwdListener);
            mDialogEmail.show();
        }
    }

    @Override
    public void clear() {
        getUIStructDefault().getBSModel().removeObserver(this);
        if (mDialog != null) {
            if (mDialog.isShowing()) {
                mDialog.dismiss();

            }
            mDialog = null;
        }
        if (mDialogEmail != null) {
            if (mDialogEmail.isShowing()) {
                mDialogEmail.dismiss();
            }
            mDialogEmail = null;
        }
        super.clear();
    }

}
