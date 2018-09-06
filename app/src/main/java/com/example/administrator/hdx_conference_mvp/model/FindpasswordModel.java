package com.example.administrator.hdx_conference_mvp.model;

import android.content.Context;

import com.example.administrator.hdx_conference_mvp.Url;
import com.example.administrator.hdx_conference_mvp.bean.FindPasswordBean;
import com.example.administrator.hdx_conference_mvp.presenter.FindPasswordPresenter;
import com.example.administrator.hdx_conference_mvp.retrofit.HttpBuilder;
import com.example.administrator.hdx_conference_mvp.retrofit.interfaces.Fair;
import com.example.administrator.hdx_conference_mvp.retrofit.interfaces.Success;
import com.google.gson.Gson;

/**
 * Created by Administrator on 2018/9/5.
 *
 * 找回密码的model，用来处理耗时请求的
 */

public class FindpasswordModel {
    public FindPasswordBean findPasswordBean;

    //登录的网络请求
    public void loginNet(final Context context, String number, String password , final FindPasswordPresenter listener){
        new HttpBuilder(Url.FindPassword)
                .params("username",number)
                .params("password",password)
                .tag(context)
                .success(new Success() {
                    @Override
                    public void Success(String model) {

                        if (model!=null){
                           findPasswordBean=new Gson().fromJson(model,FindPasswordBean.class);
                            listener.findSuccess(findPasswordBean);
                        }


                    }
                })
                .error(new Fair() {
                    @Override
                    public void Error(Object... values) {
                        listener.findFailed(values.toString());
                    }
                }).post();


    }


}
