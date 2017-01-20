package project.first.lal.moudle.album;

import android.content.Context;
import android.first.lal.R;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import project.first.lal.common.Constants;
import project.first.lal.common.base.BaseActivity;
import project.first.lal.common.http.HttpInterface;
import project.first.lal.common.utils.ThumbnailView;
import project.first.lal.common.utils.recycle.RecycleInterface;
import project.first.lal.common.utils.statusBar.StatusBarUtil;
import project.first.lal.moudle.show.AlbumModel;
import project.first.lal.moudle.show.ShowDataHandle;

/**
 * user:zhuwt
 *
 * @Description: ${todo}(这里用一句话描述这个类的作用)
 * @ClassName: ${type_name}.java
 * 2017/1/19
 * @说明 代码版权归 作者 所有
 */
public class AlbumActivity extends BaseActivity implements RecycleInterface {
    @BindView(R.id.album_toolbar)
    Toolbar mAlbumToolbar;
    @BindView(R.id.album_collbar)
    CollapsingToolbarLayout mAlbumCollbar;
    @BindView(R.id.album_appbar)
    AppBarLayout mAlbumAppbar;
    @BindView(R.id.album_bg)
    ThumbnailView mAlbumBg;
    @BindView(R.id.album_recycle)
    RecyclerView mAlbumRecycle;

    private ArrayList<AlbumModel> list = new ArrayList<>();
    private AlbumAdapter mAdapter;
    private String title;
    private String describe;
    private Context mContext;

    @Override
    protected void onCreate() {
        mContext = this;
        setSupportActionBar(mAlbumToolbar);
        final AlbumModel model = (AlbumModel) getIntent().getSerializableExtra("album");
        Glide.with(this).load(model.getLink()).into(new ViewTarget<ThumbnailView, GlideDrawable>(mAlbumBg) {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                resource.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                this.view.setImageDrawable(resource);
            }
        });
        title = model.getTitle();
        describe = model.getIntroduce();
        mAlbumCollbar.setTitle(title);
        HashMap<String, String> params = new HashMap<>();
        params.put("startPage", "0");
        params.put("title", title);
        params.put("pageSize", "50");
        ShowDataHandle.getInstance().hotDate(new HttpInterface<AlbumModel>() {
            @Override
            public void onNext(ArrayList<AlbumModel> data) {
                if (data != null && data.size() > 0) {
                    list = data;
                    mAdapter = new AlbumAdapter(describe, list, AlbumActivity.this);
                    mAlbumRecycle.setLayoutManager(new LinearLayoutManager(mContext));
                    mAlbumRecycle.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFail(String code, String msg) {

            }
        }, params);
    }

    @Override
    protected Integer setLayout() {
        return R.layout.album_layout;
    }

    @Override
    protected void initClick() {
        mAlbumRecycle.addOnScrollListener(new RecyclerView.OnScrollListener() {
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

        mAlbumToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(mContext).pauseRequests();
                finish();
            }
        });
    }

    @Override
    protected void setStatusBar() {
        StatusBarUtil.setTranslucentForCoordinatorLayout(this, Constants.TRANTSPARENT);
    }

    @Override
    public void onItemClick(int viewId, int position) {

    }
}
