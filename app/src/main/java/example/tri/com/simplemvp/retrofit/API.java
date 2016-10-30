package example.tri.com.simplemvp.retrofit;

import example.tri.com.simplemvp.model.AllBrand;
import example.tri.com.simplemvp.model.DetailProduk;
import example.tri.com.simplemvp.model.Search;
import example.tri.com.simplemvp.model.SearchBrand;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by tri on 10/9/16.
 */

public interface API {

    public static String URL_BASE = "http://ibacor.com/api/";

    public static long CONNECT_TIME_OUT = 15;
    public static long WRITE_TIME_OUT = 15;
    public static long READ_TIME_OUT = 30;

    @GET("gsm-arena?view=brands")
    Call<AllBrand> getAllBrand();

    @GET("gsm-arena?view=search&")
    Call<Search> getSearchProduk(@Query("q") String key);

    @GET("gsm-arena?view=product&")
    Call<DetailProduk> getProdukDetail(@Query("slug") String slug);

    @GET("gsm-arena?view=brand&")
    Call<SearchBrand> getProdukBrand(@Query("slug") String slug, @Query("page") int page);
}
