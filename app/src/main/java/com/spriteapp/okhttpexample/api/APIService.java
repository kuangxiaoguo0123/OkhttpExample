package com.spriteapp.okhttpexample.api;

import com.spriteapp.okhttpexample.model.CardResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by kuangxiaoguo on 2017/8/20.
 */

public interface APIService {

    @GET("bank/card/query")
    Call<CardResponse> getCardInfo(@Query("key") String key,
                                   @Query("card") String cardNumber);
}
