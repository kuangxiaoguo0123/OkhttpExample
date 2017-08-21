package com.spriteapp.okhttpexample.api;

import com.spriteapp.okhttpexample.constant.Constant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kuangxiaoguo on 2017/8/20.
 */

public class API {

    private static final int TIME_OUT = 8;
    private static API mInstance;
    public APIService mService;

    public static API getInstance() {
        if (mInstance == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                    .build();
            mInstance = new API(client);
        }
        return mInstance;
    }

    private API(OkHttpClient client) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        mService = retrofit.create(APIService.class);
    }
}
