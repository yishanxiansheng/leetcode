package com.example.testmaven.okhttp;

import com.qiniu.android.dns.DnsManager;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;
import okio.GzipSink;
import okio.Okio;

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
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();

        HashMap<String, String> hashMap = new HashMap<>(6);
        hashMap.put("appid", "2015063000000001");
        hashMap.put("salt", "1435660288");
        hashMap.put("sign", "f89f9594663708c1605f3d736d01d2d4");
        hashMap.put("q", "apple");
        hashMap.put("form", "en");
        hashMap.put("to", "zh");
//        JSONObject object = new JSONObject(hashMap);
        RequestBody requestBody = FormBody.create(MediaType.parse("application/x-www-form-urlencoded"), hashMap.toString());
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

    /**
     * 对请求体进行压缩
     *
     * @param body
     * @return
     */
    private static RequestBody gzip(final RequestBody body) {
        return new RequestBody() {
            @Override
            public MediaType contentType() {
                return body.contentType();
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                BufferedSink gzipSink = Okio.buffer(new GzipSink(sink));
                body.writeTo(gzipSink);
                gzipSink.close();
            }
        };
    }
}
