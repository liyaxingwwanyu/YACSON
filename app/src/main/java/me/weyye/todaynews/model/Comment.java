package me.weyye.todaynews.model;

import java.util.List;

/**
 * Created by Administrator on 2016/11/22 0022.
 */
public class Comment {


    /**
     * text : 这样的人才必须保留，
     * digg_count : 604
     * reply_data : {"reply_list":[]}
     * reply_count : 0
     * create_time : 1479863275
     * user : {"avatar_url":"http://p3.pstatp.com/thumb/761/7709628146","user_id":2626885297,"name":"用户22362833"}
     * dongtai_id : 6809928893
     * user_digg : 0
     * id : 52405642898
     */


    public String text;
    public int digg_count;
    public ReplyDataBean reply_data;
    public int reply_count;
    public int create_time;
    /**
     * avatar_url : http://p3.pstatp.com/thumb/761/7709628146
     * user_id : 2626885297
     * name : 用户22362833
     */

    public UserBean user;
    public long dongtai_id;
    public int user_digg;
    public long id;

    public static class ReplyDataBean {
        public List<?> reply_list;
    }

    public static class UserBean {
        public String avatar_url;
        public long user_id;
        public String name;
    }
}
