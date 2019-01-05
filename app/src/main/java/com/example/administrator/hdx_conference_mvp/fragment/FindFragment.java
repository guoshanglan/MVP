package com.example.administrator.hdx_conference_mvp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.administrator.hdx_conference_mvp.R;
import com.example.administrator.hdx_conference_mvp.adapter.HomeAdapter;
import com.example.administrator.hdx_conference_mvp.base.BaseFragment;
import com.example.administrator.hdx_conference_mvp.base.BasePresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/8/28.
 */

public class FindFragment extends BaseFragment {
    public View view;
    public SmartRefreshLayout refreshLayout;
    public ListView listView;
    public HomeAdapter adapter;
    public List<String> list;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=View.inflate(getActivity(), R.layout.findfragment_layout,null);
        refreshLayout = view.findViewById(R.id.smartLayout);
        listView = view.findViewById(R.id.lv_find);
        list = new ArrayList<>();
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {

                list.clear();
                for (int i = 0; i < 10; i++) {
                    list.add("阿辉");
                }
                adapter.notifyDataSetChanged();
                refreshlayout.finishRefresh();

            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                for (int i = 0; i < 10; i++) {
                    list.add("阿辉");
                }
                adapter.notifyDataSetChanged();
                refreshlayout.finishLoadmore();
            }

        });

        initData();

        return view;
    }

    //初始化数据
    private void initData() {
        adapter = new HomeAdapter(getActivity(), list);
        for (int i = 0; i < 10; i++) {
            list.add("阿辉");
        }
        listView.setAdapter(adapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        refreshLayout.autoRefresh();

    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
