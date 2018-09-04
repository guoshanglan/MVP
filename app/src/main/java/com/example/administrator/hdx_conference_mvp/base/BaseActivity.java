package com.example.administrator.hdx_conference_mvp.base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.administrator.hdx_conference_mvp.R;
import com.example.administrator.hdx_conference_mvp.inter.IBase;

/**
 * Created by Administrator on 2018/8/27.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements IBase {
    protected BasePresenter mPresenter;
    protected MyAppLication appLication;
    private Dialog photoDialog, upLoadingDialog;
    private AnimationDrawable dialogAnimationDrawable;

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




    public void showUpLoadingDialog(Context context) {
        upLoadingDialog = new Dialog(context, R.style.dialog);
        upLoadingDialog.setCancelable(false);
        upLoadingDialog.setCanceledOnTouchOutside(false);
        upLoadingDialog.setContentView(R.layout.dialog_upload_file_loading);

        ImageView progressbar = (ImageView) upLoadingDialog.findViewById(R.id.diaolg_uploading_header_progressbar);
        progressbar.setBackgroundResource(R.drawable.loading);
        dialogAnimationDrawable = (AnimationDrawable) progressbar.getBackground();
        if (dialogAnimationDrawable != null) {
            dialogAnimationDrawable.setOneShot(false);
            dialogAnimationDrawable.start();
        }
        upLoadingDialog.show();

    }

    public void dismissUpLoadingDialog() {
        if (dialogAnimationDrawable.isRunning()) {
            dialogAnimationDrawable.stop();
        }

        upLoadingDialog.dismiss();
    }


}
