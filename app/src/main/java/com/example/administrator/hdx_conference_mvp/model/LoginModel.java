package com.example.administrator.hdx_conference_mvp.model;

import android.content.Context;

import com.example.administrator.hdx_conference_mvp.Url;
import com.example.administrator.hdx_conference_mvp.bean.LoginBean;
import com.example.administrator.hdx_conference_mvp.presenter.LoginPresenter;
import com.example.administrator.hdx_conference_mvp.retrofit.HttpBuilder;
import com.google.gson.Gson;

import java.util.WeakHashMap;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2018/8/27.
 * 这个model加载网络数据，执行耗时操作
 */

public class LoginModel {
   public LoginBean loginBean;
    public WeakHashMap<String, Object> params;



   //登录的网络请求
   public void loginNet(final Context context, String number, String password , final LoginPresenter listener){
       new HttpBuilder(Url.LOGIN)
               .params("username",number)
               .params("password",password)
               .tag(context)
               .post2(new Observer<String>() {
                   @Override
                   public void onSubscribe(Disposable d) {

                   }

                   @Override
                   public void onNext(String value) {
                       if (value != null) {
                           loginBean = new Gson().fromJson(value, LoginBean.class);
                           listener.loginSuccess(loginBean);
                       }
                   }

                   @Override
                   public void onError(Throwable e) {
                       listener.loginFailed(e.toString());
                   }

                   @Override
                   public void onComplete() {

                   }
               });

//        params=new WeakHashMap<>();
//        params.put("username",number);
//        params.put("password",password);
//       RestClient.builder().params(params).build(Url.LOGIN, null, new ISuccess() {
//           @Override
//           public void onSuccess(String response) {
//               if (response!=null){
//                   loginBean=new Gson().fromJson(response,LoginBean.class);
//                   listener.loginSuccess(loginBean);
//               }
//           }
//       }, new IError() {
//           @Override
//           public void onError(int code, String msg) {
//               listener.loginFailed(msg.toString());
//           }
//       },null).get();

   }



}
