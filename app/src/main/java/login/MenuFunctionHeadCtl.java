package login;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import collection.UILayoutConfigForMiniSite;
import contrler.ClickCtl;
import contrler.UIStructDefault;
import util.ImageUtil;


/**
 * Created by Administrator on 2016/5/25.
 */
public class MenuFunctionHeadCtl extends ClickCtl {

    private MyRoundImageView mHeadIV;
    private Bitmap mBitmap;

    public MenuFunctionHeadCtl(UIStructDefault aUIStructDefault) {
        super(aUIStructDefault);
    }

    @Override
    public void init() {
        getUIStructDefault().getBSModel().addObserver(this);
        super.init();
    }

    @Override
    protected void initShow() {
        super.initShow();
        ((ImageView) getUIStructDefault().getDetailView(0)).setImageBitmap(getUIStructDefault().getDetailBitmap(0));
        mHeadIV = (MyRoundImageView) getUIStructDefault().getDetailView(1);
        loadInfo();
    }

    private void loadInfo() {
        if (mBitmap != null) {
            mBitmap.recycle();
            mBitmap = null;
        }
        TextView lNameTV = (TextView) getUIStructDefault().getDetailView(2);

        if (!TextUtils.isEmpty(getUIStructDefault().getBSModel().getAbstracts(1))) {
            String lName = getUIStructDefault().getBSModel().getAbstracts(5);
            if (TextUtils.isEmpty(lName)) {
                lName = getUIStructDefault().getBSModel().getAbstracts(9);
                if (TextUtils.isEmpty(lName)) {
                    lName = "";
                }
            }
            lNameTV.setText(lName);
            mHeadIV.setImageBitmap(getUIStructDefault().getDetailBitmap(2));
            String lHeadImgUrl = getUIStructDefault().getBSModel().getAbstracts(4);
            String lHeadImgPath = getUIStructDefault().getBSModel().getAbstracts(16);
            if (!TextUtils.isEmpty(lHeadImgPath)) {
                mBitmap = ImageUtil.getBitmapForNormalDegree(lHeadImgPath, 100);
                if (mBitmap != null) {
                    mHeadIV.setImageBitmap(mBitmap);
                }
            } else if (!TextUtils.isEmpty(lHeadImgUrl)) {
                DisplayImageOptions lOptions = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).build();
                ImageLoader.getInstance().displayImage(lHeadImgUrl, mHeadIV, lOptions);
            }
        } else {
            mHeadIV.setImageBitmap(getUIStructDefault().getDetailBitmap(1));
            lNameTV.setText(getUIStructDefault().getString(0));
        }
    }

    @Override
    protected void initClick() {
        mDetailViewsPos = new Integer[]{0, 1, 2, 4};
        super.initClick();
    }

    @Override
    protected void clickView(int aIndex) {
        if (aIndex == 1 || aIndex == 2) {
            //System.out.println("点击登录,yyyyyyyyyy");

//            getUIStructDefault().setCurState(UILayoutConfigForMaster.mState[7]); //这个是点击登录的
            updateObserver(getUIStructDefault());
        } else if (aIndex == 0) {
            System.out.println("3333333333,yyyyyyyyyy");
            super.clickView(aIndex);
        } else if (aIndex == 3) {
            //触发search
            // System.out.println("搜索,yyyyyyyyyy");
            UIStructDefault IUIStructDefault = new UIStructDefault(null);
            IUIStructDefault.setCurState(UILayoutConfigForMiniSite.mState[12]);
//            IUIStructDefault.setmCurID(UILayoutConfigForMaster.mIDs[5]);
            updateObserver(IUIStructDefault);
        } else if (aIndex == 5) {
            //触发search
            // System.out.println("搜索,yyyyyyyyyy");
            UIStructDefault IUIStructDefault = new UIStructDefault(null);
            IUIStructDefault.setCurState(UILayoutConfigForMiniSite.mState[38]);
            updateObserver(IUIStructDefault);
        }
    }

    @Override
    public void update(Object aObject) {
        super.update(aObject);
        if (aObject instanceof BSModelForLogin) { // model那边触发的。
            BSModelForLogin lBSModelForLogin = (BSModelForLogin) aObject;
            if (BSModelForLogin.mIDs[3].equals(lBSModelForLogin.getCurID())) {
                if (BSModelForLogin.mStates[12].equals(lBSModelForLogin.getCurState())) {
                    loadInfo();
                }
            }
        }
    }

    @Override
    public void clear() {
        getUIStructDefault().getBSModel().removeObserver(this);
        mHeadIV.setImageBitmap(null);
        if (mBitmap != null) {
            mBitmap.recycle();
            mBitmap = null;
        }
        super.clear();
    }
}

