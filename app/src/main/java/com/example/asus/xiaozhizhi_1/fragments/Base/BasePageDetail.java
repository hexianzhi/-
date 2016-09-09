package com.example.asus.xiaozhizhi_1.fragments.Base;

import android.app.Activity;
import android.view.View;

/**
 * 新闻页面中，ViewPage 具体内容
 *
 *
 *
 *
 */

public abstract class BasePageDetail {

	public Activity mActivity;
	public View mRootView;// 菜单详情页根布局

	public BasePageDetail(Activity activity) {
		mActivity = activity;
		mRootView = initView();
	}

	// 初始化布局,必须子类实现
	public abstract View initView();

	// 初始化数据
	public void initData() {

	}

}
