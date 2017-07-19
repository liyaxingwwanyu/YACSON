package me.weyye.todaynews.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.orhanobut.logger.Logger;

import butterknife.BindView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import me.weyye.todaynews.R;
import me.weyye.todaynews.model.NewsDetail;
import me.weyye.todaynews.model.Video;
import me.weyye.todaynews.ui.view.EasyJCVideoPlayer;
import me.weyye.todaynews.ui.view.NewsDetailHeaderView;
import me.weyye.todaynews.utils.ConstanceValue;
import me.weyye.todaynews.utils.VideoPathDecoder;

import static me.weyye.todaynews.R.id.videoPlayer;

/**
 * Created by Administrator on 2016/11/24 0024.
 */

public class VideoDetailActivity extends BaseNewsActivity {

    @BindView(videoPlayer)
    EasyJCVideoPlayer mVideoPlayer;
    private NewsDetailHeaderView mHeaderView;

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_video_detail);
        super.loadViewLayout();
    }

    @Override
    public View createHeader() {
        return mHeaderView = new NewsDetailHeaderView(this);
    }

    public static void start(Context context, String url) {
        Intent intent = new Intent(context, VideoDetailActivity.class);
        intent.putExtra(ConstanceValue.URL, url);
        context.startActivity(intent);
    }

    @Override
    protected void onUriLoad(Uri uri) {
        Logger.i(uri.toString());
    }


    @Override
    public void onGetNewsDetailSuccess(NewsDetail response) {
        response.content = "";
        mHeaderView.setDetail(response);

        VideoPathDecoder decoder = new VideoPathDecoder() {
            @Override
            public void onSuccess(Video video) {
                mVideoPlayer.setUp(video.main_url, JCVideoPlayer.SCREEN_LAYOUT_LIST, "");
                mVideoPlayer.startVideo();
            }

            @Override
            public void onDecodeError(Throwable e) {

            }
        };
        decoder.decodePath(response.url);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mVideoPlayer != null) {
            mVideoPlayer.release();
            mVideoPlayer = null;
        }
    }
}
