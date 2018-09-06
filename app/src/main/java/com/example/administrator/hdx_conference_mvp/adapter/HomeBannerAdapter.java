package com.example.administrator.hdx_conference_mvp.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.hdx_conference_mvp.R;

import java.util.List;

/**
 * Created by Administrator on 2018/8/31.
 */

public class HomeBannerAdapter extends PagerAdapter {

    private List<String> bannerList;
    private Context mContext;


    public HomeBannerAdapter(Context context, List<String> bannerList) {
        this.bannerList = bannerList;
        this.mContext = context;

    }



    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public int getCount() {
        return bannerList == null ? 0 : bannerList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public Object instantiateItem(ViewGroup view, final int position) {
        View layoutview = LayoutInflater.from(mContext).inflate(R.layout.listitem_square_top_list, null);
        ImageView image = (ImageView) layoutview.findViewById(R.id.iv_image);

        image.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        /*final DongtaiModel.ResultBean.BannerlistBean info = bannerList.get(position);
        // application.mImageLoader.displayImage(info.getLogo(), image);

        //先判断当前的主线程是否还存在，如果存在，就开始加载图片
        try {
            Glide.with(mContext).load(info.getLogo()).into(image);
        }catch (Exception e){

        }*/
        ((ViewPager) view).addView(layoutview, 0);

      /*  layoutview.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View positioin) {
                if (bannerList.get(position).getType()==0) {
                    if (bannerList.get(position).getValue() != null && !TextUtils.isEmpty(bannerList.get(position).getValue())) {
                        Intent intent = new Intent(mContext, BannerDetails.class).
                                putExtra("url", bannerList.get(position).getValue() + "");
                        mContext.startActivity(intent);
                    }
                }else if (bannerList.get(position).getType()==1){
                    Intent intent = new Intent(mContext, ZhanShangDetailActivity.class).
                            putExtra("id", bannerList.get(position).getValue() + "");
                    mContext.startActivity(intent);
                }

            }
        });*/
        return layoutview;
    }
}
