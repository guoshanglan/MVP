package com.example.administrator.hdx_conference_mvp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.hdx_conference_mvp.R;
import com.example.administrator.hdx_conference_mvp.bean.FileBean;

import java.util.List;

/**
 * Created by Administrator on 2019/3/27.
 */

public class FileManageAdapter extends RecyclerView.Adapter<FileManageAdapter.ViewHolder>{
    public Context mcontext;
    public List<FileBean>fileBeanList;

    public FileManageAdapter(Context mcontext, List<FileBean> fileBeanList) {
        this.mcontext = mcontext;
        this.fileBeanList = fileBeanList;
    }

    @NonNull
    @Override
    public FileManageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mcontext==null){
            mcontext=parent.getContext();
        }
        View view=View.inflate(mcontext,R.layout.file_manager_item,null);
        ViewHolder viewHolder=new ViewHolder(view);


        return viewHolder;
    }


    //赋值
    @Override
    public void onBindViewHolder(@NonNull FileManageAdapter.ViewHolder holder, int position) {
       FileBean  filebean=fileBeanList.get(position);
        Glide.with(mcontext).load(filebean.ImageId).into(holder.ivFile);
        holder.tvFileName.setText(filebean.getName());
        holder.tvDate.setText(filebean.getDate());
        holder.tvSize.setText(filebean.getSize());
        holder.tvPower.setText(filebean.getPower());

    }

    @Override
    public int getItemCount() {
        return fileBeanList==null?0:fileBeanList.size();
    }


    public class  ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivFile;
        public TextView tvFileName,tvSize,tvDate,tvPower;

        public ViewHolder(View itemView) {
            super(itemView);
            ivFile=itemView.findViewById(R.id.myFileImage);
            tvFileName=itemView.findViewById(R.id.myFileName);
            tvSize=itemView.findViewById(R.id.fileSize);
            tvDate=itemView.findViewById(R.id.fileDate);
            tvPower=itemView.findViewById(R.id.filePower);
        }

    }



}
