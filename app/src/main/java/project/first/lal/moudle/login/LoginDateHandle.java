package project.first.lal.moudle.login;

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
 * 2017/1/4
 * @说明 代码版权归 作者 所有
 */
public class LoginDateHandle extends BaseDateHandle{

    public static LoginDateHandle mLoginDateHandle;

    public static LoginDateHandle getInstance() {
        synchronized (LoginDateHandle.class) {
            if (null == mLoginDateHandle) {
                mLoginDateHandle = new LoginDateHandle();
            }
        }
        return mLoginDateHandle;
    }

    /**
     * 登录
     *
     * @param map
     */
    public void login(HttpInterface httpInterface, HashMap<String, String> map) {
        LoginService loginService = HttpUtils.getInstance().getHttpRx(LoginService.class);
        loginService.login(md5Params(map))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(handleDate(httpInterface));
    }


}
