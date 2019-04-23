package com.example.administrator.hdx_conference_mvp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.hdx_conference_mvp.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/10/29.
 */

public class PopAdapter  extends  BaseAdapter{
    public Context context;
    public ArrayList<String>list;

    public PopAdapter(Context context,ArrayList<String>list){
        this.context=context;
        this.list=list;
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
        PopViewHolder vh=null;
         if (view==null){
             view=View.inflate(context,R.layout.float_pop_item,null);
             vh=new PopViewHolder(view);
             view.setTag(vh);
         }else{
             vh= (PopViewHolder) view.getTag();
         }


        vh.tvTag.setText(list.get(i));

        return view;
    }


    public class PopViewHolder{
        public TextView tvTag;

        public PopViewHolder(View view){
            tvTag=view.findViewById(R.id.tv_float_pop);
        }

    }



}
