package com.example.administrator.hdx_conference_mvp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.administrator.hdx_conference_mvp.base.BaseActivity;
import com.example.administrator.hdx_conference_mvp.base.BasePresenter;

/**
 * Created by Administrator on 2019/3/12.
 */


//选取本地图片进行二维码扫描
public class scanPictureActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    //返回一个presenter进行网络数据交互
    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
