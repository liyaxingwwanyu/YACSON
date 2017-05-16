package login;


import Z_MajorStructure.contrler.ClickCtl;
import Z_MajorStructure.contrler.UIStructDefault;

/**
 * Created by Administrator on 2016/5/28.
 */
public class LoginRegisterThirdLoginCtl extends ClickCtl {

    public LoginRegisterThirdLoginCtl(UIStructDefault aUIStructDefault) {
        super(aUIStructDefault);
    }

    @Override
    protected void clickView(int aIndex) {
        if (aIndex == 1) {
            getUIStructDefault().setCurState(UILayoutConfigForLoginRegister.mState[5]);
            updateObserver(getUIStructDefault());
        }
        if (aIndex == 2) {
            getUIStructDefault().setCurState(UILayoutConfigForLoginRegister.mState[6]);
            updateObserver(getUIStructDefault());
        }
        if (aIndex == 3) {
            getUIStructDefault().setCurState(UILayoutConfigForLoginRegister.mState[7]);
            updateObserver(getUIStructDefault());
        }
    }
}