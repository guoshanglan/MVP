package com.example.administrator.hdx_conference_mvp.model;

import android.content.Context;

import com.example.administrator.hdx_conference_mvp.Url;
import com.example.administrator.hdx_conference_mvp.bean.LoginBean;
import com.example.administrator.hdx_conference_mvp.presenter.LoginPresenter;
import com.example.administrator.hdx_conference_mvp.retrofit.HttpBuilder;
import com.example.administrator.hdx_conference_mvp.retrofit.interfaces.Fair;
import com.example.administrator.hdx_conference_mvp.retrofit.interfaces.Success;
import com.google.gson.Gson;

/**
 * Created by Administrator on 2018/8/27.
 * 这个model加载网络数据，执行耗时操作
 */

public class LoginModel {
   public LoginBean loginBean;



   //登录的网络请求
   public void loginNet(final Context context, String number, String password , final LoginPresenter listener){
       new HttpBuilder(Url.LOGIN)
               .params("username",number)
               .params("password",password)
               .tag(context)
               .success(new Success() {
                   @Override
                   public void Success(String model) {

                       if (model!=null){
                            loginBean=new Gson().fromJson(model,LoginBean.class);
                           listener.loginSuccess(loginBean);
                       }


                   }
               })
               .error(new Fair() {
                   @Override
                   public void Error(Object... values) {
                       listener.loginFailed(values.toString());
                   }
               }).post();


   }



}
