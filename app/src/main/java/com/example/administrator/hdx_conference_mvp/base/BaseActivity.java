package com.example.administrator.hdx_conference_mvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.hdx_conference_mvp.inter.IBase;

/**
 * Created by Administrator on 2018/8/27.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements IBase {
    protected BasePresenter mPresenter;
    protected MyAppLication appLication;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //绑定初始化ButterKnife
        appLication=MyAppLication.getInstance();
        mPresenter = getPresenter();
        if (mPresenter != null) {
            mPresenter.attach(this);
        }

    }


    //界面销毁就清除，防止内存溢出
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter=null;
        }

    }


}
