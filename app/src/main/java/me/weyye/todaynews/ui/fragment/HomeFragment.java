package me.weyye.todaynews.ui.fragment;

import android.content.DialogInterface;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.weyye.todaynews.R;
import me.weyye.todaynews.base.BaseFragment;
import me.weyye.todaynews.base.BaseMvpFragment;
import me.weyye.todaynews.listener.OnChannelListener;
import me.weyye.todaynews.model.Channel;
import me.weyye.todaynews.presenter.HomePresenter;
import me.weyye.todaynews.theme.colorUi.util.SharedPreferencesMgr;
import me.weyye.todaynews.ui.adapter.ChannelPagerAdapter;
import me.weyye.todaynews.ui.view.ChannelDialogFragment;
import me.weyye.todaynews.ui.view.colortrackview.ColorTrackTabLayout;
import me.weyye.todaynews.utils.CommonUtil;
import me.weyye.todaynews.utils.ConstanceValue;
import me.weyye.todaynews.view.IHomeView;

import static me.weyye.todaynews.utils.ConstanceValue.TITLE_SELECTED;
import static me.weyye.todaynews.utils.ConstanceValue.TITLE_UNSELECTED;

/**
 * Created by Administrator on 2016/11/17 0017.
 */
public class HomeFragment extends BaseMvpFragment<HomePresenter> implements IHomeView, OnChannelListener {
    @BindView(R.id.feed_top_search_hint)
    TextView feedTopSearchHint;
    @BindView(R.id.tab)
    ColorTrackTabLayout tab;
    @BindView(R.id.icon_category)
    ImageView iconCategory;
    @BindView(R.id.vp)
    ViewPager mVp;
    private ChannelPagerAdapter mTitlePagerAdapter;

    public List<Channel> mSelectedDatas = new ArrayList<>();
    public List<Channel> mUnSelectedDatas = new ArrayList<>();
    private List<BaseFragment> mFragments;
    private Gson mGson = new Gson();

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected View loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_home, null);
    }

    @Override
    protected void bindViews(View view) {
        ButterKnife.bind(this, rootView);
    }

    @Override
    protected void processLogic() {
        getTitleData();
        mFragments = new ArrayList<>();
        for (int i = 0; i < mSelectedDatas.size(); i++) {
            NewsListFragment fragment = NewsListFragment.newInstance(mSelectedDatas.get(i).TitleCode);
            mFragments.add(fragment);
        }
        mTitlePagerAdapter = new ChannelPagerAdapter(getChildFragmentManager(), mFragments, mSelectedDatas);
        mVp.setAdapter(mTitlePagerAdapter);
        mVp.setOffscreenPageLimit(mSelectedDatas.size());

        tab.setTabPaddingLeftAndRight(CommonUtil.dip2px(10), CommonUtil.dip2px(10));
        tab.setupWithViewPager(mVp);
        tab.post(new Runnable() {
            @Override
            public void run() {
                //设置最小宽度，使其可以在滑动一部分距离
                ViewGroup slidingTabStrip = (ViewGroup) tab.getChildAt(0);
                slidingTabStrip.setMinimumWidth(slidingTabStrip.getMeasuredWidth() + iconCategory.getMeasuredWidth());
            }
        });
        //隐藏指示器
        tab.setSelectedTabIndicatorHeight(0);



    }

    /**
     * 获取标题数据
     */
    private void getTitleData() {

        String selectTitle = SharedPreferencesMgr.getString(TITLE_SELECTED, "");
        String unselectTitle = SharedPreferencesMgr.getString(TITLE_UNSELECTED, "");
        if (TextUtils.isEmpty(selectTitle) || TextUtils.isEmpty(unselectTitle)) {
            //本地没有title
            String[] titleStr = getResources().getStringArray(R.array.home_title);
            String[] titlesCode = getResources().getStringArray(R.array.home_title_code);
            //默认添加了全部频道
            for (int i = 0; i < titlesCode.length; i++) {
                String t = titleStr[i];
                String code = titlesCode[i];
                mSelectedDatas.add(new Channel(t, code));
            }

            String selectedStr = mGson.toJson(mSelectedDatas);
            SharedPreferencesMgr.setString(TITLE_SELECTED, selectedStr);
        } else {
            //之前添加过
            List<Channel> selecteData = mGson.fromJson(selectTitle, new TypeToken<List<Channel>>() {
            }.getType());
            List<Channel> unselecteData = mGson.fromJson(unselectTitle, new TypeToken<List<Channel>>() {
            }.getType());
            mSelectedDatas.addAll(selecteData);
            mUnSelectedDatas.addAll(unselecteData);
        }

    }

    @Override
    protected void setListener() {
    }


    @OnClick({R.id.feed_top_search_hint, R.id.icon_category})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_category:
                ChannelDialogFragment dialogFragment = ChannelDialogFragment.newInstance(mSelectedDatas, mUnSelectedDatas);
                dialogFragment.setOnChannelListener(this);
                dialogFragment.show(getChildFragmentManager(), "CHANNEL");
                dialogFragment.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        mTitlePagerAdapter.notifyDataSetChanged();
                        mVp.setOffscreenPageLimit(mSelectedDatas.size());
                        tab.setCurrentItem(tab.getSelectedTabPosition());
                        ViewGroup slidingTabStrip = (ViewGroup) tab.getChildAt(0);
                        //注意：因为最开始设置了最小宽度，所以重新测量宽度的时候一定要先将最小宽度设置为0
                        slidingTabStrip.setMinimumWidth(0);
                        slidingTabStrip.measure(0, 0);
                        slidingTabStrip.setMinimumWidth(slidingTabStrip.getMeasuredWidth() + iconCategory.getMeasuredWidth());

                        //保存选中和未选中的channel
                        SharedPreferencesMgr.setString(ConstanceValue.TITLE_SELECTED, mGson.toJson(mSelectedDatas));
                        SharedPreferencesMgr.setString(ConstanceValue.TITLE_UNSELECTED, mGson.toJson(mUnSelectedDatas));

                    }
                });
                break;
        }
    }


    @Override
    public void onItemMove(int starPos, int endPos) {
        listMove(mSelectedDatas, starPos, endPos);
        listMove(mFragments, starPos, endPos);
    }


    @Override
    public void onMoveToMyChannel(int starPos, int endPos) {
        //移动到我的频道
        Channel channel = mUnSelectedDatas.remove(starPos);
        mSelectedDatas.add(endPos, channel);
        mFragments.add(NewsListFragment.newInstance(channel.TitleCode));
    }

    @Override
    public void onMoveToOtherChannel(int starPos, int endPos) {
        //移动到推荐频道
        mUnSelectedDatas.add(endPos, mSelectedDatas.remove(starPos));
        mFragments.remove(starPos);
    }

    private void listMove(List datas, int starPos, int endPos) {
        Object o = datas.get(starPos);
        //先删除之前的位置
        datas.remove(starPos);
        //添加到现在的位置
        datas.add(endPos, o);
    }

}
