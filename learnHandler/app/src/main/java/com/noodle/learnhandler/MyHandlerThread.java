package com.noodle.learnhandler;

import android.os.HandlerThread;
import android.util.Log;

/**
 * @author heshufan
 * @date 2020-12-31
 */
public class MyHandlerThread extends HandlerThread {

    public MyHandlerThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        Log.d("heshufan","MyHandlerThread");
        super.run();
    }
}
