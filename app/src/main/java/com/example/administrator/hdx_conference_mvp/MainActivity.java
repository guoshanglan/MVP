package com.example.administrator.hdx_conference_mvp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import com.example.administrator.hdx_conference_mvp.base.BaseActivity;
import com.example.administrator.hdx_conference_mvp.base.BasePresenter;
import com.example.administrator.hdx_conference_mvp.fragment.FindFragment;
import com.example.administrator.hdx_conference_mvp.fragment.HomeFragment;
import com.example.administrator.hdx_conference_mvp.fragment.MessageFragment;
import com.example.administrator.hdx_conference_mvp.fragment.MoreFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    //用来显示fragment的布局控件
    @BindView(R.id.content)
    public FrameLayout frameLayout;

    //多个相同控件注解
    @BindViews({ R.id.rb_home, R.id.rb_faxian,  R.id.rb_message,R.id.rb_more})
    public List<RadioButton> buttonList ;
    private Fragment homeFragment, findFragment, messageFragment, moreFragment;
    private Fragment[] fragments;
    private android.support.v4.app.FragmentManager fragmentmanager;
    private FragmentTransaction ft;
    private int index, currentTabIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initTab();


    }


    //初始化底部控件碎片
    private void initTab() {
        homeFragment=new HomeFragment();
        findFragment=new FindFragment();
        messageFragment=new MessageFragment();
        moreFragment=new MoreFragment();
        fragments=new Fragment[]{homeFragment,findFragment,messageFragment,moreFragment};
        fragmentmanager = getSupportFragmentManager();
        ft = fragmentmanager.beginTransaction();
        buttonList.get(0).setSelected(true);
        ft.add(R.id.content, homeFragment);
        ft.show(homeFragment).commit();
    }


//Butterknife 注解点击事件
    @OnClick({R.id.rb_home, R.id.rb_faxian, R.id.rb_message, R.id.rb_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_home:
               SwitchSkip(0);
                break;
            case R.id.rb_faxian:
                SwitchSkip(1);
                break;
            case R.id.rb_message:
                SwitchSkip(2);
                break;
            case R.id.rb_more:
                SwitchSkip(3);
                break;
        }
    }


    private void SwitchSkip(int index) {
        Log.e("pp", currentTabIndex + "+++" + index);
        ft = fragmentmanager.beginTransaction();
        if (currentTabIndex != index) {
            if (!fragments[index].isAdded()) {
                ft.add(R.id.content, fragments[index]);
            }
            ft.hide(fragments[currentTabIndex]).show(fragments[index]).commit();


           for (int i=0;i<buttonList.size();i++){
               if (i==index){
                   buttonList.get(index).setSelected(true);
                   buttonList.get(index).setTextColor(Color.parseColor("#e6007b"));

               }else{
                   buttonList.get(i).setSelected(false);
                   buttonList.get(i).setTextColor(Color.parseColor("#484848"));
                   buttonList.get(i).setTextColor(Color.parseColor("#484848"));
                   buttonList.get(i).setTextColor(Color.parseColor("#484848"));
               }
           }

            currentTabIndex = index;
        }
    }


    //这个主界面如果需要进行网络操作的话，需要返回一个presenter,咱们目前暂时不需要
    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
