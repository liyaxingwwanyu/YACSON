package Z_MajorStructure.contrler;

import android.view.View;

/**
 * Created by sheenly on 16/3/22.
 *
 */
public class UIDataDefault {
    public static final String mIDs[] = {"ImageLoad", "PageLoad", "PageChange", "SensorOrientationChange", "PageTouch", "UnlockCircle", "ItemClick", "SlidingDrawer", "ShowAbstracts",
            "PatternListener", "ScreenOff", "onResume", "onPause", "WebViewFontChange", "CheckedChanged", "TextWatcher", "FocusChange", "LongClick", "ItemChange", "Dialog", "WFTransparent", "HideLoginTitle"};//0-21
    public static final String mStates[] = {"Failed", "Down", "Up", "Move", "Cancel", "BottomPullUp", "TimeOutOfFour", "Opened", "Closed"//8
            , "Start", "Detected", "LeftSlide", "RightSlide", "Scroll", "PosExChange", "Finished", "WFList", "WFStreaming", "Web", "Coolook", "OnCilck", "LoginTitleHide", "LoginTitleShow","getLoginModel"};//0-22
    private String mCurID;
    private String mCurState;
    private String mUri;
    private String mFromPage;
    private View mView;
    private int mPos;
    private float mDistance;
    private float mX, mY;

    public String getFromPage() {
        return mFromPage;
    }

    public void setFromPage(String mFromPage) {
        this.mFromPage = mFromPage;
    }

    public void setCurID(String aCurID) {
        mCurID = aCurID;
    }

    public void setCurState(String aCurState) {
        mCurState = aCurState;
    }

    public void setUri(String aUri) {
        mUri = aUri;
    }

    public void setView(View aView) {
        mView = aView;
    }

    public void setPos(int aPos) {
        mPos = aPos;
    }

    public void setDistance(float aDistance) {
        mDistance = aDistance;
    }

    public void setXY(float aX, float aY) {
        mX = aX;
        mY = aY;
    }

    public String getCurID() {
        return mCurID;
    }

    public String getCurState() {
        return mCurState;
    }

    public String getUri() {
        return mUri;
    }

    public View getView() {
        return mView;
    }

    public int getPos() {
        return mPos;
    }

    public float getDistance() {
        return mDistance;
    }

    public float getX() {
        return mX;
    }

    public float getY() {
        return mY;
    }
}
