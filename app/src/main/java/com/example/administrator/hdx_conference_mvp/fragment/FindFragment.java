package com.example.administrator.hdx_conference_mvp.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.administrator.hdx_conference_mvp.R;
import com.example.administrator.hdx_conference_mvp.base.BaseFragment;
import com.example.administrator.hdx_conference_mvp.base.BasePresenter;
import com.example.administrator.hdx_conference_mvp.bean.TranficBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/8/28.
 */

public class FindFragment extends BaseFragment {
    public View view;
    public LinearLayout ly, radioly, checkly;
    public List<TranficBean> list;
    public View radioview, checkView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.findfragment_layout, null);
        ly = view.findViewById(R.id.ly_find);
        list = new ArrayList<>();
        list.add(new TranficBean("以下说法正确的是?", new String[]{"遇到红灯需要停车", "不用礼让行人"}, "遇到红灯需要停车", "radio"));
        list.add(new TranficBean("以下说法正确的是?", new String[]{"遇到大雨视线不清楚需要减速行驶", "在行人多的地方，按喇叭鸣笛催促行人快点离开", "遇到行人需减速"}, "遇到红灯需要停车", "checkbox"));
        initData();

        return view;
    }

    //初始化数据,这个可以绘制出自定义表单的内容
    private void initData() {


        for (int i = 0; i < list.size(); i++) {
            View radioview = View.inflate(getActivity(), R.layout.radionground_layout, null);
            View checkView = View.inflate(getActivity(), R.layout.check_box_layout, null);
            TranficBean bean = list.get(i);

            switch (list.get(i).type) {
                case "radio":   //单选框
                    TextView tvtitle = radioview.findViewById(R.id.radio_title);
                    RadioGroup group = radioview.findViewById(R.id.radio_group);
                    tvtitle.setText(bean.title);

                    for (int j = 0; j < bean.item.length; j++) {
                        RadioButton radioButton = new RadioButton(getActivity());
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        radioButton.setText(bean.item[j]);
                        radioButton.setTag(j);
                        radioButton.setTextColor(Color.parseColor("#666666"));
                        radioButton.setTextSize(14);
                        lp.setMargins(15, 25, 20, 20);

                        //radiobutton的选中事件
                        radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                            }
                        });

                        radioButton.setLayoutParams(lp);
                        group.addView(radioButton);
                    }

                    ly.addView(radioview);
                    break;
                case "checkbox":    //多选框

                    checkly = checkView.findViewById(R.id.check_ly);
                    TextView checkTitle =checkView.findViewById(R.id.check_title);
                    checkTitle.setText(bean.title);
                    for (int a = 0; a < bean.item.length; a++) {
                        CheckBox checkBox = new CheckBox(getActivity());
                        checkBox.setText(bean.item[a]);
                        checkBox.setTextSize(14);
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        lp.setMargins(15, 25, 20, 20);
                        checkBox.setLayoutParams(lp);

                        //checkbox的选中事件
                        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                            }
                        });
                        checkly.addView(checkBox);

                    }
                    ly.addView(checkView);
                    break;


            }


        }


    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
