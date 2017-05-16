package login;

import android.widget.TextView;

import Z_MajorStructure.contrler.ClickCtl;
import Z_MajorStructure.contrler.UIStructDefault;


/**
 * Created by Administrator on 2016/5/28.
 */
public class PersonalCenterTitleBarCtl extends ClickCtl {

    private TextView mTextView;

    public PersonalCenterTitleBarCtl(UIStructDefault aUIStructDefault) {
        super(aUIStructDefault);
    }

    @Override
    protected void initShow() {
        getUIStructDefault().getBSModel().addObserver(this);
        mTextView = (TextView) getUIStructDefault().getDetailView(1);
        mTextView.setText(getUIStructDefault().getString(0));
    }

    @Override
    protected void initClick() {
        mDetailViewsPos = new Integer[]{0};
        super.initClick();
    }

    @Override
    public void update(Object aObject) {
        super.update(aObject);
        if (aObject instanceof BSModelForLogin) {
            BSModelForLogin lBSModelForLogin = (BSModelForLogin) aObject;
            if (BSModelForLogin.mIDs[2].equals(lBSModelForLogin.getCurID())) {
                if (lBSModelForLogin.getPosForReal() == 3) {
                    mTextView.setText(getUIStructDefault().getString(0));
                }
                if (lBSModelForLogin.getPosForReal() == 4) {
                    mTextView.setText(getUIStructDefault().getString(1));
                }
            }
        }
    }

    @Override
    public void clear() {
        getUIStructDefault().getBSModel().removeObserver(this);
        super.clear();
    }
}
