package Z_MajorStructure.contrler;

import android.view.MotionEvent;
import android.view.View;

import Z_MajorStructure.observer.ObservableDefault;

/**
 * Created by sheenly on 16/3/25.
 */
public class TouchListenerDefault extends ObservableDefault implements View.OnTouchListener {

    private UIDataDefault mUIDataDefault;
    public TouchListenerDefault() {
        mUIDataDefault = new UIDataDefault();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        mUIDataDefault.setView(v);
        mUIDataDefault.setXY(event.getRawX(),event.getRawY());
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mUIDataDefault.setCurID(UIDataDefault.mIDs[4]);
                mUIDataDefault.setCurState(UIDataDefault.mStates[1]);
                updateObserver(mUIDataDefault);
                break;
            case MotionEvent.ACTION_UP:
                mUIDataDefault.setCurID(UIDataDefault.mIDs[4]);
                mUIDataDefault.setCurState(UIDataDefault.mStates[2]);
                updateObserver(mUIDataDefault);
                break;
            case MotionEvent.ACTION_MOVE:
                mUIDataDefault.setCurID(UIDataDefault.mIDs[4]);
                mUIDataDefault.setCurState(UIDataDefault.mStates[3]);
                updateObserver(mUIDataDefault);
                break;
            case MotionEvent.ACTION_CANCEL:
                mUIDataDefault.setCurID(UIDataDefault.mIDs[4]);
                mUIDataDefault.setCurState(UIDataDefault.mStates[4]);
                updateObserver(mUIDataDefault);
                break;
        }
        return false;
    }
}
