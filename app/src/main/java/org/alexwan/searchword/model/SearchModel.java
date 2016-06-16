package org.alexwan.searchword.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * SearchModel
 * Created by alexwan on 16/6/15.
 */
public class SearchModel extends RealmObject {
    @PrimaryKey
    private long key = System.currentTimeMillis();
    private String content;
    private int type;

    public void setKey(long key) {
        this.key = key;
    }

    public long getKey() {
        return key;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(SearchType type) {
        this.type = type.getValue();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * SearchModel Type
     */
    public enum SearchType {
        HISTORY(1),         // search history
        SUGGESTION(2);      // search sugesstion
        private int value;

        SearchType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
