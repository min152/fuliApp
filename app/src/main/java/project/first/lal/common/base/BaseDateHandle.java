package project.first.lal.common.base;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;

import project.first.lal.common.Constants;
import project.first.lal.common.http.HttpInterface;
import project.first.lal.common.utils.MD5Util;
import rx.Subscriber;

public class BaseDateHandle{

    protected Subscriber<ResultMode> handleDate(final HttpInterface httpInterface) {
        return new Subscriber<ResultMode>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                httpInterface.onFail(Constants.FAIL_CODE, e.getMessage());
            }

            @Override
            public void onNext(ResultMode resultMode) {
                if (Constants.SUCCESS_CODE.equals(resultMode.getCode())) {
                    Gson gson = new Gson();
                    List<? extends Object> data = gson.fromJson(gson.toJson(resultMode.getContent()), new TypeToken<List<? extends Object>>(){}.getType());
                    httpInterface.onNext(data);
                } else {
                    httpInterface.onFail(resultMode.getCode(), resultMode.getMsg());
                }
            }
        };
    }

    //参数统一签名加密
    protected HashMap<String, String> md5Params(HashMap<String, String> map) {
        if (null == map && map.size() < 0)
            return null;
        map.put("token", "4C3FA7F1F5BEFFD534BE99875DF42EDF");
        String sign = MD5Util.Md5(MD5Util.getUrlParamsByMap(map));
        map.put("fuli", sign);
        return map;
    }
}
