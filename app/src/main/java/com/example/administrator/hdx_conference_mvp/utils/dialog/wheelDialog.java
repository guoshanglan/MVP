package com.example.administrator.hdx_conference_mvp.utils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.hdx_conference_mvp.R;
import com.example.administrator.hdx_conference_mvp.widview.wheelview.WheelView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2018/12/20.
 */

public class wheelDialog implements View.OnClickListener {
    private Context context; //上下文对象
    public View popview;
    public Dialog wheelpop;   //底部弹出popwindow
    private WheelView wlYear, wlmouth, wldate;  //3个wheelview
    private TextView tvCancel,tvsure; //保存，取消
    private List<String> listYear;  //年限数组
    private List<String> listMouth; //月份数组
    private List<String>listDate; //日期数据
    private DateChooseInterface dateChooseInterface; //接口回调
    private String year="";
    private String month="";
    private String date=""; //用来接收点击选中的值


     public wheelDialog(Context context,DateChooseInterface dateChooseInterface){
         this.context=context;
         this.dateChooseInterface=dateChooseInterface;
          initPopwindow();
     }


     //初始化数据
    private void initPopwindow() {
      //  Toast.makeText(context,"dianji",Toast.LENGTH_SHORT).show();
         listYear=new ArrayList<>();
         listMouth=new ArrayList<>();
         listDate=new ArrayList<>();

        Calendar nowCalendar = Calendar.getInstance();
         int nowYear = nowCalendar.get(Calendar.YEAR);
         final int mouth=nowCalendar.get(Calendar.MONTH);
         int day=nowCalendar.get(Calendar.DAY_OF_MONTH);

         for(int a=nowYear-100;a<2200;a++){
             listYear.add(a+"年");

         }
         for(int month=1;month<=12;month++){
             if (month<10) {
                 listMouth.add("0"+month + "月");
             }else{
                 listMouth.add(month + "月");
             }
         }
         year=nowYear+"年";
         month=(mouth+1)+"月";
         date=day+"日";
         listDate=loadData(nowYear,mouth+1);

         popview=View.inflate(context, R.layout.data_popwindow,null);
         wlYear=popview.findViewById(R.id.wl_year);
         wlmouth=popview.findViewById(R.id.wl_mouth);
         wldate=popview.findViewById(R.id.wl_date);
         tvCancel=popview.findViewById(R.id.tv_cancel);
         tvsure=popview.findViewById(R.id.tv_save);
         tvsure.setOnClickListener(this);
         tvCancel.setOnClickListener(this);
         wheelpop=new Dialog(context);
         //设置弹窗的弹出位置
        wheelpop.setContentView(popview);
        wheelpop.setCanceledOnTouchOutside(true);
        Window window = wheelpop.getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.horizontalMargin = 0;
        window.setAttributes(layoutParams);

        window.getDecorView().setBackgroundColor(Color.GREEN);


        wheelpop.getWindow().setGravity(Gravity.BOTTOM); //设置在底部
        wheelpop.getWindow().setWindowAnimations(R.style.takePhoto); // 添加动画


        //对wheelview进行赋值,和选中位置赋值
        wlYear.setItems(listYear,100);
        wlmouth.setItems(listMouth,mouth);
        wldate.setItems(listDate,day-1);

        //年份选中
        wlYear.setOnItemSelectedListener(new WheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int selectedIndex, String item) {

                if(!TextUtils.isEmpty(item))
                  year=item;
            }
        });

        //月份选中
        wlmouth.setOnItemSelectedListener(new WheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int selectedIndex, String item) {
                if(!TextUtils.isEmpty(item))
                 month=item;

                listDate=loadData(Integer.parseInt(year.substring(0,4)),Integer.parseInt(month.substring(0,2)));
                wldate.setItems(listDate,0);
            }
        });

        //日期选中
        wldate.setOnItemSelectedListener(new WheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int selectedIndex, String item) {
                if(!TextUtils.isEmpty(item))
                 date=item;

            }
        });

        wheelpop.show();
    }



    private List<String> loadData(int nowYear,int month) {
        listDate .clear();
        boolean isRun = isRunNian(nowYear);



            switch (month) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    for (int day = 1; day <= 31; day++) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(nowYear, month - 1, day);
                        listDate.add( day + "日 " + getWeek(calendar.get(Calendar.DAY_OF_WEEK)));
                    }
                    break;
                case 2:
                    if (isRun) {
                        for (int day = 1; day <= 29; day++) {
                            Calendar calendar = Calendar.getInstance();
                            calendar.set(nowYear, month - 1, day);
                            listDate.add(day + "日 " + getWeek(calendar.get(Calendar.DAY_OF_WEEK)));
                        }
                    } else {
                        for (int day = 1; day <= 28; day++) {
                            Calendar calendar = Calendar.getInstance();
                            calendar.set(nowYear, month - 1, day);
                            listDate.add( day + "日 " + getWeek(calendar.get(Calendar.DAY_OF_WEEK)));
                        }
                    }
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    for (int day = 1; day <= 30; day++) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(nowYear, month - 1, day);
                        listDate.add( day + "日 " + getWeek(calendar.get(Calendar.DAY_OF_WEEK)));
                    }
                    break;
                default:
                    break;
            }

        return listDate;
    }



    private String getWeek(int i) {
        switch (i) {
            case Calendar.MONDAY:
                return "周一";
            case Calendar.TUESDAY:
                return "周二";
            case Calendar.WEDNESDAY:
                return "周三";
            case Calendar.THURSDAY:
                return "周四";
            case Calendar.FRIDAY:
                return "周五";
            case Calendar.SATURDAY:
                return "周六";
        }
        return "周日";
    }

    private boolean isRunNian(int year) {
        return (year % 4 == 0 && year % 100 != 0 || year % 400 == 0);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_cancel:   //取消
                if(wheelpop!=null){
                  wheelpop.dismiss();
                }

                break;

            case R.id.tv_save:  //保存
                if(wheelpop!=null){
                    wheelpop.dismiss();
                }

                dateChooseInterface.getDateTime(year+" "+month+" "+date+" ");
                Toast.makeText(context,"你选中了"+year+""+month+""+date+"",Toast.LENGTH_SHORT).show();
                break;
        }
    }


    /**
     * 接口回掉选中的时间和日期  （默认时间格式）
     */
    public interface DateChooseInterface {
        void getDateTime(String time);
    }
}

