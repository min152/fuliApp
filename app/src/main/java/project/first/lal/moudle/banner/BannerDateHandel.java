package project.first.lal.moudle.banner;

import project.first.lal.common.base.BaseDateHandle;
import project.first.lal.common.http.HttpInterface;
import project.first.lal.common.http.HttpUtils;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * user:zhuwt
 *
 * @Description: ${todo}(这里用一句话描述这个类的作用)
 * @ClassName: ${type_name}.java
 * 2017/1/9
 * @说明 代码版权归 作者 所有
 */
public class BannerDateHandel extends BaseDateHandle<BannerModel> {

    public static BannerDateHandel mBannerDateHandel;

    public static BannerDateHandel getInstance() {
        synchronized (BannerDateHandel.class) {
            if (null == mBannerDateHandel) {
                mBannerDateHandel = new BannerDateHandel();
            }
        }
        return mBannerDateHandel;
    }

    public void banner(HttpInterface<BannerModel> httpInterface) {
        BannerService bannerService = HttpUtils.getInstance().getHttpRx(BannerService.class);
        bannerService.banner(md5Params())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(handleDate(httpInterface));
    }

}
