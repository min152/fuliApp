package project.first.lal.moudle.register;

import java.util.HashMap;

import project.first.lal.common.base.BaseDateHandle;
import project.first.lal.common.http.HttpInterface;
import project.first.lal.common.http.HttpUtils;
import project.first.lal.moudle.UserInfo;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * user:zhuwt
 *
 * @Description: ${todo}(这里用一句话描述这个类的作用)
 * @ClassName: ${type_name}.java
 * 2017/1/5
 * @说明 代码版权归 作者 所有
 */
public class RegisterDateHandle extends BaseDateHandle<UserInfo> {

    public static RegisterDateHandle mRegisterDateHandle;

    public static RegisterDateHandle getInstance() {
        synchronized (RegisterDateHandle.class) {
            if (null == mRegisterDateHandle) {
                mRegisterDateHandle = new RegisterDateHandle();
            }
        }
        return mRegisterDateHandle;
    }

    /**
     * 注册
     *
     * @param httpInterface
     * @param map
     */
    public void register(HttpInterface<UserInfo> httpInterface, HashMap<String, String> map) {
        RegisterService registerService = HttpUtils.getInstance().getHttpRx(RegisterService.class);
        registerService.login(md5Params(map))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(handleDate(httpInterface));
    }


}
