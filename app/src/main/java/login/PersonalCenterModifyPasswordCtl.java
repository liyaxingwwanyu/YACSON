package login;

import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.ya.cson.R;

import Z_MajorStructure.contrler.ClickCtl;
import Z_MajorStructure.contrler.UIDataDefault;
import Z_MajorStructure.contrler.UIStructDefault;
import util.ApplicationUtil;


/**
 * Created by Administrator on 2016/5/29.
 */
public class PersonalCenterModifyPasswordCtl extends ClickCtl {

    private EditText mOriginalPassword, mNewPassword;
    private Button mPasswordOk;

    public PersonalCenterModifyPasswordCtl(UIStructDefault aUIStructDefault) {
        super(aUIStructDefault);
    }

    @Override
    protected void initShow() {
        super.initShow();
        getUIStructDefault().getBSModel().addObserver(this);
        mOriginalPassword = ((EditText) getUIStructDefault().getDetailView(0));
        mNewPassword = ((EditText) getUIStructDefault().getDetailView(1));

        TextWatcherDefault lTextWatcherOldPasswordET = new TextWatcherDefault();
        lTextWatcherOldPasswordET.addObserver(this);
        mOriginalPassword.addTextChangedListener(lTextWatcherOldPasswordET);

        TextWatcherDefault lTextWatcherNewPasswordET = new TextWatcherDefault();
        lTextWatcherNewPasswordET.addObserver(this);
        mNewPassword.addTextChangedListener(lTextWatcherNewPasswordET);

        mPasswordOk = ((Button) getUIStructDefault().getDetailView(2));

        uiDefault();
    }

    protected void initClick() {
        mDetailViewsPos = new Integer[]{2};
        super.initClick();
    }

    private void uiDefault() {
        mOriginalPassword.setText("");
        mNewPassword.setText("");
    }

    @Override
    protected void clickView(int aIndex) {
        if (getUIStructDefault().getBSModel() instanceof BSModelForLogin) {

            BSModelForLogin lBSModelForLogin = (BSModelForLogin) getUIStructDefault().getBSModel();
            String lOriginalPassword = mOriginalPassword.getText().toString().trim();
            String lNewPassword = mNewPassword.getText().toString().trim();
            lBSModelForLogin.inputInfo(null, lNewPassword, lOriginalPassword);
            super.clickView(aIndex);
        }
    }

    @Override
    public void update(Object aObject) {
        super.update(aObject);
        if (aObject instanceof BSModelForLogin) {
            BSModelForLogin lBSModelForLogin = (BSModelForLogin) aObject;
            if (BSModelForLogin.mIDs[0].equals(lBSModelForLogin.getCurID())) {
                if (BSModelForLogin.mStates[1].equals(lBSModelForLogin.getCurState())) {
                    ApplicationUtil.showInfo(getUIStructDefault().getContext(), getUIStructDefault().getString(0));
                }
                if (BSModelForLogin.mStates[3].equals(lBSModelForLogin.getCurState())) {
                    ApplicationUtil.showInfo(getUIStructDefault().getContext(), getUIStructDefault().getString(1));
                }
                if (BSModelForLogin.mStates[10].equals(lBSModelForLogin.getCurState())) {
                    ApplicationUtil.showInfo(getUIStructDefault().getContext(), getUIStructDefault().getString(6));
                }
            }
            if (BSModelForLogin.mIDs[5].equals(lBSModelForLogin.getCurID())) {
                if (BSModelForLogin.mStates[5].equals(lBSModelForLogin.getCurState())) {
                    ApplicationUtil.showInfo(getUIStructDefault().getContext(), getUIStructDefault().getString(2));
                    getUIStructDefault().setCurState(UILayoutConfigForPersonalCenter.mState[6]);
                    updateObserver(getUIStructDefault());
                }
                if (BSModelForLogin.mStates[8].equals(lBSModelForLogin.getCurState())) {
                    ApplicationUtil.showInfo(getUIStructDefault().getContext(), getUIStructDefault().getString(3));
                }
                if (BSModelForLogin.mStates[4].equals(lBSModelForLogin.getCurState())) {
                    ApplicationUtil.showInfo(getUIStructDefault().getContext(), getUIStructDefault().getString(4));
                }
                if (BSModelForLogin.mStates[7].equals(lBSModelForLogin.getCurState())) {
                    ApplicationUtil.showInfo(getUIStructDefault().getContext(), getUIStructDefault().getString(5));
                }
            }
            if (BSModelForLogin.mIDs[2].equals(lBSModelForLogin.getCurID())) {
                if (lBSModelForLogin.getPosForReal() == 4) {
                    uiDefault();
                }
            }
        }

        //  控制ok按钮颜色
        if (aObject instanceof UIDataDefault) {
            UIDataDefault lUIDataDefault = (UIDataDefault) aObject;
            if (UIDataDefault.mIDs[15].equals(lUIDataDefault.getCurID())) {
                String oldPass = mOriginalPassword.getText().toString().trim();
                String newPass = mNewPassword.getText().toString().trim();
                if (TextUtils.isEmpty(oldPass) && TextUtils.isEmpty(newPass)) {
                    mPasswordOk.setBackgroundResource(R.color.lightestgray);
                } else {
                    mPasswordOk.setBackgroundResource(R.color.royalblue);
                }
            }
        }
    }

//    private void showDialog(String aInfo) {
//        Dialog lDialog = new Dialog(getUIStructDefault().getContext(), getUIStructDefault().getItemDetailViewsForDetailImageID(2).get(0));
//        View lView = View.inflate(getUIStructDefault().getContext(), getUIStructDefault().getItemForDetailViewsID(2), null);
//        lDialog.setContentView(lView);
//        ((TextView) lDialog.findViewById(getUIStructDefault().getItemDetailViewsForDetailViewsID(2).get(0))).setText(aInfo);
//        lDialog.setCanceledOnTouchOutside(true);
//        lDialog.show();
//    }

    @Override
    public void clear() {
        getUIStructDefault().getBSModel().removeObserver(this);
        super.clear();
    }
}