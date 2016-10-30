package example.tri.com.simplemvp.retrofit;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import example.tri.com.simplemvp.Apps;
import example.tri.com.simplemvp.BuildConfig;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Created by tri on 10/9/16.
 */

public class Service {
    private static final String CACHE_CONTROL = "Cache-Control";

    public API getApi() {
        return mRetrofit().create(API.class);
    }

    public Retrofit mRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(API.URL_BASE)
                .client(mClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private OkHttpClient mClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor())
                .addInterceptor(whenOfflineCacheInterceptor())
                .addNetworkInterceptor(cacheInterceptor())
                .connectTimeout(API.CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(API.WRITE_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(API.READ_TIME_OUT, TimeUnit.SECONDS)
                .cache(buatCache())
                .build();
    }

    private static Cache buatCache() {
        Cache cache = null;
        try {
            cache = new Cache(new File(Apps.getInstance().getCacheDir(), "http-cache"), 10 * 1024 * 1024); // 10 MB
        } catch (Exception e) {
            Timber.e(e, "Error creating cache file");
        }
        return cache;
    }

    private static HttpLoggingInterceptor httpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor =
                new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        Timber.d(message);
                    }
                });
        httpLoggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.HEADERS : HttpLoggingInterceptor.Level.NONE);
        return httpLoggingInterceptor;
    }

    public static Interceptor cacheInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());

                // re-write response header to force use of cache
                CacheControl cacheControl = new CacheControl.Builder()
                        .maxAge(2, TimeUnit.MINUTES)
                        .build();

                return response.newBuilder()
                        .header(CACHE_CONTROL, cacheControl.toString())
                        .build();
            }
        };
    }

    public static Interceptor whenOfflineCacheInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();


                if (!Apps.memilikiNetwork()) {
                    CacheControl cacheControl = new CacheControl.Builder()
                            .maxStale(7, TimeUnit.DAYS)
                            .build();

                    request = request.newBuilder()
                            .cacheControl(cacheControl)
                            .build();
                }
                return chain.proceed(request);
            }
        };
    }
}
