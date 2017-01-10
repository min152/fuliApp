package project.first.lal.moudle.hot;

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
 * 2017/1/10
 * @说明 代码版权归 作者 所有
 */
public class HotDateHandle extends BaseDateHandle<HotModel> {


    public static HotDateHandle mHotDateHandle;

    public static HotDateHandle getInstance() {
        synchronized (HotDateHandle.class) {
            if (null == mHotDateHandle) {
                mHotDateHandle = new HotDateHandle();
            }
        }
        return mHotDateHandle;
    }

    public void hotDate(HttpInterface<HotModel> httpInterface) {
        HotService bannerService = HttpUtils.getInstance().getHttpRx(HotService.class);
        bannerService.hotDate(md5Params())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(handleDate(httpInterface));
    }
}
