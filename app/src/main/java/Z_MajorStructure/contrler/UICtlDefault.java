package Z_MajorStructure.contrler;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import Z_MajorStructure.observer.IObserver;
import Z_MajorStructure.observer.ObservableDefault;
import util.DateTime;

/**
 * Created by sheenly on 16/3/9.
 */
public class UICtlDefault extends ObservableDefault implements IObserver {
    private UIStructDefault mUIStructDefault;
    protected Integer[] mDetailViewsPos;
    protected String mTime;
    protected String mDate;
    protected TouchListenerDefault mTouchListenerDefault;

    public UICtlDefault(UIStructDefault aUIStructDefault) {
        mUIStructDefault = aUIStructDefault;
        int lSize = mUIStructDefault.getDetailViewSize();
        if (lSize > 0) {
            mDetailViewsPos = new Integer[lSize];
            handleDetailViewsPos();
        }
        mTouchListenerDefault = new TouchListenerDefault();
        mTouchListenerDefault.addObserver(this);
    }

    @Override
    public void init() {
        handleDetailView();
    }

    @Override
    public void update(Object aObject) {
        mTime = DateTime.getTime();
        mDate = DateTime.getDate();
        if (aObject instanceof UIDataDefault) {
            UIDataDefault lUIDataDefault = (UIDataDefault) aObject;
            if (UIDataDefault.mIDs[4].equals(lUIDataDefault.getCurID())) {
                if (UIDataDefault.mStates[1].equals(lUIDataDefault.getCurState())) {
                    onDown(lUIDataDefault);
                }
                if (UIDataDefault.mStates[3].equals(lUIDataDefault.getCurState())) {
                    onMove(lUIDataDefault);
                }
                if (UIDataDefault.mStates[2].equals(lUIDataDefault.getCurState())) {
                    onUp(lUIDataDefault);
                }
                if (UIDataDefault.mStates[4].equals(lUIDataDefault.getCurState())) {
                    onCancel(lUIDataDefault);
                }
            }
        }
    }

    protected void onDown(UIDataDefault aUIDataDefault) {

    }

    protected void onDownAfter() {

    }

    protected void onUp(UIDataDefault aUIDataDefault) {

    }

    protected void onUpAfter() {

    }

    protected void onMove(UIDataDefault aUIDataDefault) {

    }

    protected void onMoveAfter() {

    }

    protected void onCancel(UIDataDefault aUIDataDefault) {

    }

    protected void onCancelAfter() {

    }

    protected UIStructDefault getUIStructDefault() {
        return mUIStructDefault;
    }

    @Override
    public void clear() {
        clearDetailView();
        mDetailViewsPos = null;
        mUIStructDefault = null;
        mTouchListenerDefault = null;
    }

    private void clearDetailView() {
        int lSize = getUIStructDefault().getDetailViewSize();
        if (lSize > 0) {
            for (int i = 0; i < lSize; i++) {
                getUIStructDefault().getDetailView(i).setBackgroundDrawable(null);
            }
        }
    }

    protected void handleDetailViewsPos() {
        for (int i = 0; i < mUIStructDefault.getDetailViewSize(); i++) {
            mDetailViewsPos[i] = i;
        }
    }

    protected void handleDetailView() {
        int lSize = getUIStructDefault().getDetailViewSize();
        if (lSize > 0) {
            for (int i = 0; i < lSize; i++) {
                Bitmap lBitmap = getUIStructDefault().getDetailBitmap(mDetailViewsPos[i]);
                if (lBitmap != null) {
                    getUIStructDefault().getDetailView(i).setBackgroundDrawable(new BitmapDrawable(lBitmap));
                }
            }
        }
    }


}
