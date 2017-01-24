package project.first.lal.common.base;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

/**
 * user:zhuwt
 *
 * @Description: ${todo}(这里用一句话描述这个类的作用)
 * @ClassName: ${type_name}.java
 * 2017/1/9
 * @说明 代码版权归 作者 所有
 */
public class BaseApplication extends Application{

    public static Context mContext;

    public static Bitmap mBitmap;

    public static Bitmap getBitmap() {
        return mBitmap;
    }

    public static void setBitmap(Bitmap bitmap) {
        mBitmap = bitmap;
    }

    public static void clearBitmap() {
        if (null != mBitmap)
            mBitmap=null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext() {
        return mContext;
    }
}
