package org.alexwan.searchword.model;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * SentenceModel
 * Created by alexwan on 16/6/16.
 */
public class SentenceModel extends RealmObject {
    private String sid;
    private String tts; // audio address
    private String content; // english content
    private String note; // chinese content
    private String love; // the num of like
    private String translation; // the comment of iciba's editor
    private String picture; // small image address
    private String picture2; // large image address
    private String caption; // title of sentence
    private String dateline; //  date of sentence
    private String s_pv;
    private String sp_pv;
    private RealmList<SentenceTagModel> tags; // tags of sentence
    private String fenxiang_img; // image using for share

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getTts() {
        return tts;
    }

    public void setTts(String tts) {
        this.tts = tts;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getLove() {
        return love;
    }

    public void setLove(String love) {
        this.love = love;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPicture2() {
        return picture2;
    }

    public void setPicture2(String picture2) {
        this.picture2 = picture2;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }

    public String getSPv() {
        return s_pv;
    }

    public void setSPv(String s_pv) {
        this.s_pv = s_pv;
    }

    public String getSpPv() {
        return sp_pv;
    }

    public void setSpPv(String sp_pv) {
        this.sp_pv = sp_pv;
    }

    public RealmList<SentenceTagModel> getTags() {
        return tags;
    }

    public void setTags(RealmList<SentenceTagModel> tags) {
        this.tags = tags;
    }

    public String getFenxiangImg() {
        return fenxiang_img;
    }

    public void setFenxiangImg(String fenxiang_img) {
        this.fenxiang_img = fenxiang_img;
    }
}
