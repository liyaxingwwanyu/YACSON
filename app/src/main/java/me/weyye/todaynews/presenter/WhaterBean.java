package me.weyye.todaynews.presenter;

import java.util.List;

/**
 * Created by wty on 2017/7/12.
 */

public class WhaterBean {

    /**
     * status : 0
     * runtime : 150
     * articles : [{"abstracts":"The cabinet has approved an action plan by the Public Health Ministry aimed at cutting the use of antibiotics in the healthcare scheme to reduce drug resistance by half by 2021.","articleid":"57ba63316876bdd87e942df1","attr":1,"author":"","channelid":1,"commentnum":0,"countrycode":"TH","createtime":"2016-08-22T10:51:47.175+08:00","createuid":0,"ctype":0,"endtime":"","id":25089364,"imgcount":0,"keywords":"","langid":"12","latitude":0,"linkurl":"http://hubii.com/article/57ba63316876bdd87e942df1?view=coolook","longitude":0,"medialink":"","publication":{"country":"","hbid":"51652252bbddbd1468000baa","id":6169,"logourl":"http://c758728.r28.cf2.rackcdn.com/587.png","name":"Bangkok Post","url":"http://www.bangkokpost.com/"},"publicationid":6169,"pushnum":0,"pushtime":null,"rank":0,"source":"","sourcetitilepic":"http://773035c32a854b22709b-59f03ca58fa65abf38bad7a503d39066.r46.cf2.rackcdn.com/57ba63316876bdd87e942df1_500x333.jpg","sourceurl":"","starttime":"","status":0,"subtime":"2016-08-22T09:52:00+08:00","subuid":0,"title":"Cabinet nod for plan to cut antibiotic use","titlepic":"http://cdn.img.coolook.org/2016-08-22/113b5f8534b4e48fb02225e2ee1223dc.jpg!240","topicsid":1,"writer":""}]
     */

    private int status;
    private int runtime;
    private List<ArticlesBean> articles;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public List<ArticlesBean> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticlesBean> articles) {
        this.articles = articles;
    }

    public static class ArticlesBean {
        /**
         * abstracts : The cabinet has approved an action plan by the Public Health Ministry aimed at cutting the use of antibiotics in the healthcare scheme to reduce drug resistance by half by 2021.
         * articleid : 57ba63316876bdd87e942df1
         * attr : 1
         * author :
         * channelid : 1
         * commentnum : 0
         * countrycode : TH
         * createtime : 2016-08-22T10:51:47.175+08:00
         * createuid : 0
         * ctype : 0
         * endtime :
         * id : 25089364
         * imgcount : 0
         * keywords :
         * langid : 12
         * latitude : 0
         * linkurl : http://hubii.com/article/57ba63316876bdd87e942df1?view=coolook
         * longitude : 0
         * medialink :
         * publication : {"country":"","hbid":"51652252bbddbd1468000baa","id":6169,"logourl":"http://c758728.r28.cf2.rackcdn.com/587.png","name":"Bangkok Post","url":"http://www.bangkokpost.com/"}
         * publicationid : 6169
         * pushnum : 0
         * pushtime : null
         * rank : 0
         * source :
         * sourcetitilepic : http://773035c32a854b22709b-59f03ca58fa65abf38bad7a503d39066.r46.cf2.rackcdn.com/57ba63316876bdd87e942df1_500x333.jpg
         * sourceurl :
         * starttime :
         * status : 0
         * subtime : 2016-08-22T09:52:00+08:00
         * subuid : 0
         * title : Cabinet nod for plan to cut antibiotic use
         * titlepic : http://cdn.img.coolook.org/2016-08-22/113b5f8534b4e48fb02225e2ee1223dc.jpg!240
         * topicsid : 1
         * writer :
         */

        private String abstracts;
        private String articleid;
        private int attr;
        private String author;
        private int channelid;
        private int commentnum;
        private String countrycode;
        private String createtime;
        private int createuid;
        private int ctype;
        private String endtime;
        private int id;
        private int imgcount;
        private String keywords;
        private String langid;
        private int latitude;
        private String linkurl;
        private int longitude;
        private String medialink;
        private PublicationBean publication;
        private int publicationid;
        private int pushnum;
        private Object pushtime;
        private int rank;
        private String source;
        private String sourcetitilepic;
        private String sourceurl;
        private String starttime;
        private int status;
        private String subtime;
        private int subuid;
        private String title;
        private String titlepic;
        private int topicsid;
        private String writer;

        public String getAbstracts() {
            return abstracts;
        }

        public void setAbstracts(String abstracts) {
            this.abstracts = abstracts;
        }

        public String getArticleid() {
            return articleid;
        }

        public void setArticleid(String articleid) {
            this.articleid = articleid;
        }

        public int getAttr() {
            return attr;
        }

        public void setAttr(int attr) {
            this.attr = attr;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public int getChannelid() {
            return channelid;
        }

        public void setChannelid(int channelid) {
            this.channelid = channelid;
        }

        public int getCommentnum() {
            return commentnum;
        }

        public void setCommentnum(int commentnum) {
            this.commentnum = commentnum;
        }

        public String getCountrycode() {
            return countrycode;
        }

        public void setCountrycode(String countrycode) {
            this.countrycode = countrycode;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public int getCreateuid() {
            return createuid;
        }

        public void setCreateuid(int createuid) {
            this.createuid = createuid;
        }

        public int getCtype() {
            return ctype;
        }

        public void setCtype(int ctype) {
            this.ctype = ctype;
        }

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getImgcount() {
            return imgcount;
        }

        public void setImgcount(int imgcount) {
            this.imgcount = imgcount;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public String getLangid() {
            return langid;
        }

        public void setLangid(String langid) {
            this.langid = langid;
        }

        public int getLatitude() {
            return latitude;
        }

        public void setLatitude(int latitude) {
            this.latitude = latitude;
        }

        public String getLinkurl() {
            return linkurl;
        }

        public void setLinkurl(String linkurl) {
            this.linkurl = linkurl;
        }

        public int getLongitude() {
            return longitude;
        }

        public void setLongitude(int longitude) {
            this.longitude = longitude;
        }

        public String getMedialink() {
            return medialink;
        }

        public void setMedialink(String medialink) {
            this.medialink = medialink;
        }

        public PublicationBean getPublication() {
            return publication;
        }

        public void setPublication(PublicationBean publication) {
            this.publication = publication;
        }

        public int getPublicationid() {
            return publicationid;
        }

        public void setPublicationid(int publicationid) {
            this.publicationid = publicationid;
        }

        public int getPushnum() {
            return pushnum;
        }

        public void setPushnum(int pushnum) {
            this.pushnum = pushnum;
        }

        public Object getPushtime() {
            return pushtime;
        }

        public void setPushtime(Object pushtime) {
            this.pushtime = pushtime;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getSourcetitilepic() {
            return sourcetitilepic;
        }

        public void setSourcetitilepic(String sourcetitilepic) {
            this.sourcetitilepic = sourcetitilepic;
        }

        public String getSourceurl() {
            return sourceurl;
        }

        public void setSourceurl(String sourceurl) {
            this.sourceurl = sourceurl;
        }

        public String getStarttime() {
            return starttime;
        }

        public void setStarttime(String starttime) {
            this.starttime = starttime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getSubtime() {
            return subtime;
        }

        public void setSubtime(String subtime) {
            this.subtime = subtime;
        }

        public int getSubuid() {
            return subuid;
        }

        public void setSubuid(int subuid) {
            this.subuid = subuid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitlepic() {
            return titlepic;
        }

        public void setTitlepic(String titlepic) {
            this.titlepic = titlepic;
        }

        public int getTopicsid() {
            return topicsid;
        }

        public void setTopicsid(int topicsid) {
            this.topicsid = topicsid;
        }

        public String getWriter() {
            return writer;
        }

        public void setWriter(String writer) {
            this.writer = writer;
        }

        public static class PublicationBean {
            /**
             * country :
             * hbid : 51652252bbddbd1468000baa
             * id : 6169
             * logourl : http://c758728.r28.cf2.rackcdn.com/587.png
             * name : Bangkok Post
             * url : http://www.bangkokpost.com/
             */

            private String country;
            private String hbid;
            private int id;
            private String logourl;
            private String name;
            private String url;

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public String getHbid() {
                return hbid;
            }

            public void setHbid(String hbid) {
                this.hbid = hbid;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLogourl() {
                return logourl;
            }

            public void setLogourl(String logourl) {
                this.logourl = logourl;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}