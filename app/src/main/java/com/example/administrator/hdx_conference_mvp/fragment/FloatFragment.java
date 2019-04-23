package com.example.administrator.hdx_conference_mvp.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.administrator.hdx_conference_mvp.R;
import com.example.administrator.hdx_conference_mvp.adapter.HomeAdapter;
import com.example.administrator.hdx_conference_mvp.base.BaseFragment;
import com.example.administrator.hdx_conference_mvp.base.BasePresenter;
import com.example.administrator.hdx_conference_mvp.utils.MyPopwindUtils;
import com.example.administrator.hdx_conference_mvp.widview.XListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/10/19.
 * 悬浮的listview的tab
 *
 */

public class FloatFragment extends BaseFragment implements XListView.IXListViewListener, View.OnClickListener {
    public XListView mListview;
    public HomeAdapter adapter;
    public List<String> list;
    public View view,headview1,headview2;
    public LinearLayout flow_view;
    public Button btTest1,btTest2,btTest3,btTest4;
    public Button btTimo,btKannan,btxiaoyu,btlulu;
    public MyPopwindUtils myPopwindUtils;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=View.inflate(getActivity(), R.layout.float_fragment,null);
        headview1=View.inflate(getActivity(),R.layout.floatfragment_headview1,null);
        headview2=View.inflate(getActivity(),R.layout.floatfragment_headview2,null);

        initView(view);

        return view;
    }


    //初始化数据
    private void initView(View view) {
        btTest1=headview2.findViewById(R.id.test1);
        btTest2=headview2.findViewById(R.id.test2);
        btTest3=headview2.findViewById(R.id.test3);
        btTest4=headview2.findViewById(R.id.test4);
        btTimo=view.findViewById(R.id.timo);
        btxiaoyu=view.findViewById(R.id.xiaoyu);
        btKannan=view.findViewById(R.id.kainan);
        btlulu=view.findViewById(R.id.lulu);

        btTest1.setOnClickListener(this);
        btTest2.setOnClickListener(this);
        btTest3.setOnClickListener(this);
        btTest4.setOnClickListener(this);
        btTimo.setOnClickListener(this);
        btxiaoyu.setOnClickListener(this);
        btKannan.setOnClickListener(this);
        btlulu.setOnClickListener(this);

        mListview=view.findViewById(R.id.listview);
        flow_view=view.findViewById(R.id.flow_view);
        list=new ArrayList<>();
        list.add("外卖");
        list.add("ahui");
        list.add("能回");
        list.add("程辉");
        list.add("策划");
        list.add("外卖");
        list.add("外卖");
        list.add("测试");
        list.add("外卖");
        list.add("无聊");
        list.add("外卖");
        list.add("中午儿女");
        list.add("外卖");
        adapter=new HomeAdapter(getActivity(),list);
        mListview.addHeaderView(headview1);
        mListview.addHeaderView(headview2);
        mListview.setAdapter(adapter);
        mListview.setXListViewListener(this);
        mListview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // TODO Auto-generated method stub
                if(mListview.mIsAutoLoadMore) {
                    if (scrollState == SCROLL_STATE_IDLE && mListview.lastItemIndex == mListview.mCount) {
                        mListview.startLoadMore();
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                // TODO Auto-generated method stub

                View h1 = mListview.getChildAt(1);
                if (h1 == null) {
                    return;
                }

                if (firstVisibleItem >=2 ) {
                    flow_view.setVisibility(View.VISIBLE);
                } else { // 校正误差 head_1_height
                    flow_view.setVisibility(View.GONE);
                }
                mListview. mTotalItemCount = totalItemCount;
//		if (mScrollListener != null) {
//			mScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
//		}

                mListview. lastItemIndex = firstVisibleItem + visibleItemCount - 1 - 1;
                mListview.mCount = totalItemCount - 2;

            }
        });


                test();
    }

    private void test() {



    }


    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    //下拉刷新
    @Override
    public void onRefresh() {
        mListview.stopLoadMore();
        mListview.stopRefresh();
    }

    //加载更多
    @Override
    public void onLoadMore() {
        mListview.stopLoadMore();
        mListview.stopRefresh();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.test1:
            case R.id.timo:
                Button bt=null;
                if (view.getId()==R.id.test1){
                     bt=btTest1;
                }else{
                     bt=btTimo;
                }
                myPopwindUtils=new MyPopwindUtils(getActivity(), (ArrayList<String>) list, bt, new MyPopwindUtils.OnItemClick() {
                    @Override
                    public void Click(String name) {
                        btTest1.setText(name);
                    }
                });

                break;
            case R.id.test2:
            case R.id.xiaoyu:
                Button bt2=null;
                if (view.getId()==R.id.test2){
                    bt2=btTest2;
                }else{
                    bt2=btxiaoyu;
                }
                myPopwindUtils=new MyPopwindUtils(getActivity(), (ArrayList<String>) list, bt2, new MyPopwindUtils.OnItemClick() {
                    @Override
                    public void Click(String name) {
                        btTest2.setText(name);
                    }
                });

                break;

            case R.id.test3:
            case R.id.kainan:
                Button bt3=null;
                if (view.getId()==R.id.test3){
                    bt3=btTest3;
                }else{
                    bt3=btKannan;
                }
                myPopwindUtils=new MyPopwindUtils(getActivity(), (ArrayList<String>) list, bt3, new MyPopwindUtils.OnItemClick() {
                    @Override
                    public void Click(String name) {
                        btTest3.setText(name);
                    }
                });

                break;

            case R.id.test4:
            case R.id.lulu:
                Button bt4=null;
                if (view.getId()==R.id.test4){
                    bt4=btTest4;
                }else{
                    bt4=btlulu;
                }
                myPopwindUtils=new MyPopwindUtils(getActivity(), (ArrayList<String>) list, bt4, new MyPopwindUtils.OnItemClick() {
                    @Override
                    public void Click(String name) {
                        btTest4.setText(name);
                    }
                });

                break;




        }
    }
}
