package example.tri.com.simplemvp.view.activity.detail;

import example.tri.com.simplemvp.base.BaseView;
import example.tri.com.simplemvp.model.DetailProduk;
import example.tri.com.simplemvp.retrofit.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by tri on 10/29/16.
 */

public class DetailPresenterImpl implements DetailPresenter {

    private BaseView<DetailProduk> detailView;

    public DetailPresenterImpl(BaseView<DetailProduk> detailView) {
        this.detailView = detailView;
    }

    @Override
    public void getDetailSlug(String slug) {
        detailView.onShowLoading();
        new Service().getApi().getProdukDetail(slug).enqueue(new Callback<DetailProduk>() {
            @Override
            public void onResponse(Call<DetailProduk> call, Response<DetailProduk> response) {
                detailView.onSuccess(response.body());
                detailView.onDismisLoading();
            }

            @Override
            public void onFailure(Call<DetailProduk> call, Throwable t) {
                Timber.e(t,"error , %s","");
                detailView.onDismisLoading();
                detailView.onError(t.getMessage());
            }
        });
    }
}
