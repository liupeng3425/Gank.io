package edu.uestc.peng.gankio.item;

/**
 * Created by Peng on 2016/5/22.
 * news
 */
public class News {
    private String _id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;

//    public News(String _id, String createdAt, String desc, String publishedAt, String source, String type, String url, boolean used, String who) {
//        this._id = _id;
//        this.createdAt = createdAt;
//        this.desc = desc;
//        this.publishedAt = publishedAt;
//        this.source = source;
//        this.type = type;
//        this.url = url;
//        this.used = used;
//        this.who = who;
//    }

    public String get_id() {
        return _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getSource() {
        return source;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public boolean isUsed() {
        return used;
    }

    public String getWho() {
        return who;
    }
}
