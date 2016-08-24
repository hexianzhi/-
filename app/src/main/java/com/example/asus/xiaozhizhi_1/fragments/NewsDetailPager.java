package com.example.asus.xiaozhizhi_1.fragments;

import android.app.Activity;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.example.asus.xiaozhizhi_1.R;
import com.example.asus.xiaozhizhi_1.fragments.Base.BaseMenuDetailPager;

import java.util.ArrayList;

/**
 * Created by xiaozhi on 2016/8/23.
 */
public class NewsDetailPager extends BaseMenuDetailPager {

    private static final String TAG="NewsDetailPager";
    private int[] test_pager_ids=new int[]{
            R.drawable.guide_1,
            R.drawable.guide_1,
            R.drawable.guide_1,
            R.drawable.guide_1,
            R.drawable.guide_1,
            R.drawable.guide_1,
            R.drawable.guide_1,
            R.drawable.guide_1,
            R.drawable.guide_1,
            R.drawable.guide_1,
            R.drawable.guide_1,

    };
    private ViewPager mViewPager;
    private ArrayList<ImageView> imageViews;

    public NewsDetailPager(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {
        View view=View.inflate(mActivity,R.layout.news_detail,null);
        mViewPager =(ViewPager) view.findViewById(R.id.vp_detail);
        return view;
    }

    @Override
    public void initData() {
        imageViews = new ArrayList<ImageView>();
        for(int i=0;i<test_pager_ids.length;i++){
            ImageView imageView=new ImageView(mActivity);
            imageView.setBackgroundResource(test_pager_ids[i]);
            imageViews.add(imageView);
        }

        mViewPager.setAdapter(new NewsViewPager());
        Log.e("NewsDetailPager```````````","```````NewsDetailPager`````initData");
    }

    class NewsViewPager extends PagerAdapter{

        @Override
        public int getCount() {
            return test_pager_ids.length;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
           container.addView(imageViews.get(position));
            return imageViews.get(position);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
