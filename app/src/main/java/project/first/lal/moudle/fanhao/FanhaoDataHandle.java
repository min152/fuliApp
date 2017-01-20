package project.first.lal.moudle.fanhao;

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
public class FanhaoDataHandle extends BaseDateHandle<DesignationModel> {
    public static FanhaoDataHandle mFanhaoDataHandle;

    public static FanhaoDataHandle getInstance() {
        synchronized (FanhaoDataHandle.class) {
            if (null == mFanhaoDataHandle) {
                mFanhaoDataHandle = new FanhaoDataHandle();
            }
        }
        return mFanhaoDataHandle;
    }

    public void fanhaoData(HttpInterface<DesignationModel> httpInterface, HashMap<String,String> params) {
        FanhaoService fanhaoService = HttpUtils.getInstance().getHttpRx(FanhaoService.class);
        fanhaoService.fanhaoData(md5Params(params))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(handleDate(httpInterface));
    }
}
