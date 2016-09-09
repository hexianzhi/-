package com.example.asus.xiaozhizhi_1.Adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.example.asus.xiaozhizhi_1.Engine.MySingleton;
import com.example.asus.xiaozhizhi_1.Model.DataBean;
import com.example.asus.xiaozhizhi_1.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaozhi on 2016/8/29.
 */
public class NewsListViewApdater extends BaseAdapter{
    private Activity mActivity;
    private List<DataBean> DataBeans;
    private RequestQueue requestQueue;
    private ImageLoader mImageloader;
    private DisplayImageOptions options;
    private Bitmap bitmap;
    public NewsListViewApdater(Activity mActivity){
        this.mActivity=mActivity;
        DataBeans=new ArrayList<>();
        requestQueue= MySingleton.getInstance(mActivity).getRequestQueue();
        mImageloader =   ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        Log.e("````````````````````````NewsListViewApdater","NewsListViewApdater```NewsListViewApdater````NewsListViewApdater");
    }


    public void addList(List<DataBean> items) {
        this.DataBeans.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return DataBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return DataBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder=null;
        if (convertView ==null){

            holder=new ViewHolder();
            convertView=View.inflate(mActivity, R.layout.news_tap_listview_item,null);
            holder.imageView =(ImageView) convertView.findViewById(R.id.iv_Home);
            holder.tvTime=(TextView) convertView.findViewById(R.id.tv_time);
            holder.tvTitle=(TextView)convertView.findViewById(R.id.tv_title);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder)convertView.getTag();
        }

        DataBean mdataBean=DataBeans.get(position);

         holder.tvTitle.setText(mdataBean.getTitle());

        //根据时间判断是否为今天

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String date=sdf.format(new java.util.Date());
        Log.e("date: ",date);
        String responseData=mdataBean.getDate().substring(0,10);

        if (responseData.equals(date)) {
            holder.tvTime.setText("今日");
        }else {
            holder.tvTime.setText(responseData);
        }
        //Volley 自带的 ImageLoader
        //holder.imageView.setImageUrl(mdataBean.getThumbnail_pic_s(),mImageloader);


        //Universal-image-loader 使用
        mImageloader.displayImage(mdataBean.getThumbnail_pic_s(), holder.imageView, options);

        Log.e("````````````````````````NewsListViewApdater","holder```holder````holder");
        return convertView;
    }


    private class ViewHolder{
        private ImageView imageView;
        private TextView tvTitle;
        private TextView tvTime;
    }


}
