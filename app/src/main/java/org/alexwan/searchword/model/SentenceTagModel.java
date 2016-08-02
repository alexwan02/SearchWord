package org.alexwan.searchword.model;

import io.realm.RealmObject;

/**
 * SentenceTagModel
 * Created by alexwan on 16/6/16.
 */
public class SentenceTagModel extends RealmObject {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
