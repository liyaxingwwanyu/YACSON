package me.weyye.todaynews.base;

public interface Presenter<V> {

    void attachView(V view);

    void detachView();

}
