package com.noodle.learnhandler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHandler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what) {
                    case 1:
                        Log.d("heshufan", "msg form MyThread");
                        break;
                    case 2:
                        Log.d("heshufan", "msg form MyRunnable");
                        break;
                }
            }
        };

        testThread();
        testRunnable();
        testHandlerThread();
    }

    private void testRunnable() {
        //方法1
        //MyRunnable runnable = new MyRunnable(mHandler);

        //方法2
        MyRunnable runnable = new MyRunnable();


        Thread thread = new Thread(runnable);
        thread.start();
        //向子线程发送消息
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Message message = new Message();
        message.what = 1;
        runnable.subHandler.sendMessage(message);
    }

    private void testThread() {
        MyThread thread = new MyThread(mHandler);
        thread.start();

        //向子线程发送消息
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Message message = new Message();
        message.what = 1;
        thread.subHandler.sendMessage(message);
    }

    private void testHandlerThread() {
        MyHandlerThread handlerThread = new MyHandlerThread("heshufan");
        handlerThread.start();

//        Handler mWorkhandler = new Handler(handlerThread.getLooper()){
//            @Override
//            public void handleMessage(@NonNull Message msg) {
//                super.handleMessage(msg);
//                //这里实在HandleThead线程中处理事情，也就是主线程发过来的消息
//                switch (msg.what){
//                    case 1:
//                        Log.d("heshufan:HandlerThread","msg form mainThread");
//                        break;
//                }
//                Log.d("heshufan:HandlerThread",Thread.currentThread().getName());
//            }
//        };

        Handler mWorkhandler = new Handler(handlerThread.getLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {
                switch (message.what) {
                    case 1:
                        Log.d("heshufan:HandlerThread", "msg form mainThread");
                        break;
                }
                Log.d("heshufan:HandlerThread", Thread.currentThread().getName());
                return true;
            }
        }) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                System.out.println("heshufan sssss");
            }
        };

        //给handlerThread发送消息
        Message message = new Message();
        message.what = 1;
        mWorkhandler.sendMessage(message);

    }
}
