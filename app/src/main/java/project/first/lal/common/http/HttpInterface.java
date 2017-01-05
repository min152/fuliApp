package project.first.lal.common.http;

import java.util.List;

/**
 * user:zhuwt
 *
 * @Description: ${todo}(这里用一句话描述这个类的作用)
 * @ClassName: ${type_name}.java
 * 2017/1/4
 * @说明 代码版权归 作者 所有
 */
public interface HttpInterface<T>{
    void onNext(List<T> data);

    void onFail(String code,String msg);
}
