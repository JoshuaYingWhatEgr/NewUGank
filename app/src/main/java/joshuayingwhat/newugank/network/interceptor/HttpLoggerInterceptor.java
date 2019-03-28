package joshuayingwhat.newugank.network.interceptor;

import android.util.Log;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 网络请求日志
 * @author joshuayingwhat
 */
public class HttpLoggerInterceptor {

    public static HttpLoggingInterceptor loggerinterceptor() {
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("netdata", message);
            }
        });
    }
}
