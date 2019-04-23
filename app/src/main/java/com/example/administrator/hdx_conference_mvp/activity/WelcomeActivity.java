package com.example.administrator.hdx_conference_mvp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.administrator.hdx_conference_mvp.MainActivity;
import com.example.administrator.hdx_conference_mvp.R;
import com.githang.statusbar.StatusBarCompat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/27.
 */

public class WelcomeActivity extends AppCompatActivity {
    @BindView(R.id.bt_jump)
    public Button btJump;  //跳转按钮

    public Handler myHandler; //利用handler来进行跳转
    public boolean isJump = true;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_layout);
        ButterKnife.bind(this);
        if (android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            StatusBarCompat.setStatusBarColor(this, Color.WHITE, true);

        }




        myHandler = new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == 0) {
                    if (isJump) {
                        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                        finish();
                    }
                } else {
                    btJump.setText(msg.what + "s跳转");
                }
                super.handleMessage(msg);
            }
        };

        getTime();
    }


    //点击事件
    @OnClick({R.id.bt_jump,})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_jump:    //返回
                isJump = false;
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
                break;

        }
    }


    //利用线程倒计时
    public void getTime() {

        new Thread() {
            @Override
            public void run() {
                for (int i = 3; i >= 0; i--) {
                    try {
                        Thread.sleep(1000); //设置让线程睡一秒
                        Message message = Message.obtain();
                        message.what = i;
                        myHandler.sendMessage(message);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();


    }


}
