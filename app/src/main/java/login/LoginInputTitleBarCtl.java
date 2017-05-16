package login;


import Z_MajorStructure.contrler.ClickCtl;
import Z_MajorStructure.contrler.UIStructDefault;

/**
 * Created by Administrator on 2016/5/27.
 */
public class LoginInputTitleBarCtl extends ClickCtl {
    public LoginInputTitleBarCtl(UIStructDefault aUIStructDefault) {
        super(aUIStructDefault);
    }

    @Override
    protected void initClick() {
        mDetailViewsPos = new Integer[]{0, 2, 3};
        super.initClick();
    }

    @Override
    protected void clickView(int aIndex) {
        switch (aIndex){
            case 0:
                super.clickView(aIndex);
                break;
            case 1:
                getUIStructDefault().setCurState(UILayoutConfigForLoginRegister.mState[3]);
                updateObserver(getUIStructDefault());
                break;
            case 2:
                getUIStructDefault().setCurState(UILayoutConfigForLoginRegister.mState[4]);
                updateObserver(getUIStructDefault());
                break;
        }
    }
}
