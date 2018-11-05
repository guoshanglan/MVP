package com.example.administrator.hdx_conference_mvp.presenter;

import android.text.TextUtils;

import com.example.administrator.hdx_conference_mvp.base.BasePresenter;
import com.example.administrator.hdx_conference_mvp.bean.FindPasswordBean;
import com.example.administrator.hdx_conference_mvp.inter.FindPasswordListener;
import com.example.administrator.hdx_conference_mvp.model.FindpasswordModel;
import com.example.administrator.hdx_conference_mvp.view.FindpassView;

/**
 * Created by Administrator on 2018/9/5.
 */

public class FindPasswordPresenter  extends BasePresenter<FindpassView> implements FindPasswordListener {

    private FindpasswordModel findpasswordModel; //用来处理网络请求等耗时操作的


    public FindPasswordPresenter(){
        this.findpasswordModel=new FindpasswordModel();
    }


    //通过activity点击的时候调用这个网络请求方法
    public void FindPassword() {
        findpasswordModel.findPassword(mView.getContext(), mView.getNumber(), mView.getUsername(), this);
    }

    //登录成功,这边的mview，已经在basePresenter中定义过了




    @Override
    public void findSuccess(FindPasswordBean findPasswordBean) {
        if (findPasswordBean!=null)
            mView.setsuccessful(findPasswordBean);
    }

    @Override
    public void findSuccessWx(String str) {

    }

    @Override
    public void findFailed(String str) {
        if (!TextUtils.isEmpty(str)){
            mView.setFailedError(str);
        }
    }
}
