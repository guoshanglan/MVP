package com.example.administrator.hdx_conference_mvp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.hdx_conference_mvp.R;
import com.example.administrator.hdx_conference_mvp.adapter.HomeAdapter;
import com.example.administrator.hdx_conference_mvp.base.BaseFragment;
import com.example.administrator.hdx_conference_mvp.base.BasePresenter;
import com.example.administrator.hdx_conference_mvp.widview.CirclePageIndicator;
import com.example.administrator.hdx_conference_mvp.widview.XListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/8/28.
 */

public class HomeFragment extends BaseFragment implements XListView.IXListViewListener {
    private Unbinder unbinder;
    public View view,headview;
    public XListView mListView;     //列表控件
    public ViewPager mViewPager;  //左右滑动控件
    public CirclePageIndicator pageIndicator;  //圆点控件
    public HomeAdapter adapter;
    public List<String>list;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=View.inflate(getActivity(), R.layout.home_fragment,null);
        headview=View.inflate(getActivity(),R.layout.home_header,null);
        initview();
        return view;
    }


    //初始化控件
    private void initview() {
        mListView = (XListView) view.findViewById(R.id.listview);
        mListView.setXListViewListener(this);
        pageIndicator = (CirclePageIndicator) headview.findViewById(R.id.cicleIndicator);
        mViewPager = (ViewPager) headview.findViewById(R.id.viewpager);
        list=new ArrayList<>();
        list.add("ahui");
        list.add("ahui");
        list.add("ahui");
        list.add("ahui");
        adapter=new HomeAdapter(getActivity(),list);
        mListView.setAdapter(adapter);
        mListView.addHeaderView(headview);

    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onRefresh() {
        mListView.stopLoadMore();
        mListView.stopRefresh();

    }

    @Override
    public void onLoadMore() {
        mListView.stopLoadMore();
        mListView.stopRefresh();
    }
}
