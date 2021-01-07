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
public class MyThread extends Thread {

    /**
     * 主线程的Handler
     */
    private Handler mainHander;

    public Handler subHandler;

    public MyThread(Handler mainHander) {
        this.mainHander = mainHander;
    }

    @Override
    public void run() {
        super.run();
        Log.d("heshufan", "MyThread");

        //子线程向主线程发送消息
        Message message = new Message();
        message.what = 1;
        mainHander.sendMessage(message);


        //这里运行在子线程，所有perpare只能再走这里调用
        Looper.prepare();
        subHandler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        Log.d("heshufan", "msg from MainThread");
                        break;
                }
            }
        };
        //开启消息队列的循环
        Looper.loop();
    }
}
