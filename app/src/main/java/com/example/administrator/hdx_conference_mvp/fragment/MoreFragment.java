package com.example.administrator.hdx_conference_mvp.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.hdx_conference_mvp.R;
import com.example.administrator.hdx_conference_mvp.activity.FileManager;
import com.example.administrator.hdx_conference_mvp.activity.LoginActivity;
import com.example.administrator.hdx_conference_mvp.base.BaseFragment;
import com.example.administrator.hdx_conference_mvp.base.BasePresenter;
import com.example.administrator.hdx_conference_mvp.utils.SelectPhoto;
import com.example.administrator.hdx_conference_mvp.widview.CircleImageView;
import com.zxinglibrary.android.CaptureActivity;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/8/28.
 */

public class MoreFragment extends BaseFragment {
    private Unbinder unbinder;
    public View view;
    @BindView(R.id.iv_touxiang_more)
    public CircleImageView circleImageView;   //头像
    @BindView(R.id.rl_gerenziliao_more)
    public RelativeLayout rlProfile;  //个人资料
    @BindView(R.id.tv_username_more)
    public TextView tvUserName;  //姓名
    @BindView(R.id.tv_zhiwu_more)
    public TextView tvZhiwu; //职务
    @BindView(R.id.tv_out)
    public TextView tvOutLogin;  //退出登录
    @BindView(R.id.rl_saoma)
    public RelativeLayout rlSaoma;
    public SelectPhoto mSelectPhoto;
    private File tempFile;
    private int CAMERA_REQUEST_CODE = 1, ALBUM_REQUEST_CODE = 2, CROP_REQUEST_CODE = 3;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=View.inflate(getActivity(), R.layout.more_fragment,null);
        //返回一个Unbinder值（进行解绑），注意这里的this不能使用getActivity()
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    //初试话数据
    private void initData() {
        mSelectPhoto = new SelectPhoto(getActivity());
        mSelectPhoto.initCallback(new SelectPhoto.PhotoCallback() {
            @Override
            public void onCarema(int type) {
                if (type == 1) {   //打开照相机
                    getPermission(1);
                } else if (type == 2) {  //打开相册
                    getPicFromAlbm();
                }
            }
        });
    }

    public void getPermission(final int a) {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            //权限发生了改变 true  //  false 小米
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA)) {


                new AlertDialog.Builder(getActivity()).setTitle("title")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 请求授权
                                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 1);
                                if (a == 1) {
                                    getPermission(1);
                                } else {
                                    getPermission(2);
                                }

                            }
                        }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create().show();


            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 1);

            }

        } else {
            if (a == 1) {
                getPicFromCamera();
            } else {
                startActivity(new Intent(getActivity(), CaptureActivity.class));
            }

        }

    }


    /**
     * 打开相机获取图片
     */
    private void getPicFromCamera() {
        //用于保存调用相机拍照后所生成的文件
        tempFile = new File(Environment.getExternalStorageDirectory().getPath(), System.currentTimeMillis() + ".jpg");
        //跳转到调用系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //判断版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {   //如果在Android7.0以上,使用FileProvider获取Uri
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(getActivity(), "com.example.administrator.hdx_conference_mvp", tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
        } else {    //否则使用Uri.fromFile(file)方法获取Uri
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        }
        startActivityForResult(intent, CAMERA_REQUEST_CODE);
    }


    /**
     * 从相册获取图片
     */
    private void getPicFromAlbm() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, ALBUM_REQUEST_CODE);
    }


    /**
     * 裁剪图片
     */
    private void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);

        startActivityForResult(intent, CROP_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case 1:   //调用相机后返回
                if (resultCode != 0) {
                    //用相机返回的照片去调用剪裁也需要对Uri进行处理
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Uri contentUri = FileProvider.getUriForFile(getActivity(), "com.example.administrator.hdx_conference_mvp", tempFile);
                        cropPhoto(contentUri);
                    } else {
                        cropPhoto(Uri.fromFile(tempFile));
                    }
                }
                break;
            case 2:    //调用相册后返回
                if (resultCode != 0) {
                    Uri uri = intent.getData();
                    cropPhoto(uri);
                }
                break;
            case 3:     //调用剪裁后返回
                Bundle bundle = intent.getExtras();
                if (bundle != null) {
                    //在这里获得了剪裁后的Bitmap对象，可以用于上传
                    Bitmap image = bundle.getParcelable("data");
                    //设置到ImageView上
                    circleImageView.setImageBitmap(image);
                    //也可以进行一些保存、压缩等操作后上传
//                    String path = saveImage("crop", image);
                }
                break;
        }
    }


    @OnClick({R.id.rl_gerenziliao_more, R.id.tv_out, R.id.rl_my_erweima, R.id.rl_saoma})
    public void Onclick(View view){
        switch (view.getId()){
            case R.id.rl_gerenziliao_more:   //跳转到个人资料

                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;

            case R.id.rl_my_erweima:  //选择图片
                mSelectPhoto.inintDialog();  //初始化弹窗
                break;
            case R.id.rl_saoma:
                getPermission(2);
                break;

            case R.id.tv_out:    //退出登录，同时清除本地缓存

                startActivity(new Intent(getActivity(), FileManager.class));
                break;
        }
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    /**
     * onDestroyView中进行解绑操作
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
