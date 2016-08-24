package com.example.asus.xiaozhizhi_1;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.example.asus.xiaozhizhi_1.fragments.MainFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG_LEFT_MENU = "TAG_LEFT_MENU";
    private static final String TAG_CONTENT = "TAG_CONTENT";

    private ViewPager viewPager;
    private MainFragment mainFragment;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题,
        // 必须在setContentView之前调用
        setContentView(R.layout.activity_main);

        // Utils.doSomthing();
        // R.drawable.p_10
      /*  setBehindContentView(R.layout.left_menu);
        SlidingMenu slidingMenu = getSlidingMenu();
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);// 全屏触摸
        slidingMenu.setBehindOffset(200);// 屏幕预留200像素宽度*/
        setStatusBarColor(getResources().getColor(android.R.color.holo_red_dark));
        initFragment();
    }


    /*public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.rb_news:
                if (checked)
                    viewPager.setCurrentItem(0, false);// 参2:表示是否具有滑动动画
                break;
            case R.id.rb_read:
                if (checked)
                    viewPager.setCurrentItem(1, false);// 参2:表示是否具有滑动动画
                break;
            case R.id.rb_radio:
                if (checked)
                    viewPager.setCurrentItem(2, false);// 参2:表示是否具有滑动动画
            case R.id.rb_topic:
                if (checked)
                    viewPager.setCurrentItem(3, false);// 参2:表示是否具有滑动动画
                break;
            case R.id.rb_me:
                if (checked)
                    viewPager.setCurrentItem(4, false);// 参2:表示是否具有滑动动画
                break;
        }
    }*/
    /**
     * 初始化fragment
     */
    private void initFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();// 开始事务
        transaction.replace(R.id.fl_main, new MainFragment(), TAG_CONTENT);
        transaction.commit();// 提交事务
/*        mainFragment=new MainFragment();
        viewPager=  mainFragment.getContentPager();*/

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
