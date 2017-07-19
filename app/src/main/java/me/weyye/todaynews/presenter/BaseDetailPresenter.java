package me.weyye.todaynews.presenter;

import me.weyye.todaynews.base.AppClient;
import me.weyye.todaynews.base.BasePresenter;
import me.weyye.todaynews.base.SubscriberCallBack;
import me.weyye.todaynews.model.CommentList;
import me.weyye.todaynews.model.NewsDetail;
import me.weyye.todaynews.view.IBaseDetailView;

/**
 * Created by Administrator on 2016/11/24 0024.
 */

public class BaseDetailPresenter extends BasePresenter<IBaseDetailView> {
    public BaseDetailPresenter(IBaseDetailView mvpView) {
        super(mvpView);
    }

    public void getComment(String group_id, String item_id, int pageNow) {
        int offset = (pageNow - 1) * 10;
        addSubscription(AppClient.getApiService().getComment(group_id, item_id, offset + "", "10"), new SubscriberCallBack<CommentList>() {
            @Override
            protected void onSuccess(CommentList response) {
                mvpView.onGetCommentSuccess(response);
            }

        });
    }

    public void getNewsDetail(String url) {
        addSubscription(AppClient.getApiService().getNewsDetail(url), new SubscriberCallBack<NewsDetail>() {
            @Override
            protected void onSuccess(NewsDetail response) {
                mvpView.onGetNewsDetailSuccess(response);
            }

        });
    }
}
