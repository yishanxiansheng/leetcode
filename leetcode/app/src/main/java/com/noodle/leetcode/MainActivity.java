package com.noodle.leetcode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.app.IntentService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.ArrayMap;
import android.util.Log;
import android.util.LruCache;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.noodle.leetcode.router.Route;
import com.noodle.leetcode.view.MyAdapter;
import com.noodle.leetcode.view.MyRecyclerView;
import com.snail.antifake.jni.EmulatorDetectUtil;

import org.greenrobot.eventbus.EventBus;

import com.noodle.leetcode.okhttp.HttpDns;
import com.noodle.leetcode.okhttp.LoggingInterceptor;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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


@Route(path = "app/main")
public class MainActivity extends AppCompatActivity {

    MyRecyclerView recyclerView;
    ViewStub viewStub;
    Button button;

    private ScreentShotListenManager screenShotListenManager;
    private boolean isHasScreenShotListener = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter());
        viewStub = findViewById(R.id.view_stub);
        button = findViewById(R.id.button);
        View view = viewStub.inflate();
        TextView textView = view.findViewById(R.id.textView);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        screenShotListenManager = ScreentShotListenManager.newInstance(this.getApplicationContext());

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
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 3/0;
            }
        });
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
                Log.d("heshufan","uncaughtException");
            }
        });
        thread.start();

        if (EmulatorDetectUtil.isEmulator(this)){
            Toast.makeText(this,"模拟器",Toast.LENGTH_LONG).show();
        }
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
    protected void onResume() {
        super.onResume();
        startScreenShotListen();
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
    }

    public void startService() {
        startService(new Intent(this, MyIntentService.class));
    }

    public int calculate(String s) {
        int pre = 0;
        int sign = 1;
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                res += pre + sign * (s.charAt(i) - '0');
            } else if (s.charAt(i) == '-') {
                sign = -1;
            } else if(s.charAt(i) == '+'){
                sign = 1;
            }
        }
        return  res;
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopScreenShotListen();
    }

    /**
     * 监听
     */
    private void startScreenShotListen() {
        if (!isHasScreenShotListener && screenShotListenManager != null) {
            screenShotListenManager.setListener(new ScreentShotListenManager.OnScreenShotListener() {
                @Override
                public void onShot(String imagePath) {
                    Toast.makeText(MainActivity.this,"截屏",Toast.LENGTH_LONG).show();
                }
            });
            screenShotListenManager.startListen();
            isHasScreenShotListener = true;
        }
    }

    /**
     * 停止监听
     */
    private void stopScreenShotListen() {
        if (isHasScreenShotListener && screenShotListenManager != null) {
            screenShotListenManager.stopListen();
            isHasScreenShotListener = false;
        }
    }
}
