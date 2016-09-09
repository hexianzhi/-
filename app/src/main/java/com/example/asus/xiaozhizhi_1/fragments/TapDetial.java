package com.example.asus.xiaozhizhi_1.fragments;

import android.app.Activity;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.asus.xiaozhizhi_1.Adapter.HeadViewAdapter;
import com.example.asus.xiaozhizhi_1.Adapter.NewsListViewApdater;
import com.example.asus.xiaozhizhi_1.Engine.MySingleton;
import com.example.asus.xiaozhizhi_1.Model.Content;
import com.example.asus.xiaozhizhi_1.Model.DataBean;
import com.example.asus.xiaozhizhi_1.Model.HttpConstants;
import com.example.asus.xiaozhizhi_1.R;
import com.example.asus.xiaozhizhi_1.fragments.Base.BasePageDetail;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaozhi on 2016/9/4.
 *
 * extends BasePageDetail 只是为了方便，没有别的意思。
 * 而 newsViewPager 和 ReadViewpager 具有一定的意义。
 */
public class TapDetial extends BasePageDetail {
    private List<DataBean> LatestDatas;
    private List<DataBean> Responsedatas;

    private ImageLoader mImageloader;
    private DisplayImageOptions options;
    private Content mcontent;
    private ListView lv_news;
    private NewsListViewApdater mAdapter;
    private String tempType;
    private String mtype;
    private Boolean isLoading=false;
    private Handler handler=new Handler();

    //HeadView -viewpager init
    private ViewPager HeadView;

    //初始化Viewpager的个数为四
    private int headViewCount;
    private ImageView vp_ivItem;
    private HeadViewAdapter headViewAdapter;



    //top(头条，默认),shehui(社会),guonei(国内),guoji(国际),yule(娱乐),tiyu(体育)junshi(军事),keji(科技),caijing(财经),shishang(时尚)
    public TapDetial(Activity activity, String type){
        super(activity);
        headViewCount=4;
        mtype =type;
        Log.e("TapDetial 初始化，","mtype: "+mtype+","+"type: "+type);
    }

    @Override
    public View initView() {
        //初始化HeadView
        View view=View.inflate(mActivity, R.layout.news_tap_detial, null);
        lv_news=(ListView) view.findViewById(R.id.lv_news);
        //初始化 HeadView
        View header = View.inflate(mActivity,R.layout.header, null);
//        View header = LayoutInflater.inflate(R.layout.header, lv_news, false);
        HeadView=(ViewPager) header.findViewById(R.id.vp_headview);
        lv_news.addHeaderView(header);
        mAdapter=new NewsListViewApdater(mActivity);




        //因为initView 在初始化构造函数时调用，所以只能用临时的方法赋值了。
//        mtype= tempType;
        Log.e("````````````````````````HomeFragment","HomeFragment```initView````initView");

        return  view;
    }

    @Override
    public void initData() {


        //inti headVIew

        headViewAdapter= new HeadViewAdapter(mActivity);
        HeadView.setAdapter(headViewAdapter);

        lv_news.setAdapter(mAdapter);
        lv_news.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (lv_news != null && lv_news.getChildCount() > 0) {
                    if (firstVisibleItem + visibleItemCount == totalItemCount){
                        Log.e("onSrcoll:","test what times listview scroll when meet this contition");}
                    if (firstVisibleItem + visibleItemCount == totalItemCount && !isLoading  ) {
                        loadData();
                    }
                }

            }
        });
        loadData();
    }



    public void loadData() {
        isLoading = true;
        //Use Volley
        Response.Listener<String> listener= new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                Log.e("onResponse:",response.toString());
                parseJson(response);
            }
        };

        Response.ErrorListener errorListener=new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("TAG", volleyError.getMessage(), volleyError);
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.GET, HttpConstants.host +mtype+HttpConstants.suffix,listener,errorListener){};
        Log.e("初始化URL  ·······",HttpConstants.host +mtype+HttpConstants.suffix);
        MySingleton.getInstance(mActivity).addToRequestQueue(stringRequest);
    }

    private void parseJson(String response){

        Content content = JSON.parseObject(response.toString(), Content.class);
        Responsedatas =content.getResult().getData();


        //粗暴的将获取到的数据分出四个出来
        LatestDatas=new ArrayList<DataBean>();
        for (int i=0;i<headViewCount;i++) {
            LatestDatas.add(i,Responsedatas.get(i));
        }



        handler.post(new Runnable() {
            @Override
            public void run() {
                headViewAdapter.addList(LatestDatas);
                mAdapter.addList(Responsedatas);
                isLoading = false;
            }
        });

    }


}
