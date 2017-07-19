package me.weyye.todaynews.view;

import java.util.List;

import me.weyye.todaynews.model.News;

/**
 * Created by Administrator on 2016/11/17 0017.
 */
public interface INewsListView {
    void onGetNewsListSuccess(List<News> response);

    void onError();
}
