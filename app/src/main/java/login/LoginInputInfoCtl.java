package login;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import Z_MajorStructure.contrler.ClickCtl;
import Z_MajorStructure.contrler.UIDataDefault;
import Z_MajorStructure.contrler.UIStructDefault;


/**
 * Created by sks on 2016/5/25.
 */
public class LoginInputInfoCtl extends ClickCtl {

    private TextView mTextView;
    private EditText mEmailET, mPasswordET;
    private Button mButton;
    private View mRound;
    private ImageView mEmailDel, mPasseordDel;
    private BSModelForLogin lBSModelForLogin;

    public LoginInputInfoCtl(UIStructDefault aUIStructDefault) {
        super(aUIStructDefault);
    }

    @Override
    protected void initShow() {
        getUIStructDefault().getBSModel().addObserver(this);
        mTextView = ((TextView) getUIStructDefault().getDetailView(0));
        mEmailET = ((EditText) getUIStructDefault().getDetailView(2));
        mPasswordET = ((EditText) getUIStructDefault().getDetailView(5));
        mButton = ((Button) getUIStructDefault().getDetailView(7));
        mEmailDel = (ImageView) getUIStructDefault().getDetailView(9);
        mPasseordDel = (ImageView) getUIStructDefault().getDetailView(10);

        TextWatcherDefault lTextWatcherEmailET = new TextWatcherDefault();
        lTextWatcherEmailET.addObserver(this);
        mEmailET.addTextChangedListener(lTextWatcherEmailET);

        TextWatcherDefault lTextWatcherPasswordET = new TextWatcherDefault();
        lTextWatcherPasswordET.addObserver(this);
        mPasswordET.addTextChangedListener(lTextWatcherPasswordET);

        mButton.setVisibility(View.VISIBLE);
        mRound = getUIStructDefault().getDetailView(8);
        mRound.setVisibility(View.GONE);
        mEmailDel.setVisibility(View.GONE);
        mPasseordDel.setVisibility(View.GONE);
    }

    @Override
    protected void initClick() {
        mDetailViewsPos = new Integer[]{7, 9, 10};
        super.initClick();
        FocusChangeListenerDefault lFocusChangeListenerDefault = new FocusChangeListenerDefault();
        lFocusChangeListenerDefault.addObserver(this);
        getUIStructDefault().getDetailView(5).setOnFocusChangeListener(lFocusChangeListenerDefault);
    }

    private void uiDefault() {
        ((ImageView) getUIStructDefault().getDetailView(1)).setImageBitmap(getUIStructDefault().getDetailBitmap(0));
        ((ImageView) getUIStructDefault().getDetailView(4)).setImageBitmap(getUIStructDefault().getDetailBitmap(1));
        mEmailET.setHint(getUIStructDefault().getString(4));
        mPasswordET.setHint(getUIStructDefault().getString(5));
        mButton.setBackgroundColor(Color.rgb(0xA3, 0xA3, 0xA3));

        if (getUIStructDefault().getBSModel() instanceof BSModelForLogin) {
            lBSModelForLogin = (BSModelForLogin) getUIStructDefault().getBSModel();
        }

        mTextView.setText("");
        mEmailET.setText(lBSModelForLogin.getSP(BSModelForLogin.SP_FILED[9]));
        mPasswordET.setText("");
        textLineDefault();
        mButton.setClickable(true);
    }

    private void textLineDefault() {
        getUIStructDefault().getDetailView(3).setBackgroundColor(Color.rgb(0xA3, 0xA3, 0xA3));
        getUIStructDefault().getDetailView(6).setBackgroundColor(Color.rgb(0xA3, 0xA3, 0xA3));
        mRound.setVisibility(View.GONE);
        mButton.setVisibility(View.VISIBLE);
    }

    private void loginDefault() {
        uiDefault();
        mButton.setText(getUIStructDefault().getString(10));
    }

    private void registerDefault() {
        uiDefault();
        mButton.setText(getUIStructDefault().getString(7));
    }

    private void forgetPasswordDefault() {
        uiDefault();
        mPasswordET.setHint(getUIStructDefault().getString(6));
        mButton.setText(getUIStructDefault().getString(13));
    }

    @Override
    protected void clickView(int aIndex) {
        if (getUIStructDefault().getBSModel() instanceof BSModelForLogin) {
            switch (aIndex) {
                case 0:
                    visibleRound();
                    lBSModelForLogin = (BSModelForLogin) getUIStructDefault().getBSModel();
                    String lEmail = mEmailET.getText().toString().trim();
                    String lPassword = mPasswordET.getText().toString().trim();
                    lBSModelForLogin.inputInfo(lEmail, lPassword);
                    super.clickView(aIndex);
                    break;
                case 1:
                    mEmailET.setText("");
                    break;
                case 2:
                    mPasswordET.setText("");
                    break;
            }
        }
    }

    private void visibleRound() {
        mButton.setVisibility(View.GONE);
        mRound.setVisibility(View.VISIBLE);
        ObjectAnimator animator = ObjectAnimator.ofFloat(mRound, "rotation", 0, 360);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setRepeatCount(-1);//-1表示一直旋转
        animator.setDuration(500).start();
    }

    @Override
    public void update(Object aObject) {
        super.update(aObject);
        if (aObject instanceof BSModelForLogin) {
            BSModelForLogin lBSModelForLogin = (BSModelForLogin) aObject;
            if (BSModelForLogin.mIDs[0].equals(lBSModelForLogin.getCurID())) {
                textLineDefault();
                if (BSModelForLogin.mStates[0].equals(lBSModelForLogin.getCurState())) {
                    mTextView.setText(getUIStructDefault().getString(0));
                    getUIStructDefault().getDetailView(3).setBackgroundColor(Color.RED);
                }
                if (BSModelForLogin.mStates[1].equals(lBSModelForLogin.getCurState())) {
                    mTextView.setText(getUIStructDefault().getString(1));
                    getUIStructDefault().getDetailView(6).setBackgroundColor(Color.RED);
                }
                if (BSModelForLogin.mStates[2].equals(lBSModelForLogin.getCurState())) {
                    mTextView.setText(getUIStructDefault().getString(2));
                    getUIStructDefault().getDetailView(3).setBackgroundColor(Color.RED);
                }
                if (BSModelForLogin.mStates[3].equals(lBSModelForLogin.getCurState())) {
                    mTextView.setText(getUIStructDefault().getString(3));
                    getUIStructDefault().getDetailView(6).setBackgroundColor(Color.RED);
                }
            }
            if (BSModelForLogin.mIDs[1].equals(lBSModelForLogin.getCurID())) {
                textLineDefault();
                if (BSModelForLogin.mStates[5].equals(lBSModelForLogin.getCurState())) {
                    getUIStructDefault().setCurState(UILayoutConfigForLoginRegister.mState[1]);
                    updateObserver(getUIStructDefault());
                }
                if (BSModelForLogin.mStates[6].equals(lBSModelForLogin.getCurState())) {
                    mTextView.setText(getUIStructDefault().getString(8));
                    getUIStructDefault().getDetailView(3).setBackgroundColor(Color.RED);
                }
                if (BSModelForLogin.mStates[7].equals(lBSModelForLogin.getCurState())) {
                    mTextView.setText(getUIStructDefault().getString(9));
                }
            }
            if (BSModelForLogin.mIDs[2].equals(lBSModelForLogin.getCurID())) {
                if (lBSModelForLogin.getPosForReal() == 0) {
                    registerDefault();
                }
                if (lBSModelForLogin.getPosForReal() == 1) {
                    loginDefault();
                }
                if (lBSModelForLogin.getPosForReal() == 2) {
                    forgetPasswordDefault();
                }
            }
            if (BSModelForLogin.mIDs[3].equals(lBSModelForLogin.getCurID())) {
                textLineDefault();
                if (BSModelForLogin.mStates[8].equals(lBSModelForLogin.getCurState())) {
                    mTextView.setText(getUIStructDefault().getString(12));
                    getUIStructDefault().getDetailView(3).setBackgroundColor(Color.RED);
                }
                if (BSModelForLogin.mStates[4].equals(lBSModelForLogin.getCurState())) {
                    mTextView.setText(getUIStructDefault().getString(11));
                    getUIStructDefault().getDetailView(6).setBackgroundColor(Color.RED);
                }
                if (BSModelForLogin.mStates[7].equals(lBSModelForLogin.getCurState())) {
                    mTextView.setText(getUIStructDefault().getString(9));
                }
                if (BSModelForLogin.mStates[5].equals(lBSModelForLogin.getCurState())) {
                    getUIStructDefault().setCurState(UILayoutConfigForLoginRegister.mState[2]);
                    updateObserver(getUIStructDefault());
                }
            }
            if (BSModelForLogin.mIDs[4].equals(lBSModelForLogin.getCurID())) {
                textLineDefault();
                if (BSModelForLogin.mStates[9].equals(lBSModelForLogin.getCurState())) {
                    mTextView.setText(getUIStructDefault().getString(14));
                    mButton.setBackgroundColor(Color.rgb(0xA3, 0xA3, 0xA3));
                    mButton.setClickable(false);
                }
                if (BSModelForLogin.mStates[8].equals(lBSModelForLogin.getCurState())) {
                    mTextView.setText(getUIStructDefault().getString(12));
                    getUIStructDefault().getDetailView(3).setBackgroundColor(Color.RED);
                }
                if (BSModelForLogin.mStates[7].equals(lBSModelForLogin.getCurState())) {
                    mTextView.setText(getUIStructDefault().getString(9));
                }
                if (BSModelForLogin.mStates[13].equals(lBSModelForLogin.getCurState())) {
                    getUIStructDefault().setCurState(UILayoutConfigForLoginRegister.mState[10]);
                    updateObserver(getUIStructDefault());
                }
            }
        }
        if (aObject instanceof UIDataDefault) {
            UIDataDefault lUIDataDefault = (UIDataDefault) aObject;
            if (UIDataDefault.mIDs[15].equals(lUIDataDefault.getCurID())) {
                String lCurEmail = mEmailET.getText().toString().trim();
                String lCurPassword = mPasswordET.getText().toString().trim();
                if (TextUtils.isEmpty(lCurEmail)) {
                    mButton.setBackgroundColor(Color.rgb(0xA3, 0xA3, 0xA3));
                    mEmailDel.setVisibility(View.GONE);
                } else {
                    mButton.setBackgroundColor(Color.rgb(0x1A, 0xB2, 0xE8));
                    mEmailDel.setVisibility(View.VISIBLE);
                }
                if (TextUtils.isEmpty(lCurPassword)) {
                    mPasseordDel.setVisibility(View.GONE);
                } else {
                    mPasseordDel.setVisibility(View.VISIBLE);
                }
            }

//            if (UIDataDefault.mIDs[16].equals(lUIDataDefault.getCurID())) {//原获取EditText焦点，隐藏上方空白
//                if (lUIDataDefault.getView().hasFocus()) {
//                    getUIStructDefault().setCurState(UILayoutConfigForLoginRegister.mState[9]);
//                    updateObserver(getUIStructDefault());
//                } else {
//                    getUIStructDefault().setCurState(UILayoutConfigForLoginRegister.mState[8]);
//                    updateObserver(getUIStructDefault());
//                }
//            }
        }
    }

    @Override
    public void clear() {
        getUIStructDefault().getBSModel().removeObserver(this);
        super.clear();
    }
}