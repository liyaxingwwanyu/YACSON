package contrler;


import collection.UILayoutConfigForMiniSite;

/**
 * Created by Administrator on 2016/4/21.
 */
public class ClickCtl extends FontSizeCtl{
    public ClickCtl(UIStructDefault aUIStructDefault) {
        super(aUIStructDefault);
    }

    @Override
    protected void initShow() {
    }

    @Override
    protected void clickView(int aIndex) {
        //System.out.println("klklklkll,oooooooooo");
        getUIStructDefault().setCurState(UILayoutConfigForMiniSite.mState[12]);
        updateObserver(getUIStructDefault());
    }
}
