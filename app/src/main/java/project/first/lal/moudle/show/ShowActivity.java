package project.first.lal.moudle.show;

import android.content.Context;
import android.content.Intent;
import android.first.lal.R;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import project.first.lal.common.Constants;
import project.first.lal.common.base.BaseActivity;
import project.first.lal.common.http.HttpInterface;
import project.first.lal.common.utils.recycle.LoadMoreInterface;
import project.first.lal.common.utils.recycle.RecycleInterface;
import project.first.lal.common.utils.statusBar.StatusBarUtil;
import project.first.lal.moudle.album.AlbumActivity;
import project.first.lal.moudle.hot.HotModel;

/**
 * user:zhuwt
 *
 * @Description: ${todo}(这里用一句话描述这个类的作用)
 * @ClassName: ${type_name}.java
 * 2017/1/11
 * @说明 代码版权归 作者 所有
 */
public class ShowActivity extends BaseActivity implements RecycleInterface, LoadMoreInterface {
    @BindView(R.id.show_recycle)
    RecyclerView mShowRecycle;
    @BindView(R.id.show_toolbar)
    Toolbar mShowToolbar;

    private final String TAG = "ShowActivity";

    private ShowAdapter mShowAdapter;
    private ArrayList<AlbumModel> list = new ArrayList<>();

    private Context mContext;
    private int index = 0;

    @Override
    protected void onCreate() {
        mContext = this;
        HotModel model = (HotModel) getIntent().getSerializableExtra("hot");
        mShowToolbar.setTitle(model.getTitle());
        mShowToolbar.setTitleTextColor(ContextCompat.getColor(mContext, R.color.white));
        setSupportActionBar(mShowToolbar);
        HashMap<String, String> params = new HashMap<>();
        params.put("startPage", index + "");
        params.put("theme", "1");
        params.put("pageSize", Constants.PAGE_SIZE);
        ShowDataHandle.getInstance().hotDate(new HttpInterface<AlbumModel>() {
            @Override
            public void onNext(ArrayList<AlbumModel> data) {
                if (data != null && data.size() > 0) {
                    list = data;
                    mShowAdapter = new ShowAdapter(data, ShowActivity.this);
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
        mShowRecycle.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (RecyclerView.SCROLL_STATE_SETTLING == newState ||
                        RecyclerView.SCROLL_STATE_DRAGGING == newState) {
                    Glide.with(mContext).pauseRequests();
                } else {
                    Glide.with(mContext).resumeRequests();
                }
            }
        });
    }

    @Override
    protected void setStatusBar() {
        StatusBarUtil.setColorNoTranslucent(this, ContextCompat.getColor(this, R.color.show_Primary));
    }

    @Override
    public void onItemClick(View viewId, int position) {
        AlbumModel model = list.get(position);
        Intent mIntent = new Intent(ShowActivity.this, AlbumActivity.class);
        mIntent.putExtra("album", model);
        startActivity(mIntent);
    }


    @Override
    public void loadMore(boolean loadMore) {
        if (loadMore) {
            //释放viewType 否则会复用 不会执行onCreateViewHolder方法
            RecyclerView.RecycledViewPool viewPool = mShowRecycle.getRecycledViewPool();
            //clear方法没用 还是会复用 并且效率不高 每次将foot重新缓存
            viewPool.setMaxRecycledViews(1, 0);
            index++;
            Log.e(TAG, "index=" + index);
            HashMap<String, String> params = new HashMap<>();
            params.put("startPage", index + "");
            params.put("theme", "1");
            params.put("pageSize", Constants.PAGE_SIZE);
            ShowDataHandle.getInstance().hotDate(new HttpInterface<AlbumModel>() {
                @Override
                public void onNext(ArrayList<AlbumModel> data) {
                    if (data != null && data.size() > 0) {
                        list.addAll(data);
                        mShowAdapter.setData(data);
                    }
                }

                @Override
                public void onFail(String code, String msg) {

                }
            }, params);
        }
    }

}
