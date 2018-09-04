package com.example.administrator.hdx_conference_mvp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.hdx_conference_mvp.R;
import com.example.administrator.hdx_conference_mvp.activity.LoginActivity;
import com.example.administrator.hdx_conference_mvp.base.BaseFragment;
import com.example.administrator.hdx_conference_mvp.base.BasePresenter;
import com.example.administrator.hdx_conference_mvp.widview.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/8/28.
 */

public class MoreFragment extends BaseFragment {
    private Unbinder unbinder;
    public View view;
    @BindView(R.id.iv_touxiang_more)
    public CircleImageView circleImageView;   //头像
    @BindView(R.id.rl_gerenziliao_more)
    public RelativeLayout rlProfile;  //个人资料
    @BindView(R.id.tv_username_more)
    public TextView tvUserName;  //姓名
    @BindView(R.id.tv_zhiwu_more)
    public TextView tvZhiwu; //职务
    @BindView(R.id.tv_out)
    public TextView tvOutLogin;  //退出登录



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=View.inflate(getActivity(), R.layout.more_fragment,null);
        //返回一个Unbinder值（进行解绑），注意这里的this不能使用getActivity()
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.rl_gerenziliao_more,R.id.tv_out})
    public void Onclick(View view){
        switch (view.getId()){
            case R.id.rl_gerenziliao_more:   //跳转到个人资料

                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;

            case R.id.tv_out:    //退出登录，同时清除本地缓存

                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
        }
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    /**
     * onDestroyView中进行解绑操作
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
