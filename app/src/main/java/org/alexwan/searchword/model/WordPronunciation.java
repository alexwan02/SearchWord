package org.alexwan.searchword.model;

import io.realm.RealmObject;

/**
 * WordPronunciation
 * Created by alexwan on 16/6/16.
 */
public class WordPronunciation extends RealmObject {
    private String uk;
    private String us;

    public String getUk() {
        return uk;
    }

    public void setUk(String uk) {
        this.uk = uk;
    }

    public String getUs() {
        return us;
    }

    public void setUs(String us) {
        this.us = us;
    }
}
