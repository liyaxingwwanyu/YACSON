package collection;

import android.graphics.Color;
import android.widget.TextView;

import Z_MajorStructure.contrler.ClickCtl;
import Z_MajorStructure.contrler.UIStructDefault;

/**
 * Created by sks on 2016/6/16.
 */
public class CollectionTitleCtl extends ClickCtl {
    private TextView mTextView;
    public CollectionTitleCtl(UIStructDefault aUIStructDefault) {
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
        mTextView = ((TextView) getUIStructDefault().getDetailView(2));
        mTextView.setText(getUIStructDefault().getString(0));
        mTextView.setTextColor(Color.GRAY);
        mTextView.setClickable(false);
    }

    @Override
    protected void initClick() {
        mDetailViewsPos = new Integer[]{0, 2};
        super.initClick();
    }

    @Override
    protected void clickView(int aIndex) {
        switch (aIndex) {
            case 0:
                getUIStructDefault().setCurState(UILayoutConfigForCollection.mState[0]);
                updateObserver(getUIStructDefault());
                break;
            case 1:
                if (getUIStructDefault().getBSModel() instanceof BSModelForRelatedArticles) {
                    BSModelForRelatedArticles lBSModelForRelatedArticles = (BSModelForRelatedArticles) getUIStructDefault().getBSModel();
                    lBSModelForRelatedArticles.setCollectionState();
                }
                break;
        }
    }

    @Override
    public void update(Object aObject) {
        super.update(aObject);
        if (aObject instanceof BSModelForRelatedArticles) {
            BSModelForRelatedArticles lBSModelForRelatedArticles = (BSModelForRelatedArticles) aObject;
            if (BSModelForRelatedArticles.mIDs[6].equals(lBSModelForRelatedArticles.getCurID())) {
                if (BSModelForRelatedArticles.mStates[10].equals(lBSModelForRelatedArticles.getCurState())) {
                    mTextView.setText(getUIStructDefault().getString(0));
                }
                if (BSModelForRelatedArticles.mStates[11].equals(lBSModelForRelatedArticles.getCurState())) {
                    mTextView.setText(getUIStructDefault().getString(1));
                }
                if (BSModelForRelatedArticles.mStates[12].equals(lBSModelForRelatedArticles.getCurState())) {
                    mTextView.setText(getUIStructDefault().getString(2));
                }
                if (BSModelForRelatedArticles.mStates[5].equals(lBSModelForRelatedArticles.getCurState())) {
                    mTextView.setText(getUIStructDefault().getString(0));
                    editClickable(lBSModelForRelatedArticles.getPosForTotal() > 0);
                }
                if (BSModelForRelatedArticles.mStates[13].equals(lBSModelForRelatedArticles.getCurState())) {
                    editClickable(lBSModelForRelatedArticles.getPosForTotal() > 0);
                }
                if (BSModelForRelatedArticles.mStates[14].equals(lBSModelForRelatedArticles.getCurState())) {
                    editClickable(false);
                }
                if (BSModelForRelatedArticles.mStates[0].equals(lBSModelForRelatedArticles.getCurState())) {
                    editClickable(lBSModelForRelatedArticles.getPosForTotal() > 0);
                }
            }
        }
    }

    private void editClickable(boolean aClickable){
        if(aClickable){
            mTextView.setTextColor(Color.argb(0xFF, 0x3b, 0x6d, 0xf8));
        } else {
            mTextView.setTextColor(Color.GRAY);
        }
        mTextView.setClickable(aClickable);
    }

    @Override
    public void clear() {
        getUIStructDefault().getBSModel().removeObserver(this);
        super.clear();
    }
}