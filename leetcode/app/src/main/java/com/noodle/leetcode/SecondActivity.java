package com.noodle.leetcode;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.noodle.leetcode.eventbus.MsgEvent;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("heshufan", "onCreate");

        setContentView(R.layout.activity_second);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                ComponentName componetName = new ComponentName("com.noodle.testtaskaffinity",
                        "com.noodle.testtaskaffinity.ThridActivity");
                intent.setComponent(componetName);
                startActivity(intent);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void getMsg(MsgEvent event){

    }
}
