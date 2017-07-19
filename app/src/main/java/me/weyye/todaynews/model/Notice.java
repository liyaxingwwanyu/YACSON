package me.weyye.todaynews.model;

/**
 * Created by Administrator on 2016/11/17 0017.
 */
public class Notice {
    public int type;
    public Object content;

    public Notice(int type) {
        this.type = type;
    }

    public Notice(int type, Object content) {
        this.type = type;
        this.content = content;
    }

    public Notice() {
    }
}
