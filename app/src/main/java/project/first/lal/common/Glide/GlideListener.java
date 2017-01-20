package project.first.lal.common.Glide;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import project.first.lal.common.utils.ScreenUtils;

/**
 * user:zhuwt
 *
 * @Description: ${todo}(这里用一句话描述这个类的作用)
 * @ClassName: ${type_name}.java
 * 2017/1/12
 * @说明 代码版权归 作者 所有
 */
public class GlideListener implements RequestListener<String, GlideDrawable> {

    private ImageView gifView;
    private int screenWidth;
    private int screenHeight;

    public GlideListener(ImageView imageView, Context context) {
        this.gifView = imageView;
        screenWidth = ScreenUtils.getScreenWidth(context);
        screenHeight = ScreenUtils.getScreenHeight(context);
    }

    @Override
    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
        return false;
    }

    @Override
    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
        int resourceH = resource.getIntrinsicHeight();
        int resourceW = resource.getIntrinsicWidth();
        ViewGroup.LayoutParams layoutParams = gifView.getLayoutParams();
        if (resourceH > screenHeight)
            layoutParams.height = resourceH;
//                            holder.showImg.setMinimumHeight(resource.getIntrinsicHeight());
        else
            layoutParams.height = (int) (resourceH * ((float) screenWidth / resourceW));
//                            holder.showImg.setMinimumHeight((int) (resourceH * ((float) width / resourceW)));
//        Log.e("showAdapter", "width=" + resourceW + "\n"
//                + "heigth=" + resourceH + "\n"
//                + "w=" + gifView.getLayoutParams().width + "\n"
//                + "h=" + gifView.getLayoutParams().height);
        gifView.setLayoutParams(layoutParams);
        return false;
    }
}
