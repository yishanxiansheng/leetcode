package com.noodle.leetcode.okhttp;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
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

    private static String URL = "https://api.github.com/markdown/raw";

    public static void main(String[] args) {

        //缓存策略
        CacheControl cacheControl = new CacheControl.Builder()
                .noCache()
                .maxAge(1, TimeUnit.MICROSECONDS)
                .build();


        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .dns(new HttpDns())
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

//        postString(client);
//        postStream(client);
//        postFile(client);
//        postForm(client);
        postMultipartBody(client);
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

    /**
     * 利用post提交string字符串
     * 如果提交的MediaType是json字符串：MediaType.parse("application/json")
     * MSP项目中都是将数据转化为json字符串进行传递的
     * 图片上传是用REtrofit+MultipartBody分块上传
     *
     * @param client
     */
    private static void postString(OkHttpClient client) {

        MediaType mediaType = MediaType.parse("text/x-markdown; charset=utf-8");
        String requestBody = "I am heshufan";
        Request request = new Request.Builder().url(URL)
                .post(RequestBody.create(mediaType, requestBody))
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println(e.getMessage());

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                System.out.println(response.body().string());
            }
        });
    }


    /**
     * post提交流
     *
     * @param client
     */
    private static void postStream(OkHttpClient client) {
        RequestBody requestBody = new RequestBody() {
            @Nullable
            @Override
            public MediaType contentType() {
                return MediaType.parse("text/x-markdown; charset=utf-8");
            }

            @Override
            public void writeTo(@NotNull BufferedSink bufferedSink) throws IOException {
                bufferedSink.writeUtf8("heshufan");
            }
        };
        Request request = new Request.Builder()
                .url(URL)
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println(e.getMessage());

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                System.out.println(response.body().string());
            }
        });
    }

    /**
     * post提交文件
     */
    private static void postFile(OkHttpClient client) {
        MediaType mediaType = MediaType.parse("text/x-markdown; charset=utf-8");
        File file = new File("heshufan.md");
        Request request = new Request.Builder()
                .url(URL)
                .post(RequestBody.create(mediaType, file))
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println(e.getMessage());

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                System.out.println(response.body().string());
            }
        });
    }

    /**
     * post提交表单
     *
     * @param client
     */
    private static void postForm(OkHttpClient client) {
        RequestBody requestBody = new FormBody.Builder()
                .add("search", "Jurassic Park")
                .build();

        Request request = new Request.Builder()
                .url("https://en.wikipedia.org/w/index.php")
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println(e.getMessage());

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                System.out.println(response.body().string());
            }
        });
    }

    /**
     * 提交分块请求
     * MSP项目中都是将数据转化为json字符串进行传递的
     * 图片上传是用REtrofit+MultipartBody分块上传，不过是用的addFormDataPart
     *
     * @param client
     */
    private static void postMultipartBody(OkHttpClient client) {
        MultipartBody multipartBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addPart(
                        Headers.of("Content-Disposition", "form-data; name=\"title\""),
                        RequestBody.create(null, "Square Logo"))
                .addPart(
                        Headers.of("Content-Disposition", "form-data; name=\"image\""),
                        //文件存放的位置
                        RequestBody.create(MediaType.parse("image/png"), new File("website/static/logo-square.png")))
                .build();
        Request request = new Request.Builder()
                .header("Authorization", "Client-ID " + "...")
                .url("https://api.imgur.com/3/image")
                .post(multipartBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println(e.getMessage());

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                System.out.println(response.body().string());
            }
        });
    }
}
