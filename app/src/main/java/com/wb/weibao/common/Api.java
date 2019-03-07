package com.wb.weibao.common;




import android.util.Log;

import com.wb.weibao.net.DownloadResponseBody;
import com.wb.weibao.net.GsonConverterFactory;
import com.wb.weibao.net.LoggerInterceptor;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


/**
 * Created by Administrator on 2017/9/21.
 */

public class Api {

    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJava2CallAdapterFactory.create();
    private static ApiService apiService;


    public static ApiService getApi() {
        if (apiService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(getOkHttpClient())
                    .baseUrl(Link.SEREVE)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            apiService = retrofit.create(ApiService.class);
        }
        return apiService;
    }


  /*  *//**
     * 从 {@link Request#header(String)} 中取出 NoSign
     *
     * @param   {@link Request}
     * @return NoSign
     */
//    private static String obtainNoSignNameFromHeaders(Request request) {
//        List<String> headers = request.headers(NO_SIGN);
//        if (headers == null || headers.size() == 0)
//            return null;
//        if (headers.size() > 1)
//            throw new IllegalArgumentException("Only one NoSign-Name in the headers");
//        return request.header(NO_SIGN);
//    }

    public static OkHttpClient getOkHttpClient(final DownloadResponseBody.DownLoadListener... downLoadListener) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
               .addInterceptor(new LoggerInterceptor("msg", true))
                .connectTimeout(30000L, TimeUnit.MILLISECONDS)
                .readTimeout(15000L, TimeUnit.MILLISECONDS);




        if (downLoadListener.length > 0) {
            builder.addNetworkInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Response originalResponse = chain.proceed(chain.request());
                    DownloadResponseBody downloadResponseBody = new DownloadResponseBody(originalResponse.body(), downLoadListener[0]);
                    return originalResponse.newBuilder().body(downloadResponseBody).build();
                }
            });
        }
        return builder.build();
    }

    public static ApiService getDownLoadApi(String url, final DownloadResponseBody.DownLoadListener downLoadListener) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(getOkHttpClient(downLoadListener))
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build();

        return retrofit.create(ApiService.class);
    }
}
