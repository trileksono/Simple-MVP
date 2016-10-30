package example.tri.com.simplemvp;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.squareup.picasso.Picasso;

import timber.log.Timber;

/**
 * Created by tri on 10/9/16.
 */

public class Apps extends Application {

    public static Apps instance;
    public static Picasso sPicasso;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        if(BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree(){
                @Override
                protected String createStackElementTag(StackTraceElement element) {
                    return super.createStackElementTag(element) +" : " + element.getLineNumber();
                }
            });
            Picasso.with(this).setLoggingEnabled(true);
        }
        Timber.i("Launch App");

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Timber.i("Terminate App");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Timber.i("====== Memory low ======");
    }

    public static Apps getInstance(){
        return instance;
    }

    public static boolean memilikiNetwork() {
        return instance.checkNetwork();
    }

    public boolean checkNetwork() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
