package example.tri.com.simplemvp.base;

/**
 * Created by tri on 10/15/16.
 */

public interface BaseView<T> {

    void onShowLoading();
    void onDismisLoading();
    void onSuccess(T data);
    void onError(String error);
}
