package login;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import Z_MajorStructure.contrler.ClickCtl;
import Z_MajorStructure.contrler.NumPwdListener;
import Z_MajorStructure.contrler.UIStructDefault;
import util.ImageUtil;


/**
 * Created by Administrator on 2016/5/30.
 */
public class PersonalCenterHeadPortraitCtl extends ClickCtl {

    private ImageView mHeadIV;
    private Bitmap mBitmap;

    private int mIndex = -1;
    private Dialog mDialog;

    public PersonalCenterHeadPortraitCtl(UIStructDefault aUIStructDefault) {
        super(aUIStructDefault);
    }

    @Override
    protected void initShow() {
        super.initShow();
        getUIStructDefault().getBSModel().addObserver(this);
        mHeadIV = (ImageView) getUIStructDefault().getDetailView(0);
        mHeadIV.setImageBitmap(getUIStructDefault().getDetailBitmap(0));

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
    }

    @Override
    protected void clickView(int aIndex) {
        if (aIndex == 0) {
            //System.out.println("点击了头像,oooooooooooggggggg");
            mIndex = aIndex;
            showDialog();
        }
    }

    private void showDialog() {
        mDialog = new Dialog(getUIStructDefault().getContext(), getUIStructDefault().getItemDetailViewsForDetailImageID(0).get(0));
        View lView = View.inflate(getUIStructDefault().getContext(), getUIStructDefault().getItemForDetailViewsID(0), null);
        mDialog.setContentView(lView);
        NumPwdListener lPwdListener = new NumPwdListener();
        lPwdListener.addObserver(this);
        mDialog.findViewById(getUIStructDefault().getItemDetailViewsForDetailViewsID(0).get(0)).setOnClickListener(lPwdListener);
        mDialog.findViewById(getUIStructDefault().getItemDetailViewsForDetailViewsID(0).get(2)).setOnClickListener(lPwdListener);
        mDialog.findViewById(getUIStructDefault().getItemDetailViewsForDetailViewsID(0).get(4)).setOnClickListener(lPwdListener);
        mDialog.setCanceledOnTouchOutside(true);
        mDialog.show();
    }

    @Override
    public void update(Object aObject) {
        if (aObject instanceof BSModelForLogin) {
            BSModelForLogin lBSModelForLogin = (BSModelForLogin) aObject;
            if (BSModelForLogin.mIDs[6].equals(lBSModelForLogin.getCurID())) {
                if (BSModelForLogin.mStates[11].equals(lBSModelForLogin.getCurState())) {
                    String lHeadImgPath = getUIStructDefault().getBSModel().getAbstracts(16);
                    if (mBitmap != null) {
                        mHeadIV.setImageBitmap(getUIStructDefault().getDetailBitmap(0));
                        mBitmap.recycle();
                    }
                    mBitmap = ImageUtil.getBitmapForNormalDegree(lHeadImgPath, 100);
                    if (mBitmap != null) {
                        mHeadIV.setImageBitmap(mBitmap);
                        lBSModelForLogin.inputInfo(mBitmap);
                        getUIStructDefault().setCurState(UILayoutConfigForPersonalCenter.mState[3]);
                        updateObserver(getUIStructDefault());
                    }
                }
            }
        }
        if (aObject instanceof View) {
            if (mDialog != null && mDialog.isShowing()) {
                mDialog.dismiss();
                if (mIndex == 0) {
                    int lViewID = ((View) aObject).getId();
                    if (lViewID == getUIStructDefault().getItemDetailViewsForDetailViewsID(0).get(0)) {
                        getUIStructDefault().setCurState(UILayoutConfigForPersonalCenter.mState[4]);
                        updateObserver(getUIStructDefault());
                    }
                    if (lViewID == getUIStructDefault().getItemDetailViewsForDetailViewsID(0).get(2)) {
                        getUIStructDefault().setCurState(UILayoutConfigForPersonalCenter.mState[5]);
                        updateObserver(getUIStructDefault());
                    }
                    mDialog = null;
                    mIndex = -1;
                }
            } else {
                super.update(aObject);
            }
        }
    }

    @Override
    public void clear() {
        getUIStructDefault().getBSModel().removeObserver(this);
        mHeadIV.setImageBitmap(null);
        if (mBitmap != null) {
            mBitmap.recycle();
        }
        if (mDialog != null) {
            if (mDialog.isShowing()) {
                mDialog.dismiss();
            }
            mDialog = null;
        }
        super.clear();
    }
}