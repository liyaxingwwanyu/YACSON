package collection;

import android.view.MotionEvent;
import android.view.View;

import contrler.TouchListenerDefault;


/**
 * Created by Administrator on 2016/7/5.
 */
public class TouchListenerForScrollItem extends TouchListenerDefault {

    private boolean mUseEvent = false;

    public void setUseEvent(boolean aUseEvent) {
        mUseEvent = aUseEvent;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        super.onTouch(v, event);
        return mUseEvent;
    }
}
