package project.first.lal.common.base;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;

import project.first.lal.common.Constants;
import project.first.lal.common.http.HttpInterface;
import project.first.lal.common.utils.MD5Util;
import rx.Subscriber;

public class BaseDateHandle<T> {

    private final String TAG = "BaseDateHandle";

    protected Subscriber<ResultMode<T>> handleDate(final HttpInterface httpInterface) {
        return new Subscriber<ResultMode<T>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                httpInterface.onFail(Constants.FAIL_CODE, e.getMessage());
            }

            @Override
            public void onNext(ResultMode<T> tResultMode) {
                if (Constants.SUCCESS_CODE.equals(tResultMode.getCode())) {
                    Log.e(TAG, tResultMode.toString());
                    httpInterface.onNext((ArrayList<T>) tResultMode.getContent());
                } else {
                    httpInterface.onFail(tResultMode.getCode(), tResultMode.getMsg());
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

    //参数统一签名加密
    protected HashMap<String, String> md5Params() {
        HashMap<String, String> map = new HashMap<>();
        map.put("token", "4C3FA7F1F5BEFFD534BE99875DF42EDF");
        String sign = MD5Util.Md5(MD5Util.getUrlParamsByMap(map));
        map.put("fuli", sign);
        return map;
    }

    private <T> ArrayList<T> getDate(String jsonString, Class<T> cls) {
        ArrayList<T> list = new ArrayList<T>();
        try {
            Gson gson = new Gson();
            list = gson.fromJson(jsonString, new TypeToken<ArrayList<T>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
