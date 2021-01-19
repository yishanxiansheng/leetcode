package com.noodle.leetcode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.noodle.leetcode.okhttp.HttpDns;
import com.noodle.leetcode.okhttp.LoggingInterceptor;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retorfit.GetRequest_Interface;
import retorfit.Translation;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        retrofitGet();
    }


    private void retrofitGet() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("http://api.fanyi.baidu.com/api/trans/vip/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

        retrofit2.Call<Translation> call = request.getCall();

        call.enqueue(new retrofit2.Callback<Translation>() {
            @Override
            public void onResponse(retrofit2.Call<Translation> call, retrofit2.Response<Translation> response) {
                System.out.println("heshufan" + response.body().toString());
            }

            @Override
            public void onFailure(retrofit2.Call<Translation> call, Throwable t) {
                System.out.println("heshufan" + "连接失败");
            }
        });
    }

    private void okHttpPost() {
        //缓存策略
        CacheControl cacheControl = new CacheControl.Builder()
                .noCache()
                .maxAge(1, TimeUnit.MICROSECONDS)
                .build();


        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .dns(new HttpDns())
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();

        HashMap<String, String> hashMap = new HashMap<>(6);
        hashMap.put("appid", "2015063000000001");
        hashMap.put("salt", "1435660288");
        hashMap.put("sign", "f89f9594663708c1605f3d736d01d2d4");
        hashMap.put("q", "apple");
        hashMap.put("form", "en");
        hashMap.put("to", "zh");
        JSONObject object = new JSONObject(hashMap);
        System.out.println(object.toString());
        RequestBody requestBody = FormBody.create(MediaType.parse("application/x-www-form-urlencoded"), object.toString());
        Request request = new Request.Builder().url("http://api.fanyi.baidu.com/api/trans/vip/translate")
                .post(requestBody)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.code());
                System.out.println(response.body().string());
            }
        });
    }
}
