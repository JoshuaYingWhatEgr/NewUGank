package joshuayingwhat.newugank.network;

import joshuayingwhat.newugank.network.api.GankApi;
import joshuayingwhat.newugank.network.interceptor.HttpLoggerInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by JoshuaYingWhat on 2017/12/6.
 */
public class NetWork {

    public static GankApi gankApi;
    public static OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(HttpLoggerInterceptor.
            LoggerInterceptor().
            setLevel(HttpLoggingInterceptor.Level.BODY)).build();
    public static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    public static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

    public static GankApi getGankApi() {
        if (gankApi == null) {
            Retrofit retrofit = new Retrofit.Builder().client(okHttpClient).baseUrl("http://gank.io/api/")
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            gankApi = retrofit.create(GankApi.class);
        }
        return gankApi;
    }

}
