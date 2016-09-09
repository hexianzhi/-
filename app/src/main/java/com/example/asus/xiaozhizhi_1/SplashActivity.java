package com.example.asus.xiaozhizhi_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.asus.xiaozhizhi_1.Activities.GuideActivity;
import com.example.asus.xiaozhizhi_1.utils.PrefUtils;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splah);
        jumpNextPage();
    }

    private void jumpNextPage() {
        // 判断之前有没有显示过新手引导
        boolean userGuide = PrefUtils.getBoolean(this, "is_user_guide_showed");

        if (!userGuide) {
            // 跳转到新手引导页
            startActivity(new Intent(SplashActivity.this, GuideActivity.class));
        } else {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
        }

        finish();
    }
}
