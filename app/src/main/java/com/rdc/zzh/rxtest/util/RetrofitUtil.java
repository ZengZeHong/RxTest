package com.rdc.zzh.rxtest.util;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by ZengZeHong on 2017/1/7.
 */

public class RetrofitUtil {
    public static RetrofitUtil instance = null;
    private Retrofit mRetrofit;
    private OkHttpClient mOkHttpClient;

    /**
     * 获取单例
     * @return
     */
    public static RetrofitUtil getInstance(){
        if(instance == null){
            synchronized(RetrofitUtil.class){
                if(instance == null){
                    instance = new RetrofitUtil();
                }
            }
        }
        return instance;
    }

    public RetrofitUtil(){
        initOkHttpClient();
        initRetrofit();
    }

    /**
     * 初始化okHttpClient,添加请求头
     */
    private void initOkHttpClient() {
        if(mOkHttpClient == null) {
            mOkHttpClient = new OkHttpClient.Builder().connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(15, TimeUnit.SECONDS)
                    .addNetworkInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request original = chain.request();
                            Request.Builder requestBuilder = original.newBuilder();
                            requestBuilder.addHeader("HOST", ApiUtil.HOST).addHeader("Referer", ApiUtil.URL_HOST_TWO);
                            Request request = requestBuilder.build();
                            return chain.proceed(request);
                        }
                    })
                    .writeTimeout(15, TimeUnit.SECONDS).build();
        }
    }

    private void initRetrofit() {
        mRetrofit = new Retrofit.Builder()
                .client(mOkHttpClient)
                .baseUrl(ApiUtil.URL_HOST_TWO)
                //支持字符串的返回
                .addConverterFactory(ScalarsConverterFactory.create())
                //支持Observable<T>返回值
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    /**
     * 提供相应的网络操作方法
     * @param cla
     * @param <T>
     * @return
     */
    public <T>T createClass(Class<T> cla){
        if(mRetrofit != null)
            return mRetrofit.create(cla);
        return null;
    }
}
