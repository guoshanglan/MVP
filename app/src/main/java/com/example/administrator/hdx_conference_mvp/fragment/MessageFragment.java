package com.example.administrator.hdx_conference_mvp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.administrator.hdx_conference_mvp.R;
import com.example.administrator.hdx_conference_mvp.adapter.MessageAdapter;
import com.example.administrator.hdx_conference_mvp.adapter.RecyclerAdapter;
import com.example.administrator.hdx_conference_mvp.base.BaseFragment;
import com.example.administrator.hdx_conference_mvp.base.BasePresenter;
import com.example.administrator.hdx_conference_mvp.utils.RotationPageTransformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/8/28.
 */

public class MessageFragment extends BaseFragment {
    public View view;

    private static final int[] drawableIds = new int[]{R.drawable.meinv,R.drawable.meinv,R.drawable.meinv,R.drawable.meinv,R.drawable.meinv};
    private ViewPager mViewPager;
    private RelativeLayout mRelativeLayout;
    private MessageAdapter adapter;

    private RecyclerAdapter recyclerAdapter;
    private RecyclerView recyclerView;
    private List<String>list=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=View.inflate(getActivity(), R.layout.message_fragment,null);

        initView(view);

        return view;
    }

    private void initView(View view) {
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        recyclerView=view.findViewById(R.id.recyclerview);
        recyclerAdapter=new RecyclerAdapter(getActivity(),list);
        LinearLayoutManager  lm= new LinearLayoutManager(getActivity());
        lm.setOrientation(LinearLayoutManager.HORIZONTAL);//实现横向的关键点，绝对不能漏
        recyclerView.setLayoutManager(lm);
        recyclerView.setAdapter(recyclerAdapter);
        //调用方法,传入一个接口回调
        recyclerAdapter.setItemClickListener(new RecyclerAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mViewPager.setCurrentItem(position);   //点击底部横排图片，上面选中某个图片
            }
        });

        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        adapter = new MessageAdapter(drawableIds,getActivity());
        mViewPager.setAdapter(adapter);
        mViewPager.setPageTransformer(true,new RotationPageTransformer());
        mViewPager.setOffscreenPageLimit(2);//设置预加载的数量，这里设置了2,会预加载中心item左边两个Item和右边两个Item
        mViewPager.setPageMargin(10);//设置两个Page之间的距离
    }


    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
