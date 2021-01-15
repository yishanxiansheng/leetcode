package com.example.testmaven.okhttp;

import com.qiniu.android.dns.DnsManager;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author heshufan
 * @date 2021-01-14
 */
public class Test {

    public static void main(String[] args) {

        //缓存策略
        CacheControl cacheControl = new CacheControl.Builder()
                .noCache()
                .maxAge(1, TimeUnit.MICROSECONDS)
                .build();


        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .dns(new HttpDns())
                .connectTimeout(15,TimeUnit.SECONDS)
                .build();

        Request request = new Request.Builder().url("https://github.com/hongyangAndroid")
                .get()
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
                System.out.println(response.body().toString());
            }
        });
    }
}
