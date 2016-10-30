package example.tri.com.simplemvp.view.activity.main;

import java.util.List;

import example.tri.com.simplemvp.base.BaseView;
import example.tri.com.simplemvp.model.AllBrand;
import example.tri.com.simplemvp.model.DataSearch;
import example.tri.com.simplemvp.retrofit.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by tri on 10/15/16.
 */

public class MainPresenterImpl implements MainPresenter {

    private BaseView<List<DataSearch>> mBaseView;

    public MainPresenterImpl(BaseView<List<DataSearch>> splashView) {
        this.mBaseView = splashView;
    }

    @Override
    public void requestAllBrand() {
        mBaseView.onShowLoading();
        new Service().getApi().getAllBrand().enqueue(new Callback<AllBrand>() {
            @Override
            public void onResponse(Call<AllBrand> call, Response<AllBrand> response) {
                mBaseView.onSuccess(response.body().getData());
                mBaseView.onDismisLoading();
            }

            @Override
            public void onFailure(Call<AllBrand> call, Throwable t) {
                Timber.e(t,"error , %s","");
                mBaseView.onDismisLoading();
                mBaseView.onError(t.getMessage());
            }
        });
    }
}
