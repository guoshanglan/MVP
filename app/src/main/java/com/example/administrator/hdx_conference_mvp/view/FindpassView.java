package com.example.administrator.hdx_conference_mvp.view;

import android.content.Context;

import com.example.administrator.hdx_conference_mvp.activity.FindPassword_Activity;
import com.example.administrator.hdx_conference_mvp.bean.FindPasswordBean;

/**
 * Created by Administrator on 2018/9/5.
 */

public interface FindpassView {
    String getUsername();
    String getNumber();
    Context getContext();
    FindPassword_Activity getActivity();

    /**
     * 用户登录成功跳转失败
     * @param findPasswordBean
     */
    void setsuccessful(FindPasswordBean findPasswordBean);

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
