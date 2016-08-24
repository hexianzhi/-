package com.example.asus.xiaozhizhi_1.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 版 权 ： 小智 版权所有 (c) 2016
 * <p/>
 * 作 者 : 何显智
 * <p/>
 * 版 本 ： 1.0
 * <p/>
 * 创建日期 ： 2016/8/20 16:12
 **/
public class PrefUtils {

    public static final String PREF_NAME = "config";

    public static Boolean getBoolean(Context context,String key){
        SharedPreferences sp=context.getSharedPreferences(PREF_NAME,context.MODE_APPEND);
       return  sp.getBoolean(key,false);
    }

    public static void setBoolean(Context context,String key,boolean value)
    {
        SharedPreferences sp=context.getSharedPreferences(PREF_NAME,context.MODE_PRIVATE);
        sp.edit().putBoolean(key,value).commit();
    }

}
