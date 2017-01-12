package project.first.lal.common.Glide;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.GlideModule;

import project.first.lal.common.utils.FileUtils;
import project.first.lal.common.utils.SDCardUtils;

/**
 * user:zhuwt
 *
 * @Description: ${todo}(这里用一句话描述这个类的作用)
 * @ClassName: ${type_name}.java
 * 2017/1/11
 * @说明 代码版权归 作者 所有
 */
public class FuliModule implements GlideModule {

    private static final int DISK_SIZE = 1024 * 1024 * 500;
    private static final int MEMORY_SIZE = (int) (Runtime.getRuntime().maxMemory()/5);


    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        //定义缓存大小和位置
        String diskPath;
        //是否有SD卡
        if (SDCardUtils.isSDCardEnable())
            diskPath = SDCardUtils.getSDCardPath()+"fuli";
        else
            diskPath = SDCardUtils.getRootDirectoryPath()+"fuli";
        if (!FileUtils.isFileExist(diskPath)){
            FileUtils.makeFolders(diskPath);
        }
        //设置SD卡缓存目录
        builder.setDiskCache(new DiskLruCacheFactory(diskPath,"FuliModule",DISK_SIZE));
        //设置内存缓存
        builder.setMemoryCache(new LruResourceCache(MEMORY_SIZE));
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
//        glide.register(Model.class, Data.class, new MyModelLoaderFactory());
    }
}
