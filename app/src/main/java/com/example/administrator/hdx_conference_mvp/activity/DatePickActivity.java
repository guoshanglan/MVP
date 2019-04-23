package com.example.administrator.hdx_conference_mvp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.hdx_conference_mvp.R;
import com.example.administrator.hdx_conference_mvp.utils.dialog.wheelDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/12/6.
 */

public class DatePickActivity extends AppCompatActivity {

    @BindView(R.id.iv_back)
    public ImageView ivback;
    @BindView(R.id.tv_date)
    public Button btdate;
    public wheelDialog wheelDialog;
    @BindView(R.id.ly_showview)
    public LinearLayout lyshow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.datepick_activity);
        ButterKnife.bind(this);
    }

    //点击事件
    @OnClick({R.id.iv_back,R.id.tv_date})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                  finish();
                break;

            case R.id.tv_date:
                 wheelDialog=new wheelDialog(this, new wheelDialog.DateChooseInterface() {
                     @Override
                     public void getDateTime(String time) {
                         btdate.setText(time);
                     }
                 });
                break;

        }

    }


}
