package com.example.administrator.hdx_conference_mvp.retrofit.utils;

import android.content.Context;

import com.example.administrator.hdx_conference_mvp.BuildConfig;
import com.example.administrator.hdx_conference_mvp.retrofit.cache.CacheProvide;
import com.example.administrator.hdx_conference_mvp.retrofit.interceptor.CacheInterceptor;
import com.example.administrator.hdx_conference_mvp.retrofit.interceptor.DownLoadInterceptor;
import com.example.administrator.hdx_conference_mvp.retrofit.interceptor.HttpLoggingInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by 戴尔 on 2017/11/9.
 */

public class OkhttpProvidede {
    static OkHttpClient okHttpClient;

    public static OkHttpClient okHttpClient(final Context context, String BASE_URL) {
        if (okHttpClient == null) {
            synchronized (OkhttpProvidede.class) {
                if (okHttpClient == null) {
                    OkHttpClient client = new OkHttpClient.Builder()
                            .addInterceptor(new DownLoadInterceptor(BASE_URL))
                            .addNetworkInterceptor(new CacheInterceptor())
                            .cache(new CacheProvide(context).provideCache())
                            .retryOnConnectionFailure(true)
                            .connectTimeout(5, TimeUnit.SECONDS)
                            .readTimeout(8, TimeUnit.SECONDS)
                            .writeTimeout(8, TimeUnit.SECONDS)
                            .build();
                    if (BuildConfig.DEBUG) {//printf logs while  debug
                        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                        client = client.newBuilder().addInterceptor(logging).build();
                    }
                    okHttpClient = client;
                }

            }

        }
        return okHttpClient;

    }
}
