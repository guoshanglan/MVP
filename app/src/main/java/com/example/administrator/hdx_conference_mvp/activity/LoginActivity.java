package com.example.administrator.hdx_conference_mvp.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.administrator.hdx_conference_mvp.R;
import com.example.administrator.hdx_conference_mvp.base.BaseActivity;
import com.example.administrator.hdx_conference_mvp.base.BasePresenter;
import com.example.administrator.hdx_conference_mvp.bean.LoginBean;
import com.example.administrator.hdx_conference_mvp.presenter.LoginPresenter;
import com.example.administrator.hdx_conference_mvp.view.LoginView;

/**
 * Created by Administrator on 2018/8/27.
 *
 * 这边通过实现loginview的接口，将我们所需要的界面交互数据传递过去
 */

public class LoginActivity extends BaseActivity  implements LoginView{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);

    }



    //需要输入的手机号码
    @Override
    public String getNumber() {
        return null;
    }

    //输入密码
    @Override
    public String getPass() {
        return null;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public LoginActivity getActivity() {
        return this;
    }


    //登录成功返回数据
    @Override
    public void setLoginsuccessful(LoginBean loginBean) {

    }


    @Override
    public void setLoginWx(String str) {

    }

    //登录失败返回数据
    @Override
    public void setFailedError(String str) {

    }


    //返回一个presenter实列对象
    @Override
    public BasePresenter getPresenter() {
        return new LoginPresenter();
    }
}
