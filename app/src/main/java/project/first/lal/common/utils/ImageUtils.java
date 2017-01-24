package project.first.lal.common.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * user:zhuwt
 *
 * @Description: ${todo}(这里用一句话描述这个类的作用)
 * @ClassName: ${type_name}.java
 * 2017/1/23
 * @说明 代码版权归 作者 所有
 */
public class ImageUtils {

    public static Bitmap compress(int width,int height){
        // 设置参数
        BitmapFactory.Options options = new BitmapFactory.Options();
        int inSampleSize = 2; // 默认像素压缩比例，压缩为原图的1/2
        int minLen = Math.min(height, width); // 原图的最小边长
        if(minLen > 100) { // 如果原始图像的最小边长大于100dp（此处单位我认为是dp，而非px）
            float ratio = (float)minLen / 100.0f; // 计算像素压缩比例
            inSampleSize = (int)ratio;
        }
        options.inSampleSize = inSampleSize; // 设置为刚才计算的压缩比例
        return null;
    }
}
