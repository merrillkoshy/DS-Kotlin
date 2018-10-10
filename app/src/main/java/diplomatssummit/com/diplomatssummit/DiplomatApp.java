package diplomatssummit.com.diplomatssummit;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;


public class DiplomatApp extends Application {

    @Override
    public void onCreate() {

        super.onCreate();

        FlowManager.init(this);
    }
}
