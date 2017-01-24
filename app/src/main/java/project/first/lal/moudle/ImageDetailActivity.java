package project.first.lal.moudle;

import android.first.lal.R;
import android.graphics.Bitmap;
import android.support.v4.app.ActivityCompat;

import project.first.lal.common.base.BaseActivity;
import project.first.lal.common.base.BaseApplication;

/**
 * user:zhuwt
 *
 * @Description: ${todo}(这里用一句话描述这个类的作用)
 * @ClassName: ${type_name}.java
 * 2017/1/23
 * @说明 代码版权归 作者 所有
 */
public class ImageDetailActivity extends BaseActivity {



    public static Bitmap mBitmap;

    public static Bitmap getBitmap() {
        return mBitmap;
    }

    public static void setBitmap(Bitmap bitmap) {
        mBitmap = bitmap;
    }

    @Override
    protected void onCreate() {
//        String link = getIntent().getStringExtra("link");
//        if (null != mBitmap)
//            Glide.with(this).load(mBitmap).into(mDetailImg);
//        else
//            Glide.with(this).load(link)
//                    .skipMemoryCache(true)
//                    .listener(new GlideListener(mDetailImg, this))
//                    .into(new GlideDrawableImageViewTarget(mDetailImg));
    }

    @Override
    protected Integer setLayout() {
//        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        return R.layout.image_detail_layout;
    }

    @Override
    protected void initClick() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityCompat.finishAfterTransition(this);
        BaseApplication.clearBitmap();
    }
}
