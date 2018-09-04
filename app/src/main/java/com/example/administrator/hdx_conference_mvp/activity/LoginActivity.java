package com.example.administrator.hdx_conference_mvp.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.hdx_conference_mvp.R;
import com.example.administrator.hdx_conference_mvp.base.BaseActivity;
import com.example.administrator.hdx_conference_mvp.base.BasePresenter;
import com.example.administrator.hdx_conference_mvp.bean.LoginBean;
import com.example.administrator.hdx_conference_mvp.presenter.LoginPresenter;
import com.example.administrator.hdx_conference_mvp.view.LoginView;
import com.example.administrator.hdx_conference_mvp.widview.EditTextWithDel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/8/27.
 *
 * 这边通过实现loginview的接口，将我们所需要的界面交互数据传递过去
 */

public class LoginActivity extends BaseActivity  implements LoginView{
    @BindView(R.id.iv_back)
    public ImageView ivBack;   //返回
    @BindView(R.id.login_user_name)
    public EditTextWithDel etUsername;   //用户名
    @BindView(R.id.login_password)
    public EditTextWithDel etPassword;   //密码
    @BindView(R.id.login_submit_botton)
    public Button btLogin; //登录
    @BindView(R.id.tv_forgetpassword)
    public TextView tvForgetpassword;
    @BindView(R.id.tv_resigest)
    public TextView tvResigest;
    public LoginPresenter loginPresenter;
    public String username,password;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);
        ButterKnife.bind(this);

    }




//点击事件
    @OnClick({R.id.iv_back,R.id.tv_resigest,R.id.login_submit_botton,R.id.tv_forgetpassword})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:    //返回
                finish();
                break;
            case R.id.tv_forgetpassword:   //忘记密码

                break;

            case R.id.tv_resigest:   //注册
                Toast.makeText(this,"请输入用户名",Toast.LENGTH_SHORT).show();

                break;

            case R.id.login_submit_botton:    //立即登录

                if (TextUtils.isEmpty(etUsername.getText().toString().trim().replace(" ",""))){
                    Toast.makeText(this,"请输入用户名",Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(etPassword.getText().toString().trim().replace(" ",""))){
                    Toast.makeText(this,"请输入密码",Toast.LENGTH_SHORT).show();
                }else{
                    showUpLoadingDialog(this);   //显示加载框
                    loginPresenter.loginNet();
                }

                break;

        }
    }

    //需要输入的手机号码
    @Override
    public String getNumber() {
        username=etUsername.getText().toString().trim();

        return username;
    }

    //输入密码
    @Override
    public String getPass() {
        password=etPassword.getText().toString().trim().replace(" ","");
        return password;
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
        if (loginBean!=null){
            dismissUpLoadingDialog();

            Log.e("登陆成功",loginBean.toString());
        }

    }


    @Override
    public void setLoginWx(String str) {

    }

    //登录失败返回数据
    @Override
    public void setFailedError(String str) {
       dismissUpLoadingDialog();
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
        Log.e("登陆成功",str.toString());
    }


    //返回一个presenter实列对象
    @Override
    public BasePresenter getPresenter() {
        if (loginPresenter==null){
            loginPresenter=new LoginPresenter();
        }
        return loginPresenter;
    }
}
