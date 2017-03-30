package collection;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.newcoolook_0329.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import contrler.*;
import contrler.TouchListenerForScrollItem;
import util.AlgorithmUtil;
import util.AnimationTool;
import util.ApplicationUtil;

/**
 * Created by sks on 2016/6/16.
 */
public class CollectionCtl extends UICtlForSingleEvent {

    public static final String ATTR_CONVERSION_ARR[] = {"null", "null", "2", "6", "4", "5"};
    public static final List<String> ATTR_CONVERSION = Arrays.asList(ATTR_CONVERSION_ARR);

    private List<View> mItemViews;
    private List<View> mTouchViews;
    private List<View> mDeleteClickViews;
    private NumPwdListener mClickListener;
    private ViewGroup mViewGroup;
    private DisplayImageOptions mOptions;

    private int mMoveDistance;
    private ObservableScrollView mScrollView;

    private int mState = 0;//0:初始状态（可接收Touch）  1：动画  2：准备删除 3：编辑状态
    private int mPreviousInitTop, mPreviousInitBottom, mPreviousInitLeft, mPreviousInitRight;
    private int mTouchPos;


    public CollectionCtl(UIStructDefault aUIStructDefault) {
        super(aUIStructDefault);
        mTouchListenerDefault = new contrler.TouchListenerForScrollItem();
        mTouchListenerDefault.addObserver(this);
        mItemViews = new ArrayList<View>();
        mTouchViews = new ArrayList<View>();
        mDeleteClickViews = new ArrayList<View>();
    }

    @Override
    public void init() {
        getUIStructDefault().getBSModel().addObserver(this);
        initView();
        initListener();
    }

    private void initView() {
        mViewGroup = (ViewGroup) getUIStructDefault().getDetailView(0);
        mOptions = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).build();
        mMoveDistance = AlgorithmUtil.dip2px(getUIStructDefault().getContext(), 60);
        mScrollView = (ObservableScrollView) getUIStructDefault().getDetailView(1);
    }

    private void initListener() {
        mClickListener = new NumPwdListener();
        mClickListener.addObserver(this);
        mScrollView.addObserver(this);
    }

    @Override
    public void update(Object aObject) {
        super.update(aObject);
        if (aObject instanceof BSModelForRelatedArticles) {
            BSModelForRelatedArticles lBSModelForRelatedArticles = (BSModelForRelatedArticles) aObject;
            if (BSModelForRelatedArticles.mIDs[0].equals(lBSModelForRelatedArticles.getCurID())) {
                if (BSModelForRelatedArticles.mStates[2].equals(lBSModelForRelatedArticles.getCurState())) {
                    mViewGroup.removeAllViews();
                    mItemViews.clear();
                    mTouchViews.clear();
                    mDeleteClickViews.clear();
                    mState = 0;
                }
            }
            if (BSModelForRelatedArticles.mIDs[6].equals(lBSModelForRelatedArticles.getCurID())) {
                if (BSModelForRelatedArticles.mStates[0].equals(lBSModelForRelatedArticles.getCurState())) {
                    load();
                }
                if (BSModelForRelatedArticles.mStates[10].equals(lBSModelForRelatedArticles.getCurState())) {
                    //正常
                    for (int i = 0; i < mItemViews.size(); i++) {
                        View lIcon = mItemViews.get(i).findViewById(getUIStructDefault().getItemDetailViewsForDetailViewsID(0).get(10));
                        if (lIcon.getVisibility() == View.GONE) break;
                        lIcon.setVisibility(View.GONE);
                        View lItemContentView = mTouchViews.get(i);
                        AnimationTool.moveAnimationForAbsolute(lItemContentView, mMoveDistance, 0.0f, 0.0f, 0.0f, 100, true);
                    }
                    mState = 0;
                }
                if (BSModelForRelatedArticles.mStates[11].equals(lBSModelForRelatedArticles.getCurState())) {
                    //编辑
                    if (mState == 2) {
                        mTouchViews.get(mTouchPos).layout(mPreviousInitLeft, mPreviousInitTop, mPreviousInitRight, mPreviousInitBottom);
                    }
                    mState = 3;
                    for (int i = 0; i < mItemViews.size(); i++) {
                        View lIcon = mItemViews.get(i).findViewById(getUIStructDefault().getItemDetailViewsForDetailViewsID(0).get(10));
                        if (lIcon.getVisibility() == View.VISIBLE) break;
                        lIcon.setVisibility(View.VISIBLE);
                        View lItemContentView = mTouchViews.get(i);
                        AnimationTool.moveAnimationForAbsolute(lItemContentView, 0.0f, mMoveDistance, 0.0f, 0.0f, 100, true);
                    }
                }
                if (BSModelForRelatedArticles.mStates[5].equals(lBSModelForRelatedArticles.getCurState())) {
                    ApplicationUtil.showInfo(getUIStructDefault().getContext(), getUIStructDefault().getString(0));
                    mViewGroup.removeAllViews();
                    mItemViews.clear();
                    mTouchViews.clear();
                    mDeleteClickViews.clear();
                    mState = 0;
                    load();
                }
                if (BSModelForRelatedArticles.mStates[6].equals(lBSModelForRelatedArticles.getCurState())) {
                    ApplicationUtil.showInfo(getUIStructDefault().getContext(), getUIStructDefault().getString(1));
                }
                if (BSModelForRelatedArticles.mStates[13].equals(lBSModelForRelatedArticles.getCurState())) {
                    load();
                }
                if (BSModelForRelatedArticles.mStates[14].equals(lBSModelForRelatedArticles.getCurState())) {
                    changeCollectionList(false);
                }
            }
        }
        if (aObject instanceof UIDataDefault) {
            UIDataDefault lUIDataDefault = (UIDataDefault) aObject;
            if (UIDataDefault.mIDs[2].equals(lUIDataDefault.getCurID())) {
                if (UIDataDefault.mStates[13].equals(lUIDataDefault.getCurState())) {
                    if (mState == 0) {
                        int lScrollY = mScrollView.getScrollY();
                        int lHeight = mScrollView.getHeight();
                        int lScrollViewMeasuredHeight = mScrollView.getChildAt(0).getMeasuredHeight();
                        if (lScrollViewMeasuredHeight > 0 && lScrollY + lHeight >= lScrollViewMeasuredHeight) {
                            getUIStructDefault().setCurState(UILayoutConfigForCollection.mState[1]);
                            updateObserver(getUIStructDefault());
                        }
                    }
                }
            }
        }
        if (aObject instanceof View) {
            int lIndex = mDeleteClickViews.indexOf(aObject);
            if (lIndex >= 0) {
                clickDeleteView(lIndex);
            }
        }
    }

    private void changeCollectionList(boolean aHasCollection) {
        int lVisibility = aHasCollection ? View.GONE : View.VISIBLE;
        getUIStructDefault().getDetailView(2).setVisibility(lVisibility);
    }

    private void clickDeleteView(int aIndex) {
        if (getUIStructDefault().getBSModel() instanceof BSModelForRelatedArticles) {
            BSModelForRelatedArticles lBSModelForRelatedArticles = (BSModelForRelatedArticles) getUIStructDefault().getBSModel();
            lBSModelForRelatedArticles.setCurPosForSingleCollection(aIndex);
        }
    }

    private void clickItemView(int aIndex) {
        if (aIndex >= 0) {
            if (getUIStructDefault().getBSModel() instanceof BSModelForRelatedArticles) {
                BSModelForRelatedArticles lBSModelForRelatedArticles = (BSModelForRelatedArticles) getUIStructDefault().getBSModel();
                if (lBSModelForRelatedArticles.isCollectionEdit()) {
                    boolean lIsSelect = lBSModelForRelatedArticles.setCurPosForCollections(aIndex);
                    if (lIsSelect) {
                        ((ImageView) mItemViews.get(aIndex).findViewById(getUIStructDefault().getItemDetailViewsForDetailViewsID(0).get(10))).setImageBitmap(getUIStructDefault().getDetailBitmap(1));
                    } else {
                        ((ImageView) mItemViews.get(aIndex).findViewById(getUIStructDefault().getItemDetailViewsForDetailViewsID(0).get(10))).setImageBitmap(getUIStructDefault().getDetailBitmap(0));
                    }
                } else {
                    lBSModelForRelatedArticles.setCurPos(aIndex);
                    getUIStructDefault().setCurState(UILayoutConfigForCollection.mState[2]);
                    updateObserver(getUIStructDefault());
                }
            }
        }
    }

    private void load() {
        if (getUIStructDefault().getBSModel() instanceof BSModelForRelatedArticles) {
            BSModelForRelatedArticles lBSModelForRelatedArticles = (BSModelForRelatedArticles) getUIStructDefault().getBSModel();
            for (int i = mItemViews.size(); i < lBSModelForRelatedArticles.getPosForTotal(); i++) {

                View lView = new View(getUIStructDefault().getContext());
                lView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1));
                lView.setBackgroundResource(R.color.title_unselected);

                View lItemView = View.inflate(getUIStructDefault().getContext(), getUIStructDefault().getItemForDetailViewsID(0), null);

                List<String> lArticle = lBSModelForRelatedArticles.getRelatedArticle(i);
                String lImageUrls = lArticle.get(14);
                ImageView lImageView = (ImageView) lItemView.findViewById(getUIStructDefault().getItemDetailViewsForDetailViewsID(0).get(0));
                TextView lTitle = (TextView) lItemView.findViewById(getUIStructDefault().getItemDetailViewsForDetailViewsID(0).get(1));
                ImageView lVideoIV = (ImageView) lItemView.findViewById(getUIStructDefault().getItemDetailViewsForDetailViewsID(0).get(8));
                TextView lPublication = (TextView) lItemView.findViewById(getUIStructDefault().getItemDetailViewsForDetailViewsID(0).get(3));
                lPublication.setVisibility(View.VISIBLE);

                lTitle.setText(lArticle.get(2) == null ? "" : lArticle.get(2));
                lPublication.setText(lArticle.get(13) == null ? "" : lArticle.get(13));

                String lAttr = lArticle.get(3) != null ? lArticle.get(3) : "";
                lAttr = "3".equals(lArticle.get(7)) ? "2" : lAttr;//临时适配服务端接口：CType==3 为视频
                int lBitmapPos = ATTR_CONVERSION.indexOf(lAttr);
                Bitmap lBitmap = lBitmapPos >= 0 ? getUIStructDefault().getDetailBitmap(lBitmapPos) : null;
                lVideoIV.setImageBitmap(lBitmap);
                lVideoIV.setVisibility(lBitmap == null ? View.GONE : View.VISIBLE);

                if (TextUtils.isEmpty(lImageUrls)) {
                    lTitle.setMaxLines(2);
                    lImageView.setVisibility(View.GONE);
                    View lDivider = lItemView.findViewById(getUIStructDefault().getItemDetailViewsForDetailViewsID(0).get(9));
                    TextView lAbstracts = (TextView) lItemView.findViewById(getUIStructDefault().getItemDetailViewsForDetailViewsID(0).get(2));
                    lDivider.setVisibility(View.VISIBLE);
                    lAbstracts.setVisibility(View.VISIBLE);
                    lAbstracts.setText(lArticle.get(12) == null ? "" : lArticle.get(12));

                } else {
                    String[] lImageUrlArr = lImageUrls.split("\\|");
                    if (lImageUrlArr.length >= 3) {
                        lTitle.setMaxLines(1);
                        lImageView.setVisibility(View.GONE);
                        View lImageGroup = lItemView.findViewById(getUIStructDefault().getItemDetailViewsForDetailViewsID(0).get(4));
                        lImageGroup.setVisibility(View.VISIBLE);
                        ImageView lOneIV = (ImageView) lItemView.findViewById(getUIStructDefault().getItemDetailViewsForDetailViewsID(0).get(5));
                        ImageView lTwoIV = (ImageView) lItemView.findViewById(getUIStructDefault().getItemDetailViewsForDetailViewsID(0).get(6));
                        ImageView lThreeIV = (ImageView) lItemView.findViewById(getUIStructDefault().getItemDetailViewsForDetailViewsID(0).get(7));
                        lOneIV.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        lTwoIV.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        lThreeIV.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        ImageLoader.getInstance().displayImage(lImageUrlArr[0], lOneIV, mOptions);
                        ImageLoader.getInstance().displayImage(lImageUrlArr[1], lTwoIV, mOptions);
                        ImageLoader.getInstance().displayImage(lImageUrlArr[2], lThreeIV, mOptions);
                    } else {
                        lTitle.setMaxLines(3);
                        lImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        ImageLoader.getInstance().displayImage(lImageUrlArr[0], lImageView, mOptions);
                    }
                }
                mViewGroup.addView(lItemView);
                mItemViews.add(lItemView);
                mViewGroup.addView(lView);

                View lItemContentView = lItemView.findViewById(getUIStructDefault().getItemDetailViewsForDetailViewsID(0).get(11));
                lItemContentView.setOnTouchListener(mTouchListenerDefault);
                mTouchViews.add(lItemContentView);
                View lItemDeleteView = lItemView.findViewById(getUIStructDefault().getItemDetailViewsForDetailViewsID(0).get(12));
                lItemDeleteView.setOnClickListener(mClickListener);
                mDeleteClickViews.add(lItemDeleteView);
            }
            changeCollectionList(lBSModelForRelatedArticles.getPosForTotal() > 0);
        }
    }


    @Override
    protected void onDownAfter() {
        super.onDownAfter();
        if (mState == 2) {
            //删除状态下Item不接收Touch事件
            ((TouchListenerForScrollItem) mTouchListenerDefault).setUseEvent(false);
            super.onCancel(null);
            return;
        }
        ((TouchListenerForScrollItem) mTouchListenerDefault).setUseEvent(true);
        mPreviousInitTop = mInitTop;
        mPreviousInitBottom = mInitBottom;
        mPreviousInitLeft = mInitLeft;
        mPreviousInitRight = mInitRight;
        mTouchPos = mTouchViews.indexOf(mCurTouchView);

    }

    @Override
    protected void onMoveAfter() {
        super.onMoveAfter();
        if (mState == 0 && mInitX - mCurX > 10) {
            mState = 1;
            mScrollView.requestDisallowInterceptTouchEvent(true);
        }
        if (mState == 1) {
            int lDisplacement = (int) (mCurX - mInitX);
            mCurTouchView.layout(mInitLeft + lDisplacement, mInitTop, mInitRight + lDisplacement, mInitBottom);
        }
    }

    @Override
    protected void onUpAfter() {
        super.onUpAfter();
        if (mState == 0 || mState == 3) {
            clickItemView(mTouchPos);
        }
        if (mState == 1) {
            if (mInitX - mCurX > 200) {
                mState = 2;
                mCurTouchView.layout(mInitLeft - mMoveDistance, mInitTop, mInitRight - mMoveDistance, mInitBottom);
            } else {
                mState = 0;
                mCurTouchView.layout(mInitLeft, mInitTop, mInitRight, mInitBottom);
            }
        }
        ((TouchListenerForScrollItem) mTouchListenerDefault).setUseEvent(false);
    }

    @Override
    protected void onCancelAfter() {
        super.onCancelAfter();
        if (mState == 1 || mState == 2) {
            mState = 0;
            mTouchViews.get(mTouchPos).layout(mPreviousInitLeft, mPreviousInitTop, mPreviousInitRight, mPreviousInitBottom);
        }
    }

    @Override
    public void clear() {
        mScrollView.removeObserver(this);
        mScrollView = null;
        getUIStructDefault().getBSModel().removeObserver(this);
        if (mClickListener != null) {
            mClickListener.removeObserver(this);
            mClickListener = null;
        }
        if (mTouchListenerDefault != null) {
            mTouchListenerDefault.removeObserver(this);
            mTouchListenerDefault = null;
        }
        mViewGroup = null;
        mOptions = null;
        super.clear();
    }
}
