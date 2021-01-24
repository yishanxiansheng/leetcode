package retorfit;

import android.os.Environment;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author heshufan
 * @date 2021-01-19
 */
public class Test {

    public static void main(String[] args) {


        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("https://api.fanyi.baidu.com/api/trans/vip/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

        retrofitGet(request);

        retrofitGetByQuery(request);
        postForm();


    }

    private static void postForm() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("https://en.wikipedia.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);
        Call<String> call = request.postForm("Jurassic Park");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println(response.body().toString());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println("连接失败");
            }
        });
    }

    private static void retrofitGet(GetRequest_Interface request) {
        Call<Translation> call = request.getCall();

        call.enqueue(new Callback<Translation>() {
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {
                System.out.println(response.body().toString());
            }

            @Override
            public void onFailure(Call<Translation> call, Throwable t) {
                System.out.println("连接失败");
            }
        });
    }

    private static void retrofitGetByQuery(GetRequest_Interface request) {
        Call<Translation> call = request.getCalls("apple", "en", "zh", "2015063000000001",
                "1435660288", "f89f9594663708c1605f3d736d01d2d4");

        call.enqueue(new Callback<Translation>() {
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {
                System.out.println(response.body().toString());
            }

            @Override
            public void onFailure(Call<Translation> call, Throwable t) {
                System.out.println("连接失败");
            }
        });
    }

    private static void retrofitForm() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("https://en.wikipedia.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);
        Call<String> call = request.postForm("Jurassic Park");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println(response.body().toString());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println("连接失败");
            }
        });
    }


    private static void PostFile(Retrofit retrofit) {

        //1、创建equestBody
        File file = new File(Environment.getExternalStorageDirectory() + "/imgs", "demo.png");
        RequestBody photoRequestBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);

        //创建Part
        MultipartBody.Part photo = MultipartBody.Part.createFormData("photo", " file.getName()", photoRequestBody);

        //发起请求
        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);
        Call<ResponseBody> call = request.testFileUpload2(photo);
    }


    private static void PostFiles(Retrofit retrofit) {

        //1、创建equestBody
        File file1 = new File(Environment.getExternalStorageDirectory() + "/imgs", "demo1.png");
        File file2 = new File(Environment.getExternalStorageDirectory() + "/imgs", "demo2.png");

        //2、创建多个RequestBody并放入到Images
        RequestBody photoRequestBody1 = RequestBody.create(MediaType.parse("application/octet-stream"), file1);
        RequestBody photoRequestBody2 = RequestBody.create(MediaType.parse("application/octet-stream"), file2);
        Map<String, RequestBody> images = new HashMap<>();
        images.put("images\"; filename=\"" + file1.getName(), photoRequestBody1);
        images.put("images\"; filename=\"" + file1.getName(), photoRequestBody2);

        //3、发起请求
        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);
        Call<ResponseBody> call = request.testFilesUpload(images);
    }
}
