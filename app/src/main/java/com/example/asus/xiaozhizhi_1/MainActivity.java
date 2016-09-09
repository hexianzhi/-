package com.example.asus.xiaozhizhi_1;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.example.asus.xiaozhizhi_1.fragments.MainFragment;
import com.example.asus.xiaozhizhi_1.fragments.TagsPager.ReadPager;

public class MainActivity extends FragmentActivity {

    private static final String TAG_LEFT_MENU = "TAG_LEFT_MENU";
    private static final String TAG_CONTENT = "TAG_CONTENT";
    private static final int testApi=1;

    private ViewPager viewPager;
    private MainFragment mainFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题,
        // 必须在setContentView之前调用
        setContentView(R.layout.activity_main);
        setStatusBarColor(getResources().getColor(android.R.color.holo_red_dark));

        initFragment();
    }


    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case testApi:
                    ReadPager p=new ReadPager(MainActivity.this);
                           p.setText(msg.obj.toString());
                    Log.e("ReadPager````````````","``````````ReadPager`````setText"+msg.obj.toString());

                    break;
            }

        }
    };

    /**
     * 初始化fragment
     */
    private void initFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();// 开始事务
        transaction.replace(R.id.fl_main, new MainFragment(), TAG_CONTENT);
        transaction.commit();// 提交事务
    }

    // 获取主页fragment对象
    public MainFragment getMainFragment() {
        FragmentManager fm = getSupportFragmentManager();
        MainFragment fragment = (MainFragment) fm
                .findFragmentByTag(TAG_CONTENT);// 根据标记找到对应的fragment
        return fragment;
    }


    @TargetApi(21)
    private void setStatusBarColor(int statusBarColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // If both system bars are black, we can remove these from our layout,
            // removing or shrinking the SurfaceFlinger overlay required for our views.
            Window window = this.getWindow();
            if (statusBarColor == Color.BLACK && window.getNavigationBarColor() == Color.BLACK) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            } else {
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            }
            window.setStatusBarColor(statusBarColor);
        }
    }
}
