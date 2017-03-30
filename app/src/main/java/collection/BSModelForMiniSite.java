package collection;

import android.content.Context;
import android.content.SharedPreferences;

import contrler.UIDataDefault;
import model.BSModelDefault;
import util.AlgorithmUtil;

/**
 * Created by Administrator on 2016/4/19.
 */
public class BSModelForMiniSite extends BSModelDefault {

    public static final String SP_NAME = "coolook.minisite";
    public static final String SP_FILED[] = {"FontSizePos", "BrowseSelect"};
    public static final String BROWSE_SELECT_TYPES[] = {"Web", "Coolook"};
    private SharedPreferences mSP;
    private String mUrl;
    private float mTitleAlpha;
    private float mAlphaSlideDistance;

    public static final String mIDs[] = {"Alpha","BrowseSelect"};
    public static final String mStates[] = {"Change","Coolook"};
    private String mCurID;
    private String mCurState;

    public BSModelForMiniSite(Context aContext) {
        mSP = aContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        mAlphaSlideDistance = AlgorithmUtil.dip2px(aContext, 100);
    }

    public void setUrl(String aUrl){
        mUrl = aUrl;
    }

    public String getUrl() {
        return mUrl;
    }

    @Override
    public void setCurPos(int aPosForTotal) {
        mPosForReal = aPosForTotal;
        mSP.edit().putInt(SP_FILED[0], mPosForReal).commit();

        UIDataDefault lUIDataDefault = new UIDataDefault();
        lUIDataDefault.setCurID(UIDataDefault.mIDs[13]);
        updateObserver(lUIDataDefault);
    }

    @Override
    public int getPosForReal() {
        mPosForReal = mSP.getInt(SP_FILED[0], 1);
        return super.getPosForReal();
    }

    public void setScrollY(float aScrollY){
        if (aScrollY > mAlphaSlideDistance) {
            aScrollY = mAlphaSlideDistance;
        }
        mTitleAlpha = 1 - aScrollY / mAlphaSlideDistance;

        mCurID = mIDs[0];
        mCurState = mStates[0];
        updateObserver(this);
    }
    public void showCoolookArticle() {
        mCurID = mIDs[1];
        mCurState = mStates[1];
        updateObserver(this);
    }

    public float getTitleAlpha() {
        return mTitleAlpha;
    }

    //写入选择的浏览方式
    public void inputInfo(String aBrowseSelect) {
        mSP.edit().putString(SP_FILED[1], aBrowseSelect).commit();
    }

    public String getCurID() {
        return mCurID;
    }

    public String getCurState() {
        return mCurState;
    }

    public String getSP(String aKey) {
        return mSP.getString(aKey, null);
    }
}
