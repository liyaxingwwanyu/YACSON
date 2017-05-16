package login;


import Z_MajorStructure.model.BSModelDefault;

/**
 * Created by Administrator on 2017/3/8.
 */

public class BSModelForLoginTitle extends BSModelDefault {
    public static final String mIDs[] = {"LoginTitle"};
    protected String mCurID;
//    protected String mCurState;

    @Override
    public void setCurPos(int aPosForTotal) {
        mPosForReal = aPosForTotal;

        mCurID = mIDs[0];
        updateObserver(this);
    }

    public String getCurID() {
        return mCurID;
    }
//
//    public String getCurState() {
//        return mCurState;
//    }
}
