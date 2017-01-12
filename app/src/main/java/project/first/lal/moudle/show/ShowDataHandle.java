package project.first.lal.moudle.show;

import java.util.HashMap;

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
 * 2017/1/12
 * @说明 代码版权归 作者 所有
 */
public class ShowDataHandle extends BaseDateHandle<PictureModel> {
    public static ShowDataHandle mShowDataHandle;

    public static ShowDataHandle getInstance() {
        synchronized (ShowDataHandle.class) {
            if (null == mShowDataHandle) {
                mShowDataHandle = new ShowDataHandle();
            }
        }
        return mShowDataHandle;
    }

    public void hotDate(HttpInterface<PictureModel> httpInterface, HashMap<String,String> params) {
        ShowService showService = HttpUtils.getInstance().getHttpRx(ShowService.class);
        showService.pictureDate(md5Params())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(handleDate(httpInterface));
    }
}
