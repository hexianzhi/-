package com.example.asus.xiaozhizhi_1.fragments.TagsPager;

import android.app.Activity;

import com.example.asus.xiaozhizhi_1.fragments.Base.BasePager;

/**
 * 新闻中心
 * 
 * @author Kevin
 * @date 2015-10-18
 */
public class ReadPager extends BasePager {

//	private ArrayList<BaseMenuDetailPager> mMenuDetailPagers;// 菜单详情页集合
//	private NewsMenu mNewsData;// 分类信息网络数据

	public ReadPager(Activity activity) {
		super(activity);
	}

	@Override
	public void initData() {
		System.out.println("新闻中心初始化啦...");
		// 修改页面标题
		tvTitle.setText("阅读");

 /*
		// 先判断有没有缓存,如果有的话,就加载缓存
		String cache = CacheUtils.getCache(GlobalConstants.CATEGORY_URL,
				mActivity);
		if (!TextUtils.isEmpty(cache)) {
			System.out.println("发现缓存啦...");
			processData(cache);*/

	}


/*
	*//**
	 * 解析数据
	 *//*
	protected void processData(String json) {
		// Gson: Google Json
		Gson gson = new Gson();
		mNewsData = gson.fromJson(json, NewsMenu.class);
		System.out.println("解析结果:" + mNewsData);

		// 获取侧边栏对象
		MainActivity mainUI = (MainActivity) mActivity;
		LeftMenuFragment fragment = mainUI.getLeftMenuFragment();

		// 给侧边栏设置数据
		fragment.setMenuData(mNewsData.data);

		// 初始化4个菜单详情页
		mMenuDetailPagers = new ArrayList<BaseMenuDetailPager>();
		mMenuDetailPagers.add(new NewsMenuDetailPager(mActivity));
		mMenuDetailPagers.add(new TopicMenuDetailPager(mActivity));
		mMenuDetailPagers.add(new PhotosMenuDetailPager(mActivity));
		mMenuDetailPagers.add(new InteractMenuDetailPager(mActivity));

		// 将新闻菜单详情页设置为默认页面
		setCurrentDetailPager(0);
	}

	// 设置菜单详情页
	public void setCurrentDetailPager(int position) {
		// 重新给frameLayout添加内容
		BaseMenuDetailPager pager = mMenuDetailPagers.get(position);// 获取当前应该显示的页面
		View view = pager.mRootView;// 当前页面的布局

		// 清除之前旧的布局
		flContent.removeAllViews();

		flContent.addView(view);// 给帧布局添加布局

		// 初始化页面数据
		pager.initData();

		// 更新标题
		tvTitle.setText(mNewsData.data.get(position).title);
	}*/

}
