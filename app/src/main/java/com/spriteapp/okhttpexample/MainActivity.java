package com.spriteapp.okhttpexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.spriteapp.okhttpexample.api.API;
import com.spriteapp.okhttpexample.model.CardModel;
import com.spriteapp.okhttpexample.model.CardResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static final String APP_KEY = "204f631d06000";

    //Please input test card number
    public static final String CARD_NUM = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void check(View view) {
        checkCardInfo();
    }

    private void checkCardInfo() {
        //通过ApiService获取call请求对象
        Call<CardResponse> cardInfoCall = API.getInstance().mService.getCardInfo(APP_KEY, CARD_NUM);
        //请求服务器
        cardInfoCall.enqueue(new Callback<CardResponse>() {
            /**
             * 网络请求成功回调此方法
             */
            @Override
            public void onResponse(@Nullable Call<CardResponse> call, @Nullable Response<CardResponse> response) {
                if (response == null) {
                    return;
                }
                //通过body方法获取服务器返回对象
                CardResponse cardResponse = response.body();
                if (cardResponse == null) {
                    return;
                }
                //获取银行卡信息
                CardModel result = cardResponse.getResult();
                //展示数据
            }

            /**
             * 网络请求失败时回调此方法，比如url不正确，请求超时等。
             */
            @Override
            public void onFailure(Call<CardResponse> call, Throwable t) {
            }
        });
    }
}
