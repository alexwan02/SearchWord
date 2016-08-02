package org.alexwan.searchword.model;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * TranslationModel
 * Created by alexwan on 16/6/16.
 */
public class TranslationModel extends RealmObject {
    private RealmList<RealmString> translation;
    private String query;
    private String errorCode;

    public RealmList<RealmString> getTranslation() {
        return translation;
    }

    public void setTranslation(RealmList<RealmString> translation) {
        this.translation = translation;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
