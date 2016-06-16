package org.alexwan.searchword.realm;

import android.app.Application;

import org.alexwan.searchword.utils.Constant;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * RealmManager
 * Created by alexwan on 16/6/15.
 */
public class RealmInstance {

    private static RealmConfiguration mRealmConfig;

    public static void initial(Application context) {
        mRealmConfig = new RealmConfiguration.Builder(context)
                .name(Constant.REALM_NAME)
                .deleteRealmIfMigrationNeeded()
                .build();
    }

    public static Realm getRealm(){
        if(mRealmConfig == null){
            throw new RuntimeException("RealmConfiguration Hasn't Init.");
        }
        return Realm.getInstance(mRealmConfig);
    }


}
