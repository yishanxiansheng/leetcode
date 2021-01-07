package com.noodle.learnhandler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;


/**
 * @author heshufan
 * @date 2020-12-31
 */
public class MyRunnable implements Runnable {

    private Handler mainHandler;
    public Handler subHandler;

    public MyRunnable(Handler mainHandler) {
        //方法1
        this.mainHandler = mainHandler;

    }

    public MyRunnable() {
        //这里的代码其实是运行在主线程
        //方法2
        this.mainHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                //如果这样设置，这里的代码运行在主线程main
                Log.d("heshufan", Thread.currentThread().getName());
            }
        };
    }

    @Override
    public void run() {
        //这里的代码运行在子线程
        Log.d("heshufan:run", "MyRunnable");
        Log.d("heshufan2", Thread.currentThread().getName());
        Message message = new Message();
        message.what = 2;
        mainHandler.sendMessage(message);

        Looper.prepare();
        subHandler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        Log.d("heshufan:run", "msg form MainThread");
                        break;
                }
            }
        };
        Looper.loop();

    }
}
