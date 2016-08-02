package org.alexwan.searchword;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import org.alexwan.searchword.realm.RealmInstance;

/**
 * WordApplication
 * Created by alexwan on 16/6/15.
 */
public class WordApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // config realm
        initConfig();
    }

    private void initConfig() {
        // config realm
        RealmInstance.initial(this);
        // config fresco
        Fresco.initialize(this);
    }


}
