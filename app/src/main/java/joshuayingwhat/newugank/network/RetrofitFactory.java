package joshuayingwhat.newugank.network;

import android.text.TextUtils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import joshuayingwhat.newugank.AppContextConfig;
import joshuayingwhat.newugank.network.interceptor.HttpLoggerInterceptor;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by JoshuaYingWhat on 2017/12/6.
 */
public class RetrofitFactory {

    private Retrofit.Builder retrofit;
    private Retrofit builder;

    public static RetrofitFactory getInstance() {
        return Holder.RETROFIT;
    }

    private static class Holder {
        static final RetrofitFactory RETROFIT = new RetrofitFactory();
    }

    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();

    private RetrofitFactory() {
        File cacheFile = new File(AppContextConfig.getContext().getCacheDir(), "httpCache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(Configurator.getReadOutTime(), TimeUnit.MILLISECONDS)
                .connectTimeout(Configurator.getConnectOutTime(), TimeUnit.MILLISECONDS)
                .addInterceptor(HttpLoggerInterceptor.
                        loggerinterceptor().
                        setLevel(HttpLoggingInterceptor.Level.BODY)).cache(cache).build();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().client(okHttpClient)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

            if (!TextUtils.isEmpty(Configurator.getBase_url())) {
                builder = retrofit.baseUrl(Configurator.getBase_url()).build();
            } else {
                throw new NullPointerException("base url 为空,请初始化");
            }
        }
    }

    /**
     * 创建api类
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T create(Class<T> clazz) {
        checkObjectNotNull(builder);
        return builder.create(clazz);
    }

    /**
     * 动态更新url
     */

    public <T> T create(String baseUrl, Class<T> clazz) {
        if (retrofit != null) {
            return retrofit.baseUrl(baseUrl).build().create(clazz);
        }
        return null;
    }

    private <T> void checkObjectNotNull(T objet) {
        if (objet == null) {
            throw new NullPointerException("object is null");
        }
    }
}
