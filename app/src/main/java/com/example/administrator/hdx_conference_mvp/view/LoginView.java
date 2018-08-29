package com.example.administrator.hdx_conference_mvp.view;

import android.content.Context;

import com.example.administrator.hdx_conference_mvp.activity.LoginActivity;
import com.example.administrator.hdx_conference_mvp.bean.LoginBean;

/**
 * Created by Administrator on 2018/8/27.
 */

//登录的view，用来更新UI
public interface LoginView {
    String getNumber();
    String getPass();
    Context getContext();
    LoginActivity getActivity();

    /**
     * 用户登录成功跳转失败
     * @param loginBean
     */
    void setLoginsuccessful(LoginBean loginBean);

    /**
     * 微信登录成功
     * @param str
     */
    void setLoginWx(String str);

    /**
     * 登录失败
     * @param str
     */
    void setFailedError(String str);

}
