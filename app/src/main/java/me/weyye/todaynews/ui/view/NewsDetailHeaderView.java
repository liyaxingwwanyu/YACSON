package me.weyye.todaynews.ui.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import me.weyye.todaynews.R;
import me.weyye.todaynews.model.NewsDetail;
import me.weyye.todaynews.utils.DateUtils;
import me.weyye.todaynews.utils.ImageLoaderUtils;

/**
 * Created by Administrator on 2017/4/24 0024.
 */

public class NewsDetailHeaderView extends FrameLayout {
    @BindView(R.id.tvTitle)
    TextView mTvTitle;
    @BindView(R.id.ivAvatar)
    CircleImageView mIvAvatar;
    @BindView(R.id.tvUserName)
    TextView mTvUserName;
    @BindView(R.id.tvDate)
    TextView mTvDate;
        @BindView(R.id.tvHtmlContent)
    HtmlTextView mTvHtmlContent;
//    @BindView(R.id.webView)
//    ProgressWebView mWebView;

    public NewsDetailHeaderView(Context context) {
        this(context, null);
    }

    public NewsDetailHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NewsDetailHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.header_news_detail, this);
        ButterKnife.bind(this, this);
    }

    public void setDetail(NewsDetail response) {
        mTvTitle.setText(response.title);
        ImageLoaderUtils.displayAvatar(response.media_user.avatar_url, mIvAvatar);
        mTvUserName.setText(response.media_user.screen_name);
        mTvDate.setText(DateUtils.getShortTime(response.publish_time * 1000));
        if (TextUtils.isEmpty(response.content))
            mTvHtmlContent.setVisibility(GONE);
        mTvHtmlContent.setRichText(response.content);
    }

}
