package com.example.asus.xiaozhizhi_1.fragments;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.asus.xiaozhizhi_1.R;
import com.example.asus.xiaozhizhi_1.fragments.Base.BaseFragment;
import com.example.asus.xiaozhizhi_1.fragments.Base.BasePager;
import com.example.asus.xiaozhizhi_1.fragments.TagsPager.MePager;
import com.example.asus.xiaozhizhi_1.fragments.TagsPager.NewsPager;
import com.example.asus.xiaozhizhi_1.fragments.TagsPager.RadioPager;
import com.example.asus.xiaozhizhi_1.fragments.TagsPager.ReadPager;
import com.example.asus.xiaozhizhi_1.fragments.TagsPager.TopicsPager;
import com.example.asus.xiaozhizhi_1.view.NoScrollViewPager;

import java.util.ArrayList;

/**
 * 主页面fragment
 *
 * @author Kevin
 * @date 2015-10-18
 */
public class MainFragment extends BaseFragment {

    private NoScrollViewPager mViewPager;
    private RadioGroup rgGroup;

    private ArrayList<BasePager> mPagers;// 五个标签页的集合

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_content, null);
        mViewPager = (NoScrollViewPager) view.findViewById(R.id.vp_content);
        rgGroup = (RadioGroup) view.findViewById(R.id.rg_group);
        return view;
    }

    @Override
    public void initData() {
        Log.e("MainFragment begin", "```````````````````````````initData begin");
        mPagers = new ArrayList<BasePager>();
    /*	// 添加五个标签页
		mPagers.add(new NewsPager(mActivity));
		mPagers.add(new ReadPager(mActivity));
		mPagers.add(new SmartServicePager(mActivity));
		mPagers.add(new GovAffairsPager(mActivity));
		mPagers.add(new SettingPager(mActivity));*/
        // 添加五个标签页
        mPagers.add(new NewsPager(mActivity));
        mPagers.add(new ReadPager(mActivity));
        mPagers.add(new RadioPager(mActivity));
        mPagers.add(new TopicsPager(mActivity));
        mPagers.add(new MePager(mActivity));

        mViewPager.setAdapter(new ContentAdapter());

        // 底栏标签切换监听
        rgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_news:
                        mViewPager.setCurrentItem(0, false);// 参2:表示是否具有滑动动画
                        Log.e("MainFragment~~~~~ select rb_news", "```````````````````");
                        break;
                    case R.id.rb_read:
                        // 新闻中心
                        mViewPager.setCurrentItem(1, false);
                        Log.e("MainFragment~~~~~ select rb_read", "```````````````````");
                        break;
                    case R.id.rb_radio:
                        // 智慧服务
                        mViewPager.setCurrentItem(2, false);
                        Log.e("MainFragment~~~~~ select rb_radio", "```````````````````");
                        break;
                    case R.id.rb_topic:
                        // 政务
                        mViewPager.setCurrentItem(3, false);
                        Log.e("MainFragment~~~~~ select rb_topic", "```````````````````");
                        break;
                    case R.id.rb_me:
                        // 设置
                        mViewPager.setCurrentItem(4, false);
                        Log.e("MainFragment~~~~~ select rb_me", "```````````````````");
                        break;

                    default:
                        break;
                }
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                BasePager pager = mPagers.get(position);
                pager.initData();
            }
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        // 手动加载第一页数据
    mPagers.get(0).initData();

    }


    class ContentAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mPagers.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BasePager pager = mPagers.get(position);
            View view = pager.mRootView;// 获取当前页面对象的布局

            // pager.initData();// 初始化数据, viewpager会默认加载下一个页面,
            // 为了节省流量和性能,不要在此处调用初始化数据的方法

            container.addView(view);

            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }


    public ViewPager getContentPager(){
        return this.mViewPager;
    }

/*
	// 获取新闻中心页面
	public ReadPager getNewsCenterPager() {
		ReadPager pager = (ReadPager) mPagers.get(1);
		return pager;
	}
*/

    }

