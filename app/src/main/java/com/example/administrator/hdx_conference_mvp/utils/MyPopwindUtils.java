package com.example.administrator.hdx_conference_mvp.utils;

import android.app.Activity;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.example.administrator.hdx_conference_mvp.R;
import com.example.administrator.hdx_conference_mvp.adapter.PopAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/10/29.
 *
 */

public class MyPopwindUtils implements View.OnClickListener {
    public Activity context;
    public ArrayList<String>list;
    public PopAdapter adapter;
    public View popView;
    public ListView mListview;
    public PopupWindow popupWindow;
    public OnItemClick click;
    public View positionView;   //popwind要显示在哪个控件下面
    public RelativeLayout rlPop;


    public MyPopwindUtils(Activity context,ArrayList<String>list,View positionView,OnItemClick click){
        this.context=context;
        this.list=list;
        this.click=click;
        this.positionView=positionView;
        CreatPopWindow();

    }



    public void CreatPopWindow(){
        adapter=new PopAdapter(context,list);
        adapter.notifyDataSetChanged();
        popView=View.inflate(context, R.layout.float_pop,null);
        mListview=popView.findViewById(R.id.listview_pop);
        rlPop=popView.findViewById(R.id.rl_pop);
        rlPop.setOnClickListener(this);
        mListview.setAdapter(adapter);
        DisplayMetrics dm = new DisplayMetrics();
       context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenHeigh = dm.heightPixels;  // 获取当前的屏幕高度
        popupWindow = new PopupWindow(popView, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

        if (list.size()>10){
            RelativeLayout.LayoutParams lp=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, screenHeigh / 3);
            mListview.setLayoutParams(lp);
        }else{
            RelativeLayout.LayoutParams lp=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            mListview.setLayoutParams(lp);
        }

        showAsDropDown2(popupWindow, positionView, 0,0);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);

        popupWindow.setAnimationStyle(R.style.dialogWindowAnim);
        ColorDrawable dw = new ColorDrawable(0xb0e00000);
       // backgroundAlpha(0.5f);
        //设置SelectPicPopupWindow弹出窗体的背景
        popupWindow.setBackgroundDrawable(dw);
        popupWindow.update();


        //listview的点击事件
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 popupWindow.dismiss();
                 click.Click(list.get(i));
            }
        });

    }




    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rl_pop:
                if (popupWindow!=null)
                popupWindow.dismiss();
                break;

        }
    }


    public interface OnItemClick{
          void Click(String name);
   }

    public static void showAsDropDown2(final PopupWindow pw, final View anchor, final int xoff, final int yoff) {
        if (Build.VERSION.SDK_INT >= 24) {
            Rect visibleFrame = new Rect();
            anchor.getGlobalVisibleRect(visibleFrame);
            int height = anchor.getResources().getDisplayMetrics().heightPixels - visibleFrame.bottom;
            pw.setHeight(height);
            pw.showAsDropDown(anchor, xoff, yoff);
        } else {
            pw.showAsDropDown(anchor, xoff, yoff);
        }
    }

}
