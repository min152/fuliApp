package project.first.lal.common.http;

import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import project.first.lal.common.Constants;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpUtils {

    private final String TAG = "HttpUtils";

    public static HttpUtils mHttpUtils;

//    private static String HTTP_URL = "http://192.168.0.109:8080/Fuli_Maven_Webapp/";
    private static String HTTP_URL = "http://114.55.238.210:8080/Fuli_Maven_Webapp/";

    private final int DEFAULT_TIMEOUT = 5;

    private final int CACHE_SIZE = 10 * 1024 * 1024;

    private Retrofit mRetrofit;

    public HttpUtils() {
        //设置缓存路径
        File dirPath = new File(Environment.getExternalStorageDirectory().getPath(), "fuli");
        Cache cache = new Cache(dirPath, CACHE_SIZE);

        OkHttpClient builder = new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)//设置超时时间
                .readTimeout(10, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(10, TimeUnit.SECONDS)//设置写入超时时间
                .cache(cache).build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(HTTP_URL)
                .client(builder)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public static HttpUtils getInstance() {
        synchronized (HttpUtils.class) {
            if (null == mHttpUtils) {
                mHttpUtils = new HttpUtils();
            }
        }
        return mHttpUtils;
    }

    public <T> T getHttpRx(Class c) {
        return (T) mRetrofit.create(c);
    }

    private static class RxInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            //没有网络
            if (1 == Constants.getNetworkType()) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            return null;
        }
    }

}
