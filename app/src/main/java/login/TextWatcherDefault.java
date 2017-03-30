package login;

import android.text.Editable;
import android.text.TextWatcher;

import contrler.UIDataDefault;
import observer.ObservableDefault;


/**
 * Created by Administrator on 2016/5/28.
 */
public class TextWatcherDefault extends ObservableDefault implements TextWatcher {

    private UIDataDefault mUIDataDefault;

    public TextWatcherDefault() {
        mUIDataDefault = new UIDataDefault();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        mUIDataDefault.setCurID(UIDataDefault.mIDs[15]);
        updateObserver(mUIDataDefault);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
