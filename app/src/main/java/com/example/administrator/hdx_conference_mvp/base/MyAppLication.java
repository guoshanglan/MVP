package com.example.administrator.hdx_conference_mvp.base;

import android.app.Application;

import com.example.administrator.hdx_conference_mvp.Url;
import com.example.administrator.hdx_conference_mvp.retrofit.HttpUtil;
import com.example.administrator.hdx_conference_mvp.utils.SharedUtils;

/**
 * Created by Administrator on 2018/8/27.
 */

public class MyAppLication extends Application {

    public static MyAppLication application;
    public SharedUtils sharedUtils;

    //创建应用
    @Override
    public void onCreate() {
        super.onCreate();
        application=this;
        //初始化sp，用来存储数据
        sharedUtils = new SharedUtils(this);
        //初始化网络框架
        new HttpUtil.SingletonBuilder(this, Url.localhost)
                .build();
    }


    public static MyAppLication getInstance() {
        return application ;
    }

}
