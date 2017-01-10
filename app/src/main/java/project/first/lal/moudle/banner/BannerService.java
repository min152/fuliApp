package project.first.lal.moudle.banner;

import java.util.HashMap;

import project.first.lal.common.base.ResultMode;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * user:zhuwt
 *
 * @Description: ${todo}(这里用一句话描述这个类的作用)
 * @ClassName: ${type_name}.java
 * 2017/1/9
 * @说明 代码版权归 作者 所有
 */
public interface BannerService {

    @FormUrlEncoded
    @POST("banner/banner.action")
    Observable<ResultMode<BannerModel>> banner(@FieldMap HashMap<String, String> map);
}
