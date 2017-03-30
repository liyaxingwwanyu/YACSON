package collection;

import java.util.Arrays;
import java.util.List;

import contrler.UILayoutConfigDefault;


/**
 * Created by sks on 2016/6/16.
 */
public class UILayoutConfigForCollection extends UILayoutConfigDefault {

    public static final String mCtlIDs[] = {"collection.CollectionTitleCtl", "collection.CollectionCtl"
    };
    //控件(单元)
    public static final String mIDs[] = {"CollectionTitle", "CollectionCtl"};
    public static final List<String> mIDsForList = Arrays.asList(mIDs);
    //子控件
    public static final String mDetailViewIDs[][] = {
            {"personal_collection_title_bar_back_btn", "personal_center_title_bar_content_tv", "personal_center_title_bar_edit_btn"},
            {"personal_collection_list_add_view_ll", "personal_collection_group_scroll_view", "personal_collection_list_null_rl"},
    };
    //子控件的图片
    public static final String mDetailDrawableIDs[][] = {{},
            {"personal_collection_site_operating_box_thumb_up_default", "personal_collection_site_operating_box_thumb_up_selected",
                    "zuoyoo_wf_recycerview_item_video_icon", "wf_recycerview_item_game_icon", "wf_recycerview_item_h5_icon", "wf_recycerview_item_predictions_icon"}
    };
    //子控件有状态的的图片
    public static final String mDetailStateDrawableIDs[][] = {{}, {}};
    //子控件的字符串
    public static final String mStringIDs[][] = {{"Edit", "Cancel", "Delete"}, {"DELETE_SUCCESS", "DELETE_FAILURE"}
    };

    //子控件Item
    private static final String mItemForDetailViewsIDs[] = {
            "wf_item_for_list_type_version_one"
    };
    public static final String mItemForDetailViewsIDsForIndex[] = {
            mIDs[1] + mSplit + mDetailViewIDs[1][0]
    };
    public static final List<String> mItemForDetailViewsIDsForIndexForList = Arrays.asList(mItemForDetailViewsIDsForIndex);
    public static final String mItemDetailViewsForDetailViewsIDs[][] = {
            {"wf_item_for_list_type_iv", "wf_item_for_list_type_tv", "zuoyoo_wf_recycerview_item_abstracts",
                    "zuoyoo_wf_recycerview_item_publication_name", "wf_item_for_list_group_ll",
                    "wf_item_for_list_group_one_iv", "wf_item_for_list_group_two_iv", "wf_item_for_list_group_three_iv",
                    "zuoyoo_wf_recycerview_item_video_icon_iv", "zuoyoo_wf_recycerview_item_abstracts_divider",
                    "personal_collection_list_choose_iv", "personal_collection_content_list_layout_rl", "personal_collection_list_delete_iv"
            }

    };
    public static final String mItemDetailViewsForDetailImageIDs[][] = {
            {}
    };

    //单元状态
    public static final String mState[] = {"back", "ScrollBottom", "LoadArticle"};

    public static final List<String> mStateForList = Arrays.asList(mState);

    @Override
    protected String[] getIDs() {
        return mIDs;
    }

    @Override
    protected String[] getItemForDetailViewsIDsForIndex() {
        return mItemForDetailViewsIDsForIndex;
    }

    @Override
    protected List<String> getIDsForList() {
        return mIDsForList;
    }

    @Override
    protected String[][] getDetailViewIDs() {
        return mDetailViewIDs;
    }

    @Override
    protected String[] getCtlIDs() {
        return mCtlIDs;
    }

}
