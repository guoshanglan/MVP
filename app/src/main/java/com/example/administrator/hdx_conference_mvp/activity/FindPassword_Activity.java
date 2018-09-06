package com.example.administrator.hdx_conference_mvp.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.hdx_conference_mvp.R;
import com.example.administrator.hdx_conference_mvp.base.BaseActivity;
import com.example.administrator.hdx_conference_mvp.base.BasePresenter;
import com.example.administrator.hdx_conference_mvp.bean.FindPasswordBean;
import com.example.administrator.hdx_conference_mvp.presenter.FindPasswordPresenter;
import com.example.administrator.hdx_conference_mvp.view.FindpassView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/5.
 */

public class FindPassword_Activity extends BaseActivity implements FindpassView{

    @BindView(R.id.iv_back)
    public ImageView ivBack;  //返回
    @BindView(R.id.tv_find)
    public TextView tvFind;  //确认调用接口找回
    public FindPasswordPresenter presenter;
    public String username;  //这里暂时写一个姓名，其他的后面再看




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_password);
        ButterKnife.bind(this);
    }







    //点击事件
    @OnClick({R.id.iv_back,R.id.tv_find})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:    //返回
                finish();
                break;

            case R.id.tv_find:    //立即找回




                break;

        }
    }



    @Override
    public BasePresenter getPresenter() {
        if (presenter==null){
            presenter=new FindPasswordPresenter();
        }
        return presenter;
    }



    //调用网络请求需要从view界面用到的数据
    @Override
    public String getNumber() {
        return null;
    }

    @Override
    public String getPass() {
        return null;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public FindPassword_Activity getActivity() {
        return this;
    }


    //找回成功
    @Override
    public void setsuccessful(FindPasswordBean findPasswordBean) {

    }

    @Override
    public void setLoginWx(String str) {

    }

    //找回失败
    @Override
    public void setFailedError(String str) {

    }
}
