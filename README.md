# OkhttpExample
The uses of retrofit, okhttp and MVP

# How to use okhttp ?
This is an example to check your card info use okhttp.

#添加依赖
```
compile 'com.squareup.retrofit2:retrofit:2.3.0'
compile 'com.squareup.retrofit2:converter-gson:2.3.0'
compile 'com.squareup.retrofit2:adapter-rxjava:2.3.0'
```
# API封装
```
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
```

# APIService封装
```
public interface APIService {

    @GET("bank/card/query")
    Call<CardResponse> getCardInfo(@Query("key") String key,
                                   @Query("card") String cardNumber);
}
```
# Model封装
- CardModel
```
public class CardModel {

    private String bank;
    private String bin;
    private int binNumber;
    private String cardName;
    private int cardNumber;
    private String cardType;

	//省略getter setter
}
```
CardResponse
```
public class CardResponse {

    private String msg;
    private int retCode;
    private CardModel result;
    
	//省略getter setter
}
```
# 查询
```
public static final String APP_KEY = "204f631d06000";
    
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
```
![](https://github.com/kuangxiaoguo0123/OkhttpExample/blob/master/screenshot/%E8%AF%B7%E6%B1%82%E7%BB%93%E6%9E%9C.png)

# Sample source code
[https://github.com/kuangxiaoguo0123/OkhttpExample](https://github.com/kuangxiaoguo0123/OkhttpExample)

# More information
[http://blog.csdn.net/kuangxiaoguo0123/article/details/77461425](http://blog.csdn.net/kuangxiaoguo0123/article/details/77461425)



