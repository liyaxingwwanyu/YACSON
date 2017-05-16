package collection;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.ya.cson.R;

import java.util.HashMap;
import java.util.Map;

import Z_MajorStructure.contrler.UILayoutMrg;
import Z_MajorStructure.contrler.UIStructDefault;
import Z_MajorStructure.model.BSModelDefault;
import Z_MajorStructure.observer.IObserver;
import util.ApplicationUtil;
import util.BSConfig;
import util.Consts;


/**
 * Created by sks on 2016/6/16.
 */
public abstract class ActivityConfigForCollection extends Activity implements IObserver {
    protected View mView;
    private Integer mToIDs[] = {R.id.personal_collection_title_bar_to_view, R.id.personal_collection_list_to_view};
    private Integer mChangeToIDs[] = {null, null};
    private Integer mMasterIDs[] = {R.layout.personal_collection_title_bar_add_view, R.layout.personal_collection_list_add_view};
    private Integer mDetailViewIDs[][] = {
            {R.id.personal_collection_title_bar_back_btn, R.id.personal_center_title_bar_content_tv, R.id.personal_center_title_bar_edit_btn},
            {R.id.personal_collection_list_add_view_ll, R.id.personal_collection_group_scroll_view, R.id.personal_collection_list_null_rl}
    };
    private Integer mDetailDrawableIDs[][] = {{},
            {R.drawable.personal_collection_site_operating_box_thumb_up_default, R.drawable.personal_collection_site_operating_box_thumb_up_selected,
                    R.drawable.zuoyoo_wf_recycerview_item_video_icon, R.drawable.wf_recycerview_item_game_icon, R.drawable.wf_recycerview_item_h5_icon, R.drawable.wf_recycerview_item_predictions_icon}
    };
    private Integer mDetailStateDrawableIDs[][] = {{}, {}};
    private String mStrings[][] = {
            {Consts.EDIT, Consts.CANCEL, Consts.DELETE}, {Consts.DELETE_SUCCESS, Consts.DELETE_FAILURE}
    };

    private Integer mItemForDetailViewsIDs[] = {
            R.layout.personal_collection_list_add_view_item
    };
    private Integer mItemDetailViewsForDetailViewsIDs[][] = {{
            R.id.wf_item_for_list_type_iv,
            R.id.wf_item_for_list_type_tv,
            R.id.zuoyoo_wf_recycerview_item_abstracts,
            R.id.zuoyoo_wf_recycerview_item_publication_name,
            R.id.wf_item_for_list_group_ll,
            R.id.wf_item_for_list_group_one_iv,
            R.id.wf_item_for_list_group_two_iv,
            R.id.wf_item_for_list_group_three_iv,
            R.id.zuoyoo_wf_recycerview_item_video_icon_iv,
            R.id.zuoyoo_wf_recycerview_item_abstracts_divider,
            R.id.personal_collection_list_choose_iv,
            R.id.personal_collection_content_list_layout_rl, R.id.personal_collection_list_delete_iv
    }
    };
    private Integer mItemDetailViewsForDetailImageIDs[][] = {{}
    };
    private UILayoutConfigForCollection mUILayoutConfigForCollection;
    private UILayoutMrg mUILayoutMrg;
    private BSModelForRelatedArticles mBSModelForRelatedArticles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBSModelForRelatedArticles = new BSModelForRelatedArticles(this);
    }

    protected void initUILayoutConfig() {
        mUILayoutConfigForCollection = new UILayoutConfigForCollection();
        mUILayoutConfigForCollection.setIDs(mToIDs, mChangeToIDs, mMasterIDs, mDetailViewIDs, mDetailDrawableIDs, mDetailStateDrawableIDs, mStrings);
        mUILayoutConfigForCollection.setItemIDs(mItemForDetailViewsIDs, mItemDetailViewsForDetailViewsIDs, mItemDetailViewsForDetailImageIDs);
    }

    protected void initUILayoutMrg() {
        mUILayoutMrg = new UILayoutMrg(mUILayoutConfigForCollection, this, mView, this);
    }

    protected void initCreate() {
        Map<String, BSModelDefault> lBSModels = new HashMap<String, BSModelDefault>();
        lBSModels.put(UILayoutConfigForCollection.mIDs[0], mBSModelForRelatedArticles);
        lBSModels.put(UILayoutConfigForCollection.mIDs[1], mBSModelForRelatedArticles);
        mUILayoutMrg.create(new String[]{UILayoutConfigForCollection.mIDs[0], UILayoutConfigForCollection.mIDs[1]}, lBSModels);
        mBSModelForRelatedArticles.collectionList(true);
    }

    @Override
    public void update(Object aObject) {
        if (aObject instanceof UIStructDefault) {
            UIStructDefault lUIStructDefault = (UIStructDefault) aObject;
            String lCurState = lUIStructDefault.getCurState();
            int lStateIndex = UILayoutConfigForCollection.mStateForList.indexOf(lCurState);
            String lCurID = lUIStructDefault.getCurID();
            int lPos = UILayoutConfigForCollection.mIDsForList.indexOf(lCurID);
            switch (lStateIndex) {
                case 0:
                    ApplicationUtil.openActivity(this, BSConfig.mCommandIDs[24], null);
                    finish();
                    break;
                case 1:
                    mBSModelForRelatedArticles.collectionList(false);
                    break;
                case 2:
                    loadArticle();
                    break;
            }
        }
    }

    protected void loadArticle() {
        String lContentUrl = mBSModelForRelatedArticles.getSPForArticle(BSModelForRelatedArticles.SP_FILED[0]);
        String lArticleID = mBSModelForRelatedArticles.getSPForArticle(BSModelForRelatedArticles.SP_FILED[1]);
        String lTitle = mBSModelForRelatedArticles.getSPForArticle(BSModelForRelatedArticles.SP_FILED[2]);
        String lIsH5 = mBSModelForRelatedArticles.getSPForArticle(BSModelForRelatedArticles.SP_FILED[3]);
        String lSourceUrl = mBSModelForRelatedArticles.getSPForArticle(BSModelForRelatedArticles.SP_FILED[15]);
        String lImagePath = mBSModelForRelatedArticles.getSPForArticle(BSModelForRelatedArticles.SP_FILED[5]);
        Map<String, String> lParam = new HashMap<String, String>();
        if (!TextUtils.isEmpty(lContentUrl)) {
            lParam.put("src", lContentUrl);
            lParam.put("share_id", lArticleID);
            lParam.put("title_key", lTitle);
            lParam.put("attr_key", lIsH5);
            lParam.put("source_url_key", lSourceUrl);
            lParam.put("image_path", lImagePath);
        }
        ApplicationUtil.openActivity(ActivityConfigForCollection.this, BSConfig.mCommandIDs[14], lParam);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUILayoutMrg != null) {
            mUILayoutMrg.clear();
            mUILayoutMrg = null;
        }
    }

    @Override
    public void clear() {

    }
}