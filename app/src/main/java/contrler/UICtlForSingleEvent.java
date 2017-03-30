package contrler;

import android.view.View;

/**
 * Created by sheenly on 16/3/29.
 */
public class UICtlForSingleEvent extends UICtlDefault {
    private int mEventCount = 0;
    protected View mCurTouchView;
    protected int mInitTop, mInitBottom, mInitLeft, mInitRight;
    protected float mInitX, mInitY, mCurX, mCurY;

    public UICtlForSingleEvent(UIStructDefault aUIStructDefault) {
        super(aUIStructDefault);
    }

    @Override
    protected void onDown(UIDataDefault aUIDataDefault) {
        if (++mEventCount > 1) return;
        mCurTouchView = aUIDataDefault.getView();
        if (mCurTouchView != null) {
            mInitTop = mCurTouchView.getTop();
            mInitBottom = mCurTouchView.getBottom();
            mInitLeft = mCurTouchView.getLeft();
            mInitRight = mCurTouchView.getRight();
        }
        mInitX = aUIDataDefault.getX();
        mInitY = aUIDataDefault.getY();
        mCurX = mInitX;
        mCurY = mInitY;
        onDownAfter();
    }

    @Override
    protected void onUp(UIDataDefault aUIDataDefault) {
        if (--mEventCount > 0) return;
        mCurX = aUIDataDefault.getX();
        mCurY = aUIDataDefault.getY();
        onUpAfter();
    }

    @Override
    protected void onMove(UIDataDefault aUIDataDefault) {
        if (mEventCount != 1) return;
        mCurX = aUIDataDefault.getX();
        mCurY = aUIDataDefault.getY();
        onMoveAfter();
    }

    @Override
    protected void onCancel(UIDataDefault aUIDataDefault) {
        mEventCount = 0;
        onCancelAfter();
    }
}
