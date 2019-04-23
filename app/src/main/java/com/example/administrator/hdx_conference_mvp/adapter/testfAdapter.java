package com.example.administrator.hdx_conference_mvp.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2019/2/22.
 */

public class testfAdapter extends FragmentPagerAdapter {
    public Context mcontext;
    public List<Fragment>list;
    public List<String>titleList;

    public testfAdapter(FragmentManager fm,Context context,List<Fragment>list,List<String>titlelist) {
        super(fm);
        this.mcontext=context;
        this.list=list;
        this.titleList=titlelist;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
