package Z_MajorStructure.contrler;

import android.view.View;

import Z_MajorStructure.observer.ObservableDefault;


/**
 * Created by sheenly on 16/3/16.
 */
public class NumPwdListener extends ObservableDefault implements View.OnClickListener {

    @Override
    public void onClick(View v) {
        updateObserver(v);
    }
}
