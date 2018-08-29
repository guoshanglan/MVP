package com.example.administrator.hdx_conference_mvp.presenter;

import android.text.TextUtils;

import com.example.administrator.hdx_conference_mvp.base.BasePresenter;
import com.example.administrator.hdx_conference_mvp.bean.LoginBean;
import com.example.administrator.hdx_conference_mvp.inter.LoginListener;
import com.example.administrator.hdx_conference_mvp.model.LoginModel;
import com.example.administrator.hdx_conference_mvp.view.LoginView;

/**
 * Created by Administrator on 2018/8/27.
 */

//登录的中间者，用来与view交互数据的
public class LoginPresenter extends BasePresenter<LoginView> implements LoginListener{

    private LoginModel loginModel; //用来处理网络请求等耗时操作的


    public LoginPresenter(){
       this.loginModel=new LoginModel();
    }


    //通过activity点击的时候调用这个网络请求方法
    public void loginNet(){
        loginModel.loginNet(mView.getContext(),mView.getNumber(),mView.getPass(),this);
    }

    //登录成功,这边的mview，已经在basePresenter中定义过了
    @Override
    public void loginSuccess(LoginBean loginBean) {
        if (loginBean!=null)
        mView.setLoginsuccessful(loginBean);

    }

    @Override
    public void loginSuccessWx(String str) {

    }


    //登录失败
    @Override
    public void loginFailed(String str) {
        if (!TextUtils.isEmpty(str)){
            mView.setFailedError(str);
        }

    }



}
