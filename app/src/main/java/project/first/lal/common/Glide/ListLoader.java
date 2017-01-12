package project.first.lal.common.Glide;

import android.content.Context;

import com.bumptech.glide.load.model.stream.BaseGlideUrlLoader;

/**
 * user:zhuwt
 *
 * @Description: 根据Glide接口传进来的VIEW大小来确定请求的地址 后面实现
 * @ClassName: ${type_name}.java
 * 2017/1/11
 * @说明 代码版权归 作者 所有
 */
public class ListLoader extends BaseGlideUrlLoader<IDataModel> {

    public ListLoader(Context context) {
        super(context);
    }

    @Override
    protected String getUrl(IDataModel model, int width, int height) {
        return null;
    }

}
