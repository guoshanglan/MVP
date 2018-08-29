package com.example.administrator.hdx_conference_mvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.administrator.hdx_conference_mvp.inter.IBase;

/**
 * Created by Administrator on 2018/8/28.
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements IBase{
    protected BasePresenter mPresenter;
    protected MyAppLication appLication;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = getPresenter();
        appLication=MyAppLication.getInstance();
        if (mPresenter != null) {
            mPresenter.attach(this);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter=null;
        }

    }
}
