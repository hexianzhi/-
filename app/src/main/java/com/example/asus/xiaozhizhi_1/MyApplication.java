package com.example.asus.xiaozhizhi_1;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

// 请在AndroidManifest.xml中application标签下android:name中指定该类
public class MyApplication extends Application {
    
    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader(getApplicationContext());
    }
    private void initImageLoader(Context context) {
        File cacheDir = StorageUtils.getCacheDirectory(context);
/*        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context).threadPoolSize(3)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .diskCache(new UnlimitedDiskCache(cacheDir)).writeDebugLogs()
                .build();*/

        ImageLoaderConfiguration config = ImageLoaderConfiguration.createDefault(context);

        com.nostra13.universalimageloader.core.ImageLoader.getInstance().init(config);
    }
}
