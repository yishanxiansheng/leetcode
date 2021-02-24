package com.noodle.leetcode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.ArrayMap;
import android.util.Log;
import android.util.LruCache;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.noodle.leetcode.router.Route;
import com.noodle.leetcode.view.MyAdapter;
import com.noodle.leetcode.view.MyRecyclerView;

import com.noodle.leetcode.okhttp.HttpDns;
import com.noodle.leetcode.okhttp.LoggingInterceptor;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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

@Route(path = "app/main")
public class MainActivity extends AppCompatActivity {

    MyRecyclerView recyclerView;
    ViewStub viewStub;
     Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("heshufan", "onCreate");
        Looper.myLooper();
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter());
        viewStub = findViewById(R.id.view_stub);
        button = findViewById(R.id.button);
        View view = viewStub.inflate();
        TextView textView = view.findViewById(R.id.textView);
        textView.setText("ViewStub");
        button.post(new Runnable() {
            @Override
            public void run() {
                System.out.println("1heshufan" + button.getHeight());
                System.out.println("1heshufan" + button.getMeasuredHeight());
            }
        });
        button.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                System.out.println("2heshufan" + button.getHeight());
                System.out.println("2heshufan" + button.getMeasuredHeight());
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
                Intent intent = new Intent();
                ComponentName componetName = new ComponentName("com.noodle.testtaskaffinity",
                        "com.noodle.testtaskaffinity.ThridActivity");
                intent.setComponent(componetName);
                startActivity(intent);
            }
        });

        SparseArray<String> stringSparseArray = new SparseArray<>();
        for (int i = 0; i < 13; i++) {
            stringSparseArray.put(i, "heshufan");
        }
        System.out.println(stringSparseArray.get(1));
    }


    public void okhttp() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://github.com/hongyangAndroid")
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

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        button.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                System.out.println("3heshufan" + button.getHeight());
                System.out.println("3heshufan" + button.getMeasuredHeight());
            }
        });
        super.onWindowFocusChanged(hasFocus);
        LinkedHashMap hashMap = new LinkedHashMap();

    }
}
