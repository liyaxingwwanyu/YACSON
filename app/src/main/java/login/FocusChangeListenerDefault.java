package login;

import android.view.View;

import Z_MajorStructure.contrler.UIDataDefault;
import Z_MajorStructure.observer.ObservableDefault;


/**
 * Created by Administrator on 2016/7/4.
 */
public class FocusChangeListenerDefault extends ObservableDefault implements View.OnFocusChangeListener {

    private UIDataDefault mUIDataDefault;

    public FocusChangeListenerDefault() {
        mUIDataDefault = new UIDataDefault();
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        mUIDataDefault.setView(v);
        mUIDataDefault.setCurID(UIDataDefault.mIDs[16]);
        updateObserver(mUIDataDefault);
    }


}
