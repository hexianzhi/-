package com.example.asus.xiaozhizhi_1.fragments;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.xiaozhizhi_1.R;
import com.example.asus.xiaozhizhi_1.fragments.Base.BasePageDetail;
import com.example.asus.xiaozhizhi_1.view.ViewPagerIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xiaozhi on 2016/8/23.
 */
public class NewsViewpager extends BasePageDetail {

    private static final String TAG="NewsViewpager";
    private ViewPager mViewPager;
    private List<TapDetial> tapDetialList;

    private String[] topics;
    private List<String> topicsName;


    private ViewPagerIndicator mIndicator;
    public NewsViewpager(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {
        View view=View.inflate(mActivity, R.layout.news_detail,null);
        mViewPager =(ViewPager) view.findViewById(R.id.vp_detail);
        mIndicator=(ViewPagerIndicator) view.findViewById(R.id.indicator);
        return view;
    }

    @Override
    public void initData() {

        //初始化新闻主题，以后当做更改主题控件时再作扩展了。只改一处而已。
        topics=new String[]{"top","shehui","guonei","guoji","yule","tiyu","junshi","keji","caijing","shishang" };
        topicsName= Arrays.asList("头条","社会","国内","国际","娱乐","体育","军事","科技","财经","时尚");
        //初始化VIewpager,数据
        tapDetialList=new ArrayList<TapDetial>();
        for (int i = 0; i < topics.length; i++) {
            TapDetial pager = new TapDetial(mActivity, topics[i]);
            pager.initData();
            tapDetialList.add(pager);
        }

        mViewPager.setAdapter(new NewsViewPager());
        mIndicator.setTabItemTitles(topicsName);
        mIndicator.setViewPager(mViewPager,0);

        Log.e("NewsViewpager```````````","```````NewsViewpager`````initData");
    }




    class NewsViewPager extends PagerAdapter{

        // 指定指示器的标题
        @Override
        public CharSequence getPageTitle(int position) {

            return  topicsName.get(position);
        }
        @Override
        public int getCount() {
            return  tapDetialList.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
           TapDetial tap =tapDetialList.get(position);
            View view = tap.mRootView;
            container.addView(view);
            return view;
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
