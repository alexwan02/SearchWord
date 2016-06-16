package org.alexwan.searchword.model;

import io.realm.RealmObject;

/**
 * word definition
 * Created by alexwan on 16/6/15.
 */
public class WordDefinitionModel extends RealmObject {
    private String pos;     // property of word
    private String defn;    // definition of word

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getDefn() {
        return defn;
    }

    public void setDefn(String defn) {
        this.defn = defn;
    }
}
