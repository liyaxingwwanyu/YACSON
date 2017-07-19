package me.weyye.todaynews.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.orhanobut.logger.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.weyye.todaynews.R;
import me.weyye.todaynews.base.BaseActivity;
import me.weyye.todaynews.listener.ItemDragHelperCallBack;
import me.weyye.todaynews.listener.OnChannelDragListener;
import me.weyye.todaynews.model.Channel;
import me.weyye.todaynews.ui.adapter.ChannelAdapter;
import me.weyye.todaynews.utils.ConstanceValue;

import static me.weyye.todaynews.model.Channel.TYPE_MY_CHANNEL;

public class ChannelActivity extends BaseActivity implements OnChannelDragListener {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private List<Channel> mDatas = new ArrayList<>();
    private ChannelAdapter mAdapter;
    private final String[] titles = new String[]{"推荐", "视频", "热点", "社会", "娱乐", "科技", "汽车", "体育", "财经", "军事", "国际", "时尚", "游戏", "旅游", "历史", "探索", "美食", "育儿", "养生", "故事", "美文"};
    private ItemTouchHelper mHelper;

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_channel);
        ButterKnife.bind(this);
    }

    public static void start(Context context, List<Channel> list) {
        start(context, list, -1);
    }

    public static void start(Context context, List<Channel> list, int requestCode) {
        Intent intent = new Intent(context, ChannelActivity.class);
        intent.putExtra(ConstanceValue.DATA, (Serializable) list);
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            activity.startActivityForResult(intent, requestCode);
        }
    }


    @Override
    protected void bindViews() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        mAdapter = new ChannelAdapter(mDatas);
        GridLayoutManager manager = new GridLayoutManager(this, 4);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int itemViewType = mAdapter.getItemViewType(position);
                return itemViewType == TYPE_MY_CHANNEL || itemViewType == Channel.TYPE_OTHER_CHANNEL ? 1 : 4;
            }
        });
        ItemDragHelperCallBack callBack = new ItemDragHelperCallBack(this);
        mHelper = new ItemTouchHelper(callBack);
        mAdapter.setOnChannelDragListener(this);
        //attachRecyclerView
        mHelper.attachToRecyclerView(mRecyclerView);

    }



    @Override
    protected void setListener() {
    }

    @OnClick(R.id.icon_collapse)
    public void onClick(View v) {
        Iterator<Channel> iterator = mDatas.iterator();
        while (iterator.hasNext()) {
            Channel next = iterator.next();
            if (next.getItemType() != TYPE_MY_CHANNEL)
                iterator.remove();
        }
        Intent data = new Intent();
        data.putExtra(ConstanceValue.DATA, (Serializable) mDatas);
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public void onStarDrag(BaseViewHolder baseViewHolder) {
        //开始拖动
        Logger.i("开始拖动");
        mHelper.startDrag(baseViewHolder);
    }

    @Override
    public void onItemMove(int starPos, int endPos) {
//        if (starPos < 0||endPos<0) return;
        Channel startChannel = mDatas.get(starPos);
        //先删除之前的位置
        mDatas.remove(starPos);
        //添加到现在的位置
        mDatas.add(endPos, startChannel);
        mAdapter.notifyItemMoved(starPos, endPos);
    }

    @Override
    public void onMoveToMyChannel(int starPos, int endPos) {

    }

    @Override
    public void onMoveToOtherChannel(int starPos, int endPos) {

    }
}
