package me.weyye.todaynews.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/18 0018.
 */

public class Title implements Serializable{
    public String TitleStr;
    public String TitleCode;

    public Title(String titleStr, String titleCode) {
        TitleStr = titleStr;
        TitleCode = titleCode;
    }
}
