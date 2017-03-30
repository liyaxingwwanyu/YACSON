package contrler;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import model.BSModelDefault;

/**
 * Created by sheenly on 16/3/7.
 */
public class UIStructDefault {
    private UILayoutConfigDefault mUILayoutConfigDefault;
    private Context mContext;
    private Resources mResources;
    private View mView;
    public ViewGroup mToContain;
    private ViewGroup mChangeToContain;
    private ViewGroup mMasterContain;
    private List<View> mDetailViews;
    private List<Bitmap> mDetailBitmaps;
    private List<Drawable> mDetailStateDrawables;
    private List<String> mStrings;
    private String mCurCase;
    private String mCurID;


    private String mCurState;

    private List<Integer> mItemForDetailViewsID;
    private List<List<Integer>> mItemDetailViewsForDetailViewsID;
    private List<List<Integer>> mItemDetailViewsForDetailImageID;
    private BSModelDefault mBSModelDefault;


    public UIStructDefault(UILayoutConfigDefault aUILayoutConfigDefault) {
        mUILayoutConfigDefault = aUILayoutConfigDefault;
        mDetailViews = new ArrayList<View>();
        mDetailBitmaps = new ArrayList<Bitmap>();
        mDetailStateDrawables = new ArrayList<Drawable>();
        mStrings = new ArrayList<String>();
        mItemForDetailViewsID = new ArrayList<Integer>();
        mItemDetailViewsForDetailViewsID = new ArrayList<List<Integer>>();
        mItemDetailViewsForDetailImageID = new ArrayList<List<Integer>>();
    }

    public void setView(Context aContext, View aView) {
        mContext = aContext;
        mView = aView;
        mResources = mContext.getResources();
    }

    public void setmCurID(String mCurID) {
        this.mCurID = mCurID;
    }

    //控件
    public void setID(String aID) {
        Integer lToLayoutID = mUILayoutConfigDefault.getRID(0, aID);
        Integer lChangeToLayoutID = mUILayoutConfigDefault.getRID(1, aID);
        Integer lAddLayoutID = mUILayoutConfigDefault.getRID(2, aID);
        if (lToLayoutID == null || lAddLayoutID == null) {
            return;
        }
        mCurID = aID;
        mToContain = (ViewGroup) mView.findViewById(lToLayoutID);
        mChangeToContain = null;
        if (lChangeToLayoutID != null) {
            mChangeToContain = (ViewGroup) mView.findViewById(lChangeToLayoutID);
        }
        mMasterContain = (ViewGroup) View.inflate(mContext, lAddLayoutID, null);
        mDetailViews.clear();
        mItemForDetailViewsID.clear();
        mItemDetailViewsForDetailViewsID.clear();
        List<Integer> lDetailViewIDs = mUILayoutConfigDefault.getDetailRID(0, aID);
        if (lDetailViewIDs != null) {
            for (int i = 0; i < lDetailViewIDs.size(); i++) {
                View lView = mMasterContain.findViewById(lDetailViewIDs.get(i));
                mDetailViews.add(lView);
                int lIDIndex = mUILayoutConfigDefault.getIDsForList().indexOf(mCurID);
                String lItemID = mCurID + UILayoutConfigDefault.mSplit + mUILayoutConfigDefault.getDetailViewIDs()[lIDIndex][i];//
                Integer lItemRID = mUILayoutConfigDefault.getItemRID(lItemID);// mCurID 是一个key组成部分 来确定是否含有item。和之前的map中key对应
                mItemForDetailViewsID.add(lItemRID);
                List<Integer> lItemDetailRID = null;
                List<Integer> lItemDetailImageRID = null;
                if (lItemRID != null) {
                    lItemDetailRID = mUILayoutConfigDefault.getItemDetailViewRID(lItemID);
                    lItemDetailImageRID = mUILayoutConfigDefault.getItemDetailImageRID(lItemID);
                }
                mItemDetailViewsForDetailViewsID.add(lItemDetailRID);
                mItemDetailViewsForDetailImageID.add(lItemDetailImageRID);
            }
        }
        mDetailBitmaps.clear();
        List<Integer> lDetailDrawableIDs = mUILayoutConfigDefault.getDetailRID(1, aID);
        if (lDetailDrawableIDs != null) {
            for (int i = 0; i < lDetailDrawableIDs.size(); i++) {
                Bitmap lBitmap = BitmapFactory.decodeResource(mResources, lDetailDrawableIDs.get(i));
                mDetailBitmaps.add(lBitmap);
            }
        }
        mDetailStateDrawables.clear();
        List<Integer> lDetailStateDrawableIDs = mUILayoutConfigDefault.getDetailRID(2, aID);
        if (lDetailStateDrawableIDs != null) {
            for (int i = 0; i < lDetailStateDrawableIDs.size(); i++) {
                Drawable lDrawable = mContext.getResources().getDrawable(lDetailStateDrawableIDs.get(i));
                mDetailStateDrawables.add(lDrawable);
            }
        }

        mStrings.clear();
        List<String> lStrings = mUILayoutConfigDefault.getString(aID);
        if (lStrings != null) {
            mStrings.addAll(lStrings);
        }
    }

    public void setID(String aID, BSModelDefault aBSModelDefault) {
        setID(aID);
        mBSModelDefault = aBSModelDefault;
    }

    // 0: ToContain , 1: ChangeToContain , 2: MasterContain
    public ViewGroup getContain(Integer aType) {
        ViewGroup lContain = null;
        switch (aType) {
            case 0:
                lContain = mToContain;
                break;
            case 1:
                lContain = mChangeToContain;
                break;
            case 2:
                lContain = mMasterContain;
                break;
            default:
                break;
        }
        return lContain;
    }

    public void setCurCase(String aCurCase) {
        mCurCase = aCurCase;
    }

    public String getCurCase() {
        return mCurCase;
    }

    public Context getContext() {
        return mContext;
    }

    public List<View> getDetailViews() {
        return mDetailViews;
    }

    public List<Bitmap> getDetailBitmaps() {
        return mDetailBitmaps;
    }

    public List<Drawable> getDetailStateDrawables() {
        return mDetailStateDrawables;
    }

    public int getDetailViewSize() {
        if (mDetailViews != null)
            return mDetailViews.size();
        return 0;
    }

    public View getDetailView(int aPos) {
        if (mDetailViews != null && aPos < mDetailViews.size()) {
            return mDetailViews.get(aPos);
        }
        return null;
    }

    public Bitmap getDetailBitmap(int aPos) {
        if (mDetailBitmaps != null && aPos < mDetailBitmaps.size()) {
            return mDetailBitmaps.get(aPos);
        }
        return null;
    }

    public Drawable getDetailStateDrawable(int aPos) {
        if (mDetailStateDrawables != null && aPos < mDetailStateDrawables.size()) {
            return mDetailStateDrawables.get(aPos);
        }
        return null;
    }

    public String getString(int aPos) {
        if (mStrings != null && aPos < mStrings.size()) {
            return mStrings.get(aPos);
        }
        return null;
    }

    public Integer getItemForDetailViewsID(int aPos) {
        if (mItemForDetailViewsID != null && aPos < mItemForDetailViewsID.size()) {
            return mItemForDetailViewsID.get(aPos);
        }
        return null;
    }

    public List<Integer> getItemDetailViewsForDetailViewsID(int aPos) {
        if (mItemDetailViewsForDetailViewsID != null && aPos < mItemDetailViewsForDetailViewsID.size()) {
            return mItemDetailViewsForDetailViewsID.get(aPos);
        }
        return null;
    }

    public List<Integer> getItemDetailViewsForDetailImageID(int aPos) {
        if (mItemDetailViewsForDetailImageID != null && aPos < mItemDetailViewsForDetailImageID.size()) {
            return mItemDetailViewsForDetailImageID.get(aPos);
        }
        return null;
    }

    public String getCurID() {
        return mCurID;
    }

    public void setCurState(String aCurState) {
        mCurState = aCurState;
    }

    public String getCurState() {
        return mCurState;
    }

    public BSModelDefault getBSModel() {
        return mBSModelDefault;
    }

    public void clear() {
        if (mDetailViews != null) {
            mDetailViews.clear();
            mDetailViews = null;
        }
        if (mDetailBitmaps != null) {
            for (Bitmap lBitmap : mDetailBitmaps) {
                if (lBitmap != null) {
                    lBitmap.recycle();
                }
            }
            mDetailBitmaps.clear();
        }
        if (mDetailStateDrawables != null) {
            for (Drawable lDrawable : mDetailStateDrawables) {
                if (lDrawable != null) {
                    lDrawable.setCallback(null);
                }
            }
            mDetailStateDrawables.clear();
        }
        if (mStrings != null) {
            mStrings.clear();
            mStrings = null;
        }
        mUILayoutConfigDefault = null;
        mContext = null;
        mResources = null;
        mView = null;
        mToContain = null;
        mChangeToContain = null;
        mMasterContain = null;
    }
}
