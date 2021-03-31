package com.noodle.leetcode;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

/**
 * @author heshufan
 * @date 2021/3/7
 */

class MyIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
