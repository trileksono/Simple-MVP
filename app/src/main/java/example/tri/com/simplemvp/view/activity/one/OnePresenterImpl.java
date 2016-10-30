package example.tri.com.simplemvp.view.activity.one;

import java.util.List;

import example.tri.com.simplemvp.base.BaseView;
import example.tri.com.simplemvp.model.DataSearch;
import example.tri.com.simplemvp.model.SearchBrand;
import example.tri.com.simplemvp.retrofit.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by tri on 10/23/16.
 */

public class OnePresenterImpl implements OnePresenter {

    private BaseView<List<DataSearch>> mBaseView;
    public int maxPage;

    public OnePresenterImpl(BaseView<List<DataSearch>> baseView) {
        this.mBaseView = baseView;
    }

    @Override
    public void requestBrand(String slug, int page) {
        mBaseView.onShowLoading();
        new Service().getApi().getProdukBrand(slug,page).enqueue(new Callback<SearchBrand>() {
            @Override
            public void onResponse(Call<SearchBrand> call, Response<SearchBrand> response) {
                mBaseView.onSuccess(response.body().getData());
                maxPage = response.body().getTotal_page();
                mBaseView.onDismisLoading();
            }

            @Override
            public void onFailure(Call<SearchBrand> call, Throwable t) {
                Timber.e(t,"error , %s","");
                mBaseView.onDismisLoading();
                mBaseView.onError(t.getMessage());
            }
        });
    }
}
