package com.example.administrator.hdx_conference_mvp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.hdx_conference_mvp.R;
import com.example.administrator.hdx_conference_mvp.base.BaseActivity;
import com.example.administrator.hdx_conference_mvp.base.BasePresenter;
import com.example.administrator.hdx_conference_mvp.utils.NestedScrollViewUtils;
import com.example.administrator.hdx_conference_mvp.retrofit.utils.UIUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

/**
 * Created by Administrator on 2018/11/2.
 */

public class Details_Activity extends BaseActivity implements NestedScrollViewUtils.ScrollViewListener, View.OnClickListener {
    private SmartRefreshLayout refreshLayout;
    ;
    private AppBarLayout appBarLayout;
    private LinearLayout headLayout;

    private LinearLayout mToolbarHead;
    private ImageView backImg;
    private TextView nameTvToolbar;
    private ImageView msgImg;

    private LinearLayout mTabLayoutLayout;

    private RelativeLayout llCommodityTab;
    private TextView mCommodityTab;
    private TextView mIvCommodityTab;
    private RelativeLayout llDetailsTab;
    private TextView mShopDetailTab;
    private TextView mIvShopDetailTab;
    private RelativeLayout llCommentTab;
    private TextView mCommentTab;
    private TextView mIvCommentTab;
    private RelativeLayout llRecommendTab;
    private TextView mRecommendTab;
    private TextView mIvRecommendTab;
    private NestedScrollViewUtils scrollViewUtils;
    private LinearLayout mCommodityLayout;

    private LinearLayout mShopDetailLayout;
    private RelativeLayout mCommentItemLayout;

    private LinearLayout mRecommendLayout;


    private int height;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);
        initView();
    }

    //初始化控件
    private void initView() {
        refreshLayout = findViewById(R.id.smartLayout);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar_detail);
        headLayout = (LinearLayout) findViewById(R.id.head_layout);
        mToolbarHead = (LinearLayout) findViewById(R.id.head_toolbar);
        backImg = (ImageView) findViewById(R.id.back_img);
        msgImg = (ImageView) findViewById(R.id.msg_img);
        mTabLayoutLayout = (LinearLayout) findViewById(R.id.tablayout_layout);

        llCommodityTab = (RelativeLayout) findViewById(R.id.ll_commodity_tab);
        mCommodityTab = (TextView) findViewById(R.id.tv_commodity_tab);
        mIvCommodityTab = (TextView) findViewById(R.id.iv_commodity_tab);
        llDetailsTab = (RelativeLayout) findViewById(R.id.ll_details_tab);
        mShopDetailTab = (TextView) findViewById(R.id.tv_details_tab);
        mIvShopDetailTab = (TextView) findViewById(R.id.iv_details_tab);
        llCommentTab = (RelativeLayout) findViewById(R.id.ll_comment_tab);
        mCommentTab = (TextView) findViewById(R.id.tv_comment_tab);
        mIvCommentTab = (TextView) findViewById(R.id.iv_comment_tab);
        llRecommendTab = (RelativeLayout) findViewById(R.id.ll_recommend_tab);
        mRecommendTab = (TextView) findViewById(R.id.tv_recommend_tab);
        mIvRecommendTab = (TextView) findViewById(R.id.iv_recommend_tab);
        scrollViewUtils = (NestedScrollViewUtils) findViewById(R.id.nsv);
        mCommodityLayout = (LinearLayout) findViewById(R.id.commodity_layout);

        mShopDetailLayout = (LinearLayout) findViewById(R.id.shop_detail_layout);
        mCommentItemLayout = (RelativeLayout) findViewById(R.id.comment_item_layout);
        mRecommendLayout = (LinearLayout) findViewById(R.id.recommend_layout);
        llCommodityTab.setOnClickListener(this);
        llDetailsTab.setOnClickListener(this);
        llCommentTab.setOnClickListener(this);
        llRecommendTab.setOnClickListener(this);


        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh();
            }
        });
        refreshLayout.setEnableLoadmore(false);


        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset >= 0) {

                } else {

                }

                if (verticalOffset <= -headLayout.getHeight() / 2) {
                    backImg.setVisibility(View.VISIBLE);
                    msgImg.setVisibility(View.VISIBLE);
                    backImg.setImageResource(R.mipmap.ic_launcher);
                    msgImg.setImageResource(R.mipmap.ic_launcher);
                    //头部导航
                    mTabLayoutLayout.setVisibility(View.VISIBLE);
//                    nameTvToolbar.setVisibility(View.VISIBLE);
//                    nameTvToolbar.setText("坚果-500g");


                } else {
                    backImg.setVisibility(View.VISIBLE);
                    msgImg.setVisibility(View.VISIBLE);
                    // nameTvToolbar.setVisibility(View.INVISIBLE);
                    mTabLayoutLayout.setVisibility(View.GONE);
                    backImg.setImageResource(R.mipmap.ic_launcher);
                    msgImg.setImageResource(R.mipmap.ic_launcher);

                }

            }
        });

        ViewTreeObserver vto = headLayout.getViewTreeObserver();

        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mToolbarHead.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                height = headLayout.getHeight();
                scrollViewUtils.setScrollViewListener(Details_Activity.this);
            }
        });

    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void onScrollChanged(NestedScrollViewUtils scrollView, int x, int y, int oldx, int oldy) {
        if (y < mCommodityLayout.getTop() && y <= mShopDetailLayout.getTop() - mToolbarHead.getHeight()) {
            //商品基础信息
            CommodityColor();
        } else if (y >= mShopDetailLayout.getTop() - mToolbarHead.getHeight() && y <= mCommentItemLayout.getTop() - mToolbarHead.getHeight()) {
            //商品详情
            ShopDetailColor();
        } else if (y >= mCommentItemLayout.getTop() - mToolbarHead.getHeight() && y <= mRecommendLayout.getTop() - mToolbarHead.getHeight()) {
            //评价
            CommentColor();
        } else if (y >= mRecommendLayout.getTop() - mToolbarHead.getHeight()) {
            //推荐
            RecommendColor();
        }

    }


    private void CommodityColor() {
        mCommodityTab.setTextColor(UIUtils.getColor(R.color.youhui_textcolor));
        mIvCommodityTab.setVisibility(View.VISIBLE);

        mShopDetailTab.setTextColor(UIUtils.getColor(R.color.text_color));
        mIvShopDetailTab.setVisibility(View.INVISIBLE);

        mCommentTab.setTextColor(UIUtils.getColor(R.color.text_color));
        mIvCommentTab.setVisibility(View.INVISIBLE);

        mRecommendTab.setTextColor(UIUtils.getColor(R.color.text_color));
        mIvRecommendTab.setVisibility(View.INVISIBLE);
    }


    private void ShopDetailColor() {
        mShopDetailTab.setTextColor(UIUtils.getColor(R.color.youhui_textcolor));
        mIvShopDetailTab.setVisibility(View.VISIBLE);

        mCommodityTab.setTextColor(UIUtils.getColor(R.color.text_color));
        mIvCommodityTab.setVisibility(View.INVISIBLE);

        mCommentTab.setTextColor(UIUtils.getColor(R.color.text_color));
        mIvCommentTab.setVisibility(View.INVISIBLE);

        mRecommendTab.setTextColor(UIUtils.getColor(R.color.text_color));
        mIvRecommendTab.setVisibility(View.INVISIBLE);
    }


    private void CommentColor() {
        mCommentTab.setTextColor(UIUtils.getColor(R.color.youhui_textcolor));
        mIvCommentTab.setVisibility(View.VISIBLE);

        mCommodityTab.setTextColor(UIUtils.getColor(R.color.text_color));
        mIvCommodityTab.setVisibility(View.INVISIBLE);

        mShopDetailTab.setTextColor(UIUtils.getColor(R.color.text_color));
        mIvShopDetailTab.setVisibility(View.INVISIBLE);

        mRecommendTab.setTextColor(UIUtils.getColor(R.color.text_color));
        mIvRecommendTab.setVisibility(View.INVISIBLE);

    }


    private void RecommendColor() {
        mRecommendTab.setTextColor(UIUtils.getColor(R.color.youhui_textcolor));
        mIvRecommendTab.setVisibility(View.VISIBLE);

        mCommentTab.setTextColor(UIUtils.getColor(R.color.text_color));
        mIvCommentTab.setVisibility(View.INVISIBLE);

        mCommodityTab.setTextColor(UIUtils.getColor(R.color.text_color));
        mIvCommodityTab.setVisibility(View.INVISIBLE);

        mShopDetailTab.setTextColor(UIUtils.getColor(R.color.text_color));
        mIvShopDetailTab.setVisibility(View.INVISIBLE);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_commodity_tab:
                scrollViewUtils.scrollTo(0, 0);
                CommodityColor();
                break;
            case R.id.ll_details_tab:
                scrollViewUtils.scrollTo(0, mShopDetailLayout.getTop() - mToolbarHead.getHeight());
                ShopDetailColor();
                break;
            case R.id.ll_comment_tab:
                scrollViewUtils.scrollTo(0, mCommentItemLayout.getTop() - mToolbarHead.getHeight());
                CommentColor();
                break;
            case R.id.ll_recommend_tab:
                scrollViewUtils.scrollTo(0, mRecommendLayout.getTop() - mToolbarHead.getHeight());
                RecommendColor();
                break;
            default:
                break;
        }
    }
}
