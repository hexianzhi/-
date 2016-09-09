package com.example.asus.xiaozhizhi_1.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.asus.xiaozhizhi_1.MainActivity;
import com.example.asus.xiaozhizhi_1.R;
import com.example.asus.xiaozhizhi_1.utils.PrefUtils;
import com.example.asus.xiaozhizhi_1.utils.ScreenUtil;

import java.util.ArrayList;

public class GuideActivity extends Activity {


    private static int[] guide_iv_Ids =new int[]{
        R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3
    };

    private ViewPager vP;
    private ArrayList<ImageView> mImageViewList;

    private SharedPreferences sp;

    private int Radius;
    private Button start_btn;
    private LinearLayout ll;
    private int mPointWidth;// 圆点间的距离
    private View viewPoint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();

    }

    private void  initView(){
        Radius=ScreenUtil.dip2px(this,20);
        start_btn=(Button) findViewById(R.id.btn_start);
        ll=(LinearLayout) findViewById(R.id.ll_point_group);
        vP=(ViewPager) findViewById(R.id.vp_guide);
        viewPoint= findViewById(R.id.view_red_point);

        mImageViewList = new ArrayList<ImageView>();

        // 初始化引导页的3个页面
        for (int i = 0; i < guide_iv_Ids.length; i++) {
            ImageView image = new ImageView(this);
            image.setBackgroundResource(guide_iv_Ids[i]);// 设置引导页背景
            mImageViewList.add(image);
        }

        //初始化圆点
        for (int i=0;i< guide_iv_Ids.length;i++){
            View point =new View(this);
            point.setBackgroundResource(R.drawable.shape_point_gray);

            LinearLayout.LayoutParams params= new LinearLayout.LayoutParams(Radius,Radius);

            if(i>0){
                params.leftMargin=Radius;
            }
            point.setLayoutParams(params);
            ll.addView(point);

        }
        ll.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {

                    // 当layout执行结束后回调此方法
                    @Override
                    public void onGlobalLayout() {
                        System.out.println("layout 结束");
                        ll.getViewTreeObserver()
                                .removeOnGlobalLayoutListener(this);
                        mPointWidth = ll.getChildAt(1).getLeft()
                                - ll.getChildAt(0).getLeft();
                        System.out.println("圆点距离:" + mPointWidth);
                    }
                });
        RelativeLayout.LayoutParams params= new RelativeLayout.LayoutParams(Radius,Radius);
        viewPoint.setLayoutParams(params);
        vP.setAdapter(new GuideAdapter());
        vP.addOnPageChangeListener(new GuidePageListener());
        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrefUtils.setBoolean(getApplicationContext(), "is_first_enter", false);

                startActivity(new Intent(GuideActivity.this, MainActivity.class));
                finish();
            }
        });

    }

    class GuidePageListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            int len=(int) (mPointWidth * positionOffset) + position * mPointWidth;
            RelativeLayout.LayoutParams params=(RelativeLayout.LayoutParams) viewPoint.getLayoutParams();
            params.leftMargin=len;

            viewPoint.setLayoutParams(params);

        }

        //呈现某页面
        @Override
        public void onPageSelected(int position) {
            if (position == guide_iv_Ids.length - 1) {// 最后一个页面
                start_btn.setVisibility(View.VISIBLE);// 显示开始体验的按钮
            } else {
                start_btn.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }


     class GuideAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return guide_iv_Ids.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mImageViewList.get(position));
            return mImageViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }




}
