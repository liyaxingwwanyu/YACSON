package Z_MajorStructure.contrler;

import android.text.TextUtils;

import java.util.List;
import java.util.Map;

import util.CollectionUtil;


/**
 * Created by Administrator on 2016/4/17.
 */
public abstract class UILayoutConfigDefault {

    public static final String mSplit = "-";

    private Map<String, Integer> mToRIDs;
    private Map<String, Integer> mChangeToRIDs;
    private Map<String, Integer> mMasterRIDs;

    private Map<String, List<Integer>> mDetailViewRIDs;
    private Map<String, List<Integer>> mDetailDrawableRIDs;
    private Map<String, List<Integer>> mDetailStateDrawableRIDs;
    private Map<String, List<String>> mStrings;

    private Map<String, Integer> mItemForDetailViewsRIDs;
    private Map<String, List<Integer>> mItemDetailViewsForDetailViewsRIDs;
    private Map<String, List<Integer>> mItemDetailViewsForDetailImageRIDs;

    public void setIDs(Integer aToRIDs[], Integer aChangeToRIDs[], Integer aMasterRIDs[], Integer aDetailViewRIDs[][], Integer aDetailDrawableRIDs[][], String aStrings[][]) {
        setIDs(aToRIDs, aChangeToRIDs, aMasterRIDs, aDetailViewRIDs, aDetailDrawableRIDs, new Integer[][]{}, aStrings);
    }

    public void setIDs(Integer aToRIDs[], Integer aChangeToRIDs[], Integer aMasterRIDs[], Integer aDetailViewRIDs[][], Integer aDetailDrawableRIDs[][], Integer aDetailStateDrawableRIDs[][], String aStrings[][]) {
        String[] lIDs = getIDs();
        mToRIDs = CollectionUtil.arrayToMap(lIDs, aToRIDs);
        if (mToRIDs == null) {
            return;
        }
        mChangeToRIDs = CollectionUtil.arrayToMap(lIDs, aChangeToRIDs);
        mMasterRIDs = CollectionUtil.arrayToMap(lIDs, aMasterRIDs);
        if (mMasterRIDs == null || mToRIDs.size() != mMasterRIDs.size()) {
            mToRIDs = null;
            mMasterRIDs = null;
            return;
        }
        mDetailViewRIDs = CollectionUtil.arrayToMap(lIDs, aDetailViewRIDs);
        if (mDetailViewRIDs != null) {
            mDetailStateDrawableRIDs = CollectionUtil.arrayToMap(lIDs, aDetailStateDrawableRIDs);
            mDetailDrawableRIDs = CollectionUtil.arrayToMap(lIDs, aDetailDrawableRIDs);
            mStrings = CollectionUtil.arrayToMap(lIDs, aStrings);
        }
    }

    public void setItemIDs(Integer aItemForDetailViewsRIDs[], Integer aItemDetailViewsForDetailViewsRIDs[][], Integer aItemDetailViewsForDetailImageRIDs[][]) {
        String[] lItemForDetailViewsIDsForIndex = getItemForDetailViewsIDsForIndex();
        mItemForDetailViewsRIDs = CollectionUtil.arrayToMap(lItemForDetailViewsIDsForIndex, aItemForDetailViewsRIDs);//key 是 string类型标识
        if (mItemForDetailViewsRIDs == null) {
            return;
        }
        mItemDetailViewsForDetailViewsRIDs = CollectionUtil.arrayToMap(lItemForDetailViewsIDsForIndex, aItemDetailViewsForDetailViewsRIDs);
        mItemDetailViewsForDetailImageRIDs = CollectionUtil.arrayToMap(lItemForDetailViewsIDsForIndex, aItemDetailViewsForDetailImageRIDs);
    }

    // 0: ToRID, 1: ChangeToRID, 2: MasterRID
    public Integer getRID(Integer aType, String aKey) {
        Integer lRID = null;
        switch (aType) {
            case 0:
                lRID = getMapValue(mToRIDs, aKey);
                break;
            case 1:
                lRID = getMapValue(mChangeToRIDs, aKey);
                break;
            case 2:
                lRID = getMapValue(mMasterRIDs, aKey);// 给key 拿layout
                break;
            default:
                break;
        }
        return lRID;
    }

    // 0: DetailViewRID, 1: DetailDrawableRID, 2:DetailStateDrawableRID
    public List<Integer> getDetailRID(Integer aType, String aKey) {
        List<Integer> lDetailRID = null;
        switch (aType) {
            case 0:
                lDetailRID = getMapValue(mDetailViewRIDs, aKey);
                break;
            case 1:
                lDetailRID = getMapValue(mDetailDrawableRIDs, aKey);
                break;
            case 2:
                lDetailRID = getMapValue(mDetailStateDrawableRIDs, aKey);
                break;
            default:
                break;
        }
        return lDetailRID;
    }

    public Integer getItemRID(String aKey) {
        return getMapValue(mItemForDetailViewsRIDs, aKey);
    }

    public List<Integer> getItemDetailViewRID(String aKey) {
        return getMapValue(mItemDetailViewsForDetailViewsRIDs, aKey);
    }

    public List<Integer> getItemDetailImageRID(String aKey) {
        return getMapValue(mItemDetailViewsForDetailImageRIDs, aKey);
    }

    public List<String> getString(String aKey) {
        return getMapValue(mStrings, aKey);
    }

    private <T> T getMapValue(Map<String, T> aMap, String aKey) {
        if (aMap == null || TextUtils.isEmpty(aKey)) {
            return null;
        }
        return aMap.get(aKey);
    }

    public Integer getIDPos(String aID) {
        if (aID == null) {
            return null;
        }
        Integer lPos = getIDsForList().indexOf(aID);
        if (lPos < 0) {
            lPos = null;
        }
        return lPos;
    }

    protected abstract String[] getIDs();

    protected abstract List<String> getIDsForList();

    protected abstract String[] getItemForDetailViewsIDsForIndex();

    protected abstract String[][] getDetailViewIDs();

    protected abstract String[] getCtlIDs();
}
