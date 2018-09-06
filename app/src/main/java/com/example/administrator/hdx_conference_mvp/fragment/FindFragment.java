package com.example.administrator.hdx_conference_mvp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.hdx_conference_mvp.R;
import com.example.administrator.hdx_conference_mvp.base.BaseFragment;
import com.example.administrator.hdx_conference_mvp.base.BasePresenter;

/**
 * Created by Administrator on 2018/8/28.
 */

public class FindFragment extends BaseFragment {
    public View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=View.inflate(getActivity(), R.layout.findfragment_layout,null);
        return view;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
