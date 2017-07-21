package me.weyye.todaynews.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerManager;
import me.weyye.todaynews.ui.adapter.VideoAdapter;
import me.weyye.todaynews.utils.ConstanceValue;

/**
 * Created by Administrator on 2017/2/8 0008.
 */

public class VideoListFragment extends NewsListFragment {
    @Override
    protected BaseQuickAdapter createAdapter() {
        return mAdapter = new VideoAdapter(mDatas);
    }


    @Override
    protected void setListener() {
        super.setListener();
        //视频监听
        recyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {

            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
                if (JCVideoPlayerManager.getCurrentJcvd() != null) {
                    JCVideoPlayer videoPlayer = JCVideoPlayerManager.getCurrentJcvd();
                    if (((ViewGroup) view).indexOfChild(videoPlayer) != -1 && videoPlayer.currentState == JCVideoPlayer.CURRENT_STATE_PLAYING) {
                        //当滑动的时，正在播放的视频移除屏幕，取消播放这个视频
                        JCVideoPlayer.releaseAllVideos();
                    }
                }
            }
        });
    }
    //重写父类方法，新闻列表为瀑布流样式， 视频列表为列表样式
    @Override
    protected void processLogic() {
        initCommonRecyclerView(createAdapter(), null);
//        initGridRecyclerView(createAdapter(), null,2);//更换为瀑布流样式
        mTitleCode = getArguments().getString(ConstanceValue.DATA);
//        srl.measure(0, 0);
//        srl.setRefreshing(true);
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isVisibleToUser) {
            JCVideoPlayer.releaseAllVideos();
        }
    }

}
