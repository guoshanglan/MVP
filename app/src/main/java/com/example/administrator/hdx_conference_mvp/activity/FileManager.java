package com.example.administrator.hdx_conference_mvp.activity;

import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.hdx_conference_mvp.R;
import com.example.administrator.hdx_conference_mvp.adapter.FileManageAdapter;
import com.example.administrator.hdx_conference_mvp.base.BaseActivity;
import com.example.administrator.hdx_conference_mvp.base.BasePresenter;
import com.example.administrator.hdx_conference_mvp.bean.FileBean;
import com.example.administrator.hdx_conference_mvp.utils.SubDirectoriesAndSize;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/11/8.
 * 文件管理
 */

public class FileManager extends BaseActivity {
    @BindView(R.id.iv_back)
    public ImageView ivBack;
    @BindView(R.id.recycler_filemanager)
    public RecyclerView recyclerView;
    public FileManageAdapter adapter;
    public List<FileBean>myFileList;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_manager_layout);
        ButterKnife.bind(this);
        initData();
    }

    //初始化数据

    private void initData() {
        LinearLayoutManager lm= new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.VERTICAL);//实现横向的关键点，绝对不能漏
        recyclerView.setLayoutManager(lm);
        myFileList=new ArrayList<>();
        adapter=new FileManageAdapter(this,myFileList);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onResume() {
        super.onResume();
        initMyFile(Environment.getExternalStorageDirectory());
        recyclerView.setAdapter(adapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void initMyFile(File sourceFile) {
        myFileList.clear();
        List<File> files = new ArrayList<>();
        Collections.addAll(files, sourceFile.listFiles());
        for (File file : files) {
            String fileName = file.getName();
            //默认是未知文件
            int imageId = R.drawable.meinv;

            //下面开始判断
            if (file.isDirectory()) {
                imageId = R.drawable.meinv;
            } else {
                //如果是文件，就从文件名的后缀名来判断是什么文件，从而添加对应图标
                //获取后缀名前的分隔符"."在fName中的位置。
                int dotIndex = fileName.lastIndexOf(".");
                if(dotIndex >= 0){
                    /* 获取文件的后缀名*/
                    String end= fileName.substring(dotIndex,fileName.length()).toLowerCase();
                    if(!Objects.equals(end, "")){
                        if (Objects.equals(end, ".mp3")||Objects.equals(end, ".ape")
                                ||Objects.equals(end, ".flac")||Objects.equals(end, ".m4a")
                                ||Objects.equals(end, ".ape")||Objects.equals(end, ".wav")
                                ||Objects.equals(end, ".aac")){
                            //如果是音乐文件
                            imageId = R.drawable.ic_photo;
                        }else if (Objects.equals(end, ".mp4")||Objects.equals(end, ".mkv")
                                ||Objects.equals(end, ".avi")||Objects.equals(end, ".rmvb")
                                ||Objects.equals(end, ".rm")||Objects.equals(end, ".mov")
                                ||Objects.equals(end, ".mpeg")){
                            //如果是影视文件
                            imageId = R.drawable.meinv;
                        }
                    }
                }
            }

            String fileSize = "";
            long size = 0;
            //下面开始判断文件大小
            if (file.isDirectory()) {
                //如果是文件夹就要求出占用大小 = 总大小 - 可用大小
                try {
                    size = SubDirectoriesAndSize.getTotalSizeOfFilesInDir(file);   //获取文件大小
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                size = file.length();
            }
            //判断大小是该用什么单位,K\M\G
            if (size / 1024 / 1024 / 1024 > 0) {
                fileSize = size / 1024 / 1024 / 1024 + " G";
            } else if (size / 1024 / 1024 > 0) {
                fileSize = size / 1024 / 1024 + " M";
            } else {
                fileSize = size / 1024 + " K";
            }

            String filePower = "";
            StringBuilder builder = new StringBuilder();
            builder.append("-");
            if (file.canRead()) builder.append("r");
            if (file.canWrite()) builder.append("w");
            if (file.canExecute()) builder.append("x");
            filePower = builder.toString();

            String fileDate = "";
            fileDate = getModifiedTime_2(file);    //获取文件的修改日期

            FileBean myFile = new FileBean(imageId,fileName, fileSize, filePower, fileDate);
            myFileList.add(myFile);
        }
        adapter.notifyDataSetChanged();
    }











    //获取文件修改时间
    public static String getModifiedTime_2(File f){
        Calendar cal = Calendar.getInstance();
        long time = f.lastModified();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        cal.setTimeInMillis(time);
        return formatter.format(cal.getTime());
    }




    @OnClick({R.id.iv_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_back:   //返回
                       finish();
                break;

        }

    }





    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
