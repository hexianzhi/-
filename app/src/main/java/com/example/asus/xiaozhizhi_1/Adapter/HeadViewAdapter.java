package com.example.asus.xiaozhizhi_1.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.asus.xiaozhizhi_1.Model.DataBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HeadViewAdapter extends PagerAdapter {

    private ImageLoader mImageloader;
    private DisplayImageOptions options;
    private List<DataBean> mLatestDatas;
    private Context mContext;

    public HeadViewAdapter(Context context){
        mContext=context;
        mLatestDatas=new ArrayList<DataBean>();
        mImageloader =   ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
    }

    public void addList(List<DataBean> mDatas){
       this.mLatestDatas.addAll(mDatas);
        notifyDataSetChanged();

    }

    @Override
    public int getCount() {

        return mLatestDatas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        DataBean dataBean= mLatestDatas.get(position);
        ImageView vp_ivItem=new ImageView(mContext);
        vp_ivItem.setScaleType(ImageView.ScaleType.FIT_XY);

        mImageloader.displayImage(dataBean.getThumbnail_pic_s03(), vp_ivItem, options);
        if(vp_ivItem ==null) {
            Log.e("test why viewpager imageview is null??", "vp_ivItem is null");
        }else {
            Log.e("test why viewpager imageview is null??", "vp_ivItem isn't null");
        }
        container.addView(vp_ivItem);
        return vp_ivItem;
    }



    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}