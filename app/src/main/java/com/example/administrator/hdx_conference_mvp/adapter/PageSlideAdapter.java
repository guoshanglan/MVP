package com.example.administrator.hdx_conference_mvp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/10/18.
 */

public class PageSlideAdapter extends FragmentPagerAdapter {
    public List<Fragment>list;
    public ArrayList<String>title;



    public PageSlideAdapter(FragmentManager fm,List<Fragment>list,ArrayList<String>title) {
        super(fm);
        this.list=list;
        this.title=title;
    }



    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return title.get(position);
    }
}
