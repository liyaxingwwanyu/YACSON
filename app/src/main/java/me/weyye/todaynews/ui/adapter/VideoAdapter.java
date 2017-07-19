package me.weyye.todaynews.ui.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import me.weyye.todaynews.R;
import me.weyye.todaynews.model.News;
import me.weyye.todaynews.model.Video;
import me.weyye.todaynews.theme.colorUi.util.ColorUiUtil;
import me.weyye.todaynews.ui.view.EasyJCVideoPlayer;
import me.weyye.todaynews.utils.ImageLoaderUtils;
import me.weyye.todaynews.utils.VideoPathDecoder;

/**
 * Created by Administrator on 2017/2/8 0008.
 */

public class VideoAdapter extends BaseQuickAdapter<News> {
    public VideoAdapter(List<News> data) {
        super(R.layout.item_video_list, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, final News news) {
        ColorUiUtil.changeTheme(baseViewHolder.convertView, mContext.getTheme());
        final EasyJCVideoPlayer videoPlayer = baseViewHolder.getView(R.id.videoPlayer);
        ImageLoaderUtils.displayAvatar(news.media_avatar_url, (ImageView) baseViewHolder.getView(R.id.ivAvatar));
        ImageLoaderUtils.displayImage(news.image_url, videoPlayer.thumbImageView);
        videoPlayer.thumbImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        baseViewHolder
//                .setText(R.id.tvDuration, news.video_duration_str)
                .setText(R.id.tvFrom, news.source)
                .setText(R.id.tvCommentCount, news.comments_count + "");
        videoPlayer.titleTextView.setText(news.title);
        videoPlayer.setDurationText(news.video_duration_str);
        if (news.video == null) {
            //由于地址加密，所以抽出一个类专门解析播放地址
            VideoPathDecoder decoder = new VideoPathDecoder() {
                @Override
                public void onSuccess(Video s) {
                    news.video = s;
                    setPlayer(videoPlayer, news);
                }

                @Override
                public void onDecodeError(Throwable e) {

                }
            };
            decoder.decodePath(news.source_url);
        } else {
            setPlayer(videoPlayer, news);
        }

    }

    private void setPlayer(JCVideoPlayerStandard videoPlayer, News news) {
        videoPlayer.setUp(news.video.main_url, JCVideoPlayer.SCREEN_LAYOUT_LIST, news.title);
    }
}
