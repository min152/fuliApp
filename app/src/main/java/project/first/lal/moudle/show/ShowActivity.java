package project.first.lal.moudle.show;

import android.content.Context;
import android.first.lal.R;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import project.first.lal.common.Constants;
import project.first.lal.common.base.BaseActivity;
import project.first.lal.common.http.HttpInterface;
import project.first.lal.common.utils.statusBar.StatusBarUtil;

/**
 * user:zhuwt
 *
 * @Description: ${todo}(这里用一句话描述这个类的作用)
 * @ClassName: ${type_name}.java
 * 2017/1/11
 * @说明 代码版权归 作者 所有
 */
public class ShowActivity extends BaseActivity {
    @BindView(R.id.show_collbar)
    CollapsingToolbarLayout mShowCollbar;
    @BindView(R.id.show_appbar)
    AppBarLayout mShowAppbar;
    @BindView(R.id.show_recycle)
    RecyclerView mShowRecycle;
    @BindView(R.id.show_toolbar)
    Toolbar mShowToolbar;
    @BindView(R.id.show_collbar_bg)
    ImageView mShowCollbarBg;

    private ShowAdapter mShowAdapter;
    private ArrayList<PictureModel> list = new ArrayList<>();

    private Context mContext;
    private int index = 0;

    @Override
    protected void onCreate() {
        mContext = this;
        setSupportActionBar(mShowToolbar);
        mShowCollbar.setTitle("维多利亚的秘密");
        mShowCollbar.setExpandedTitleColor(ContextCompat.getColor(mContext,R.color.title_bg));
        HashMap<String, String> params = new HashMap<>();
        params.put("startPage", index + "");
        params.put("pageSize", Constants.PAGE_SIZE);
        ShowDataHandle.getInstance().hotDate(new HttpInterface<PictureModel>() {
            @Override
            public void onNext(ArrayList<PictureModel> data) {
                if (data != null && data.size() > 0) {
                    mShowAdapter = new ShowAdapter(data, mContext);
                    mShowRecycle.setLayoutManager(new LinearLayoutManager(mContext));
                    mShowRecycle.setAdapter(mShowAdapter);
                }
            }

            @Override
            public void onFail(String code, String msg) {

            }
        }, params);
    }

    @Override
    protected Integer setLayout() {
        return R.layout.show_layout;
    }

    @Override
    protected void initClick() {

    }

    @Override
    protected void setStatusBar() {
        StatusBarUtil.setTranslucentForCoordinatorLayout(this, Constants.TRANTSPARENT);
    }
}
