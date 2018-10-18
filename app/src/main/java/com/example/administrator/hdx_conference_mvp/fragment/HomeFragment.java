package com.example.administrator.hdx_conference_mvp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.hdx_conference_mvp.R;
import com.example.administrator.hdx_conference_mvp.adapter.PageSlideAdapter;
import com.example.administrator.hdx_conference_mvp.base.BaseFragment;
import com.example.administrator.hdx_conference_mvp.base.BasePresenter;
import com.example.administrator.hdx_conference_mvp.db.TagString;
import com.example.administrator.hdx_conference_mvp.utils.GreenDaoManager;
import com.example.administrator.hdx_conference_mvp.widview.PagerSlidingTabStrip;
import com.jyjt.ydyl.greendao.gen.TagStringDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/10/18.
 */

public class HomeFragment extends BaseFragment {
    public View view;
    public ViewPager mViewpage;
    public PagerSlidingTabStrip ptab;
    public PageSlideAdapter psAdapter;
    public List<Fragment>list;
    public ArrayList<String>title;
    public TagStringDao tagStringDao;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=View.inflate(getActivity(), R.layout.home_fragment,null);
        initView(view);

        return view;
    }

    //初始化数据
    private void initView(View view) {
        tagStringDao= GreenDaoManager.getInstance().getSession().getTagStringDao();   //获取greendao标签的数据库对象
        mViewpage=view.findViewById(R.id.viewpage);
        ptab=view.findViewById(R.id.pageSide);
        list=new ArrayList<>();
        title=new ArrayList<>();

        //先查询数据库中是否含有，没有的话，在添加数据
        List<TagString> name=tagStringDao.loadAll();
        if (name!=null&&name.size()>0){
            for (int i=0;i<name.size();i++) {
             title.add(name.get(i).getTitle());
             list.add(new HomePageFragment());
            }
        }else{
           TagString tagString=new TagString("首页");
            TagString tagString2=new TagString("测试1");
            TagString tagString3=new TagString("测试2");
            TagString tagString4=new TagString("测试3");
              title.add(tagString.getTitle());
            title.add(tagString2.getTitle());
            title.add(tagString3.getTitle());
            title.add(tagString4.getTitle());
             list.add(new HomePageFragment());
            list.add(new HomePageFragment());
            list.add(new HomePageFragment());
            list.add(new HomePageFragment());
            if (!isInsert(tagString)) {
                tagStringDao.insert(tagString);
            }
            if (!isInsert(tagString2))
            tagStringDao.insert(tagString2);
            if (!isInsert(tagString3))
            tagStringDao.insert(tagString3);
                if (!isInsert(tagString4))
            tagStringDao.insert(tagString4);
        }

        psAdapter=new PageSlideAdapter(getActivity().getSupportFragmentManager(),list,title);
        mViewpage.setAdapter(psAdapter);
        ptab.setViewPager(mViewpage);
    }

    public boolean isInsert(TagString tagString){
        if (tagStringDao.queryBuilder().where(TagStringDao.Properties.Title.eq(tagString.getTitle())).list()==null||tagStringDao.queryBuilder().where(TagStringDao.Properties.Title.eq(tagString.getTitle())).list().size()==0){
            return false;
        }else {

            return true;
        }
    }


    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
