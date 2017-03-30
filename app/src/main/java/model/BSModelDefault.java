package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import observer.ObservableDefault;

/**
 * Created by sheenly on 16/3/23.
 */
public class BSModelDefault extends ObservableDefault implements ICommand {
    public static LinkedList<Object> items = new LinkedList<Object>();
    protected List<String> mPathList;
    protected List<Integer> mTypeList;
    protected List<String> mTitleList;
    protected List<String> mAbstractsList;
    protected List<String> mPackageList;
    protected String mCurTitle;
    protected String mCurAbstracts;
    protected int mPosForReal, mPos;

    protected Integer mPointPos[][];
    protected Integer mPointValue[][];

    public BSModelDefault() {
        mPathList = new ArrayList<String>();
        mTypeList = new ArrayList<Integer>();
        mTitleList = new ArrayList<String>();
        mAbstractsList = new ArrayList<String>();
        mPackageList = new ArrayList<String>();
    }

    public int getTotalCount() {
        return 0;
    }

    public int getCount() {
        if (mPathList != null) {
            return mPathList.size();
        }
        return 0;
    }

    public int getPosForTotal() {
        return 0;
    }

    public int getPosForReal() {
        return mPosForReal;
    }

    public int getRealPos(int aPosForTotal) {
        return aPosForTotal % getCount();
    }

    public Integer getType(int aPos) {
        if (mTypeList != null && aPos < mTypeList.size()) {
            return mTypeList.get(aPos);
        }
        return null;
    }

    public String getPath(int aPos) {
        if (mPathList != null && aPos < mPathList.size()) {
            return mPathList.get(aPos);
        }
        return null;
    }

    public String getTitle(int aPos) {
        if (mTitleList != null && aPos < mTitleList.size()) {
            return mTitleList.get(aPos);
        }
        return null;
    }

    public String getAbstracts(int aPos) {
        if (mAbstractsList != null && aPos < mAbstractsList.size()) {
            return mAbstractsList.get(aPos);
        }
        return null;
    }

    public List<String> getPackageList() {
        return mPackageList;
    }

    public List<String> getPathList() {
        return mPathList;
    }

    public String getCurTitle() {
        return mCurTitle;
    }

    public String getCurAbstracts() {
        return mCurAbstracts;
    }

    public void setCurPos(int aPosForTotal) {
        mPosForReal = getRealPos(aPosForTotal);
    }


    public void setPos(int aPos, int aPosForRea) {
        mPos = aPos;
        mPosForReal = aPosForRea;
    }

    @Override
    public void execute() {

    }

    public Integer[][] getPointPos() {
        return mPointPos;
    }

    public Integer[][] getPointValue() {
        return mPointValue;
    }

}
