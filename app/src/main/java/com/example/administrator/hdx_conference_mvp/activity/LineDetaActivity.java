package com.example.administrator.hdx_conference_mvp.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.hdx_conference_mvp.R;
import com.example.administrator.hdx_conference_mvp.base.BaseActivity;
import com.example.administrator.hdx_conference_mvp.base.BasePresenter;
import com.example.administrator.hdx_conference_mvp.utils.MPChartHelper;
import com.github.mikephil.charting.charts.LineChart;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/11/12.
 *
 * 折线图和曲线图的数据展示
 */

public class LineDetaActivity  extends BaseActivity{
    @BindView(R.id.lc_view_count)
    public LineChart mlineChart;
    @BindView(R.id.tv_sevenday)   //7天数据
    public TextView tvSevenday;
    @BindView(R.id.tv_thirtyday)
    public TextView tvThirthday;  //30天数据
    @BindView(R.id.iv_back)
    public ImageView ivBack;  //返回
    private List<String> xAxisSeven   = new ArrayList<>();
    private List<Integer>  yAxisSeven   = new ArrayList<>();    //存储7天的数据

    private List<String> xAxisThirty  = new ArrayList<>();    //存储30天的数据
    private List<Integer>  yAxisThirty  = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_activity);
        ButterKnife.bind(this);
        initData();
    }

    //初始化7天的数据
    private void initData() {
        mlineChart.clear();
        xAxisSeven.clear();
        yAxisSeven.clear();
        xAxisSeven.add("星期一");
        xAxisSeven.add("星期二");
        xAxisSeven.add("星期三");
        xAxisSeven.add("星期四");
        xAxisSeven.add("星期五");
        xAxisSeven.add("星期六");
        xAxisSeven.add("星期日");
        yAxisSeven.add(100);
        yAxisSeven.add(110);
        yAxisSeven.add(105);
        yAxisSeven.add(80);
        yAxisSeven.add(120);
        yAxisSeven.add(93);
        yAxisSeven.add(64);
        tvThirthday.setTextColor(Color.parseColor("#484848"));
        tvSevenday.setTextColor(getResources().getColor(R.color.text_right));
        MPChartHelper.setLineChart(mlineChart,xAxisSeven,yAxisSeven,"浏览数",false,getResources().getColor(R.color.text_right), ContextCompat.getDrawable(this,R.drawable.bg_line_green));
    }

    private void initThirty() {
        mlineChart.clear();
        xAxisThirty.clear();
        yAxisThirty.clear();
        xAxisThirty.add("1号");
        xAxisThirty.add("2号");
        xAxisThirty.add("3号");
        xAxisThirty.add("4号");
        xAxisThirty.add("5号");
        xAxisThirty.add("6号");
        xAxisThirty.add("7号");
        xAxisThirty.add("8号");
        xAxisThirty.add("9号");
        xAxisThirty.add("10号");
        xAxisThirty.add("11号");
        xAxisThirty.add("12号");
        xAxisThirty.add("13号");
        xAxisThirty.add("14号");
        xAxisThirty.add("15号");
        yAxisThirty.add(100);
        yAxisThirty.add(110);
        yAxisThirty.add(105);
        yAxisThirty.add(80);
        yAxisThirty.add(120);
        yAxisThirty.add(93);
        yAxisThirty.add(64);
        yAxisThirty.add(100);
        yAxisThirty.add(110);
        yAxisThirty.add(105);
        yAxisThirty.add(80);
        yAxisThirty.add(120);
        yAxisThirty.add(93);
        yAxisThirty.add(64);
        yAxisThirty.add(100);

   ;    tvSevenday.setTextColor(Color.parseColor("#484848"));
        tvThirthday.setTextColor(getResources().getColor(R.color.text_right));
        MPChartHelper.setLineChart(mlineChart,xAxisThirty,yAxisThirty,"浏览数",false,getResources().getColor(R.color.text_right), ContextCompat.getDrawable(this,R.drawable.bg_line_green));
    }

    //点击事件
    @OnClick({R.id.iv_back,R.id.tv_sevenday,R.id.tv_thirtyday})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:    //返回
                finish();
                break;
            case R.id.tv_sevenday:   //7天数据
               initData();
                break;

            case R.id.tv_thirtyday:   //30天数据
                initThirty();
                break;


        }
    }


    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
