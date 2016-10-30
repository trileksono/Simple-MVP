package example.tri.com.simplemvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by tri on 10/30/16.
 */

public abstract class BasePresenter<V extends BaseView> {

    @Nullable
    private V view;

    public void attachView(V view) {
        this.view = view;
    }

    public void detachView() {
        this.view = null;
    }

    @Nullable
    public V getView() {
        return view;
    }

    public abstract Bundle getStatus();

    public abstract void loadStatus(Bundle bundle);
}
