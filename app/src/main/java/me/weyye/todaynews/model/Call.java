package me.weyye.todaynews.model;

/**
 * Created by Administrator on 2017/4/20 0020.
 */

public class Call {
    public String title;
    public String desc;
    public String image;
    public String url;

    @Override
    public String toString() {
        return "Call{" +
                "title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", image='" + image + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
