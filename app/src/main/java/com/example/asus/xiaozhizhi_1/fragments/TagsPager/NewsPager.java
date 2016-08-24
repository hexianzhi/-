package com.example.asus.xiaozhizhi_1.fragments.TagsPager;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.asus.xiaozhizhi_1.fragments.Base.BasePager;
import com.example.asus.xiaozhizhi_1.fragments.NewsDetailPager;

import java.util.ArrayList;

/**
 * 首页
 * 
 * @author Kevin
 * @date 2015-10-18
 */
public class NewsPager extends BasePager {

	private static final String TAG="NewsPager";

	private ArrayList<ImageView> imageViews;

	public NewsPager(Activity activity) {
		super(activity);
	}
	@Override
	public void initData() {
		System.out.println("首页初始化啦...");
		// 修改页面标题
		tvTitle.setText("网易");
		setNewsDetailPager();
		Log.e("NewsPager```````````","```````NewsPager`````initData");

	}


	public void setNewsDetailPager() {
		// 重新给frameLayout添加内容
		NewsDetailPager newsDetailPager=new NewsDetailPager(mActivity);
		View view = newsDetailPager.mRootView;// 当前页面的布局

		// 清除之前旧的布局
		flContent.removeAllViews();

		flContent.addView(view);// 给帧布局添加布局
		Log.e("NewsPager```````````","```````NewsPager`````flContent```addView");
		// 初始化页面数据
		newsDetailPager.initData();

	}
}
