package me.weyye.todaynews.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2016/11/18 0018.
 */
public class News {


    /**
     * media_name : 澎湃新闻
     * abstract : 新华社消息：中美首脑就朝鲜问题通电话。
     * impression_count : 25411551
     * image_list : []
     * media_avatar_url : http://p3.pstatp.com/large/c080012f1f1a2f94e13
     * external_visit_count : 21269
     * article_type : 1
     * more_mode : false
     * tag : news_world
     * is_favorite : 0
     * has_m3u8_video : 0
     * keywords : 习近平,特朗普,朝鲜局势,朝鲜
     * chinese_tag : 国际
     * has_mp4_video : 0
     * favorite_count : 4247
     * display_url : http://toutiao.com/group/6412411413247377666/
     * article_sub_type : 1
     * tag_url : news_world
     * bury_count : 0
     * title : 习近平与特朗普就朝鲜局势等问题通话
     * datetime : 2017-04-24 14:07
     * has_video : false
     * share_url : http://toutiao.com/group/6412411413247377666/?iid=36385357187&app=news_article
     * id : 6412411413247377666
     * source : 澎湃新闻
     * comment_count : 7
     * article_url : http://t.m.youth.cn/transfer/toutiao/url/3g.youth.cn/rdzx/201704/t20170424_9568032.htm
     * create_time : 1493006405
     * recommend : 0
     * tips : 0
     * aggr_type : 1
     * item_source_url : /item/6412413677739180545/
     * media_url : http://toutiao.com/m51045089537/
     * display_time : 1493006220
     * publish_time : 1493006220
     * go_detail_count : 892550
     * group_flags : 0
     * middle_mode : false
     * display_title :
     * gallary_image_count : 0
     * item_seo_url : /item/6412413677739180545/
     * tag_id : 6412411413247377666
     * source_url : /group/6412411413247377666/
     * article_genre : article
     * large_mode : false
     * item_id : 6412413677739180545
     * natant_level : 0
     * is_digg : 0
     * seo_url : /group/6412411413247377666/
     * repin_count : 4247
     * url : http://t.m.youth.cn/transfer/toutiao/url/3g.youth.cn/rdzx/201704/t20170424_9568032.htm
     * level : 0
     * digg_count : 1
     * behot_time : 1493014043
     * hot : 0
     * preload_web : 2
     * comments_count : 7
     * has_image : false
     * is_bury : 0
     * group_id : 6412411413247377666
     */

    public String media_name;
    @SerializedName("abstract")
    public String abstractX;
    public int impression_count;
    public String media_avatar_url;
    public int external_visit_count;
    public int article_type;
    public boolean more_mode;
    public String tag;
    public int is_favorite;
    public int has_m3u8_video;
    public String keywords;
    public String chinese_tag;
    public int has_mp4_video;
    public int favorite_count;
    public String display_url;
    public int article_sub_type;
    public String tag_url;
    public int bury_count;
    public String title;
    public String datetime;
    public boolean has_video;
    public String share_url;
    public long id;
    public String source;
    public int comment_count;
    public String article_url;
    public long create_time;
    public int recommend;
    public int tips;
    public int aggr_type;
    public String item_source_url;
    public String media_url;
    public long display_time;
    public int publish_time;
    public int go_detail_count;
    public int group_flags;
    public boolean middle_mode;
    public String display_title;
    public int gallary_image_count;
    public String item_seo_url;
    public long tag_id;
    public String source_url;
    public String article_genre;
    public boolean large_mode;
    public long item_id;
    public int natant_level;
    public int is_digg;
    public String seo_url;
    public int repin_count;
    public String url;
    public int level;
    public int digg_count;
    public long behot_time;
    public int hot;
    public int preload_web;
    public int comments_count;
    public boolean has_image;
    public int is_bury;
    public String group_id;
    public String video_duration_str;
    public String image_url;
    public String video_id;
    public List<ImageUrl> image_list;

    public Video video;

}
