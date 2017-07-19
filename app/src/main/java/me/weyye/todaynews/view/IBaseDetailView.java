package me.weyye.todaynews.view;

import me.weyye.todaynews.model.CommentList;
import me.weyye.todaynews.model.NewsDetail;

/**
 * Created by Administrator on 2016/11/24 0024.
 */

public interface IBaseDetailView {
    void onGetCommentSuccess(CommentList response);

    void onGetNewsDetailSuccess(NewsDetail response);
}
