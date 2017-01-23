package project.first.lal.moudle.fanhao;

import android.content.Context;
import android.first.lal.R;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import project.first.lal.common.Constants;
import project.first.lal.common.base.BaseActivity;
import project.first.lal.common.http.HttpInterface;
import project.first.lal.common.utils.recycle.LoadMoreInterface;
import project.first.lal.moudle.hot.HotModel;

/**
 * user:zhuwt
 *
 * @Description: ${todo}(这里用一句话描述这个类的作用)
 * @ClassName: ${type_name}.java
 * 2017/1/20
 * @说明 代码版权归 作者 所有
 */
public class FanhaoActivity extends BaseActivity implements LoadMoreInterface {

    DrawerLayout mFanhaoDrawer;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fanhao_recycle)
    RecyclerView mFanhaoRecycle;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    private Context mContext;
    private int index = 0;

    private ArrayList<DesignationModel> list = new ArrayList<>();
    private FanhaoAdapter mAdapter;

    @Override
    protected void onCreate() {
        mContext = this;
        mFanhaoDrawer = (DrawerLayout) findViewById(R.id.fanhao_drawer);
        HotModel model = (HotModel) getIntent().getSerializableExtra("fanhao");
        String title = model.getTitle();
        mToolbar.setTitle(title);
        setSupportActionBar(mToolbar);
        mFanhaoRecycle.setLayoutManager(new LinearLayoutManager(mContext));
        HashMap<String, String> params = new HashMap<>();
        params.put("startPage", "" + index);
        params.put("pageSize", Constants.PAGE_SIZE);
        params.put("orderIndex", "release_time");
        FanhaoDataHandle.getInstance().fanhaoData(new HttpInterface<DesignationModel>() {
            @Override
            public void onNext(ArrayList<DesignationModel> data) {
                if (null != data && data.size() > 0) {
                    list = data;
                    mAdapter = new FanhaoAdapter(list, FanhaoActivity.this);
                    mFanhaoRecycle.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFail(String code, String msg) {

            }
        }, params);
    }

    @Override
    protected Integer setLayout() {
        return R.layout.fanhao_layout;
    }

    @Override
    protected void initClick() {
        mNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Log.e("xx", item.getTitle() + "");
                return true;
            }
        });
    }

    @Override
    public void loadMore(boolean loadMore) {
        if (loadMore) {
            index++;
            //释放viewType 否则会复用 不会执行onCreateViewHolder方法
            RecyclerView.RecycledViewPool viewPool = mFanhaoRecycle.getRecycledViewPool();
            //clear方法没用 还是会复用 并且效率不高 每次将foot重新缓存
            viewPool.setMaxRecycledViews(1, 0);
            HashMap<String, String> params = new HashMap<>();
            params.put("startPage", "" + index);
            params.put("pageSize", Constants.PAGE_SIZE);
            params.put("orderIndex", "release_time");
            FanhaoDataHandle.getInstance().fanhaoData(new HttpInterface<DesignationModel>() {
                @Override
                public void onNext(ArrayList<DesignationModel> data) {
                    if (null != data && data.size() > 0) {
                        list.addAll(data);
                        mAdapter.setData(data);
                    }
                }

                @Override
                public void onFail(String code, String msg) {

                }
            }, params);
        }
    }

    @Override
    protected void setStatusBar() {
    }
}
