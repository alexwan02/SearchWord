package org.alexwan.searchword.model;

import io.realm.RealmObject;

/**
 * RealmString
 * Created by alexwan on 16/6/16.
 */
public class RealmString extends RealmObject {
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
