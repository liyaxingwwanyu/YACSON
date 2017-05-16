package login;


import Z_MajorStructure.contrler.ClickCtl;
import Z_MajorStructure.contrler.UIStructDefault;

/**
 * Created by sks on 2016/6/28.
 */
public class PersonalBusinessCtl extends ClickCtl {
    public PersonalBusinessCtl(UIStructDefault aUIStructDefault) {
        super(aUIStructDefault);
    }

    @Override
    protected void initClick() {
        mDetailViewsPos = new Integer[]{1, 2, 3, 4, 6};
        super.initClick();
    }

    @Override
    protected void clickView(int aIndex) {
        if (aIndex == 0) {
//            getUIStructDefault().setCurState(UILayoutConfigForMaster.mState[8]);
            updateObserver(getUIStructDefault());
        }
        if (aIndex == 1) {
//            getUIStructDefault().setCurState(UILayoutConfigForMaster.mState[9]);
            updateObserver(getUIStructDefault());
        }
        if (aIndex == 2) {
//            getUIStructDefault().setCurState(UILayoutConfigForMaster.mState[10]);
            updateObserver(getUIStructDefault());
        }
        if (aIndex == 3) {
//            getUIStructDefault().setCurState(UILayoutConfigForMaster.mState[11]);
            updateObserver(getUIStructDefault());
        }
        if (aIndex == 4) {
//            getUIStructDefault().setCurState(UILayoutConfigForMaster.mState[14]);
            updateObserver(getUIStructDefault());
        }
    }
}
