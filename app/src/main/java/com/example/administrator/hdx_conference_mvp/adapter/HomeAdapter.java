package com.example.administrator.hdx_conference_mvp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.hdx_conference_mvp.R;

import java.util.List;

/**
 * Created by Administrator on 2018/8/29.
 */

public class HomeAdapter extends BaseAdapter {
    public Context mContext;
    public List<String>list;


    public HomeAdapter(Context mContext, List<String> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        HomeViewholder vh=null;
          if (view==null){
              view=View.inflate(mContext,R.layout.home_item,null);
              vh=new HomeViewholder(view);
              view.setTag(vh);
          }else{
              vh= (HomeViewholder) view.getTag();
          }

        return view;
    }


    public class HomeViewholder{
        public TextView tvTitle;
        public ImageView iv;

        public HomeViewholder(View view){
            tvTitle=view.findViewById(R.id.tv_title);
            iv=view.findViewById(R.id.iv_item);
        }
    }

}
