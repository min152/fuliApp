package project.first.lal.moudle.album;

import android.content.Context;
import android.first.lal.R;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

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
import project.first.lal.common.utils.StringUtils;
import project.first.lal.common.utils.ThumbnailView;
import project.first.lal.common.utils.photo.Info;
import project.first.lal.common.utils.photo.PhotoView;
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
    @BindView(R.id.album_parent)
    FrameLayout mAlbumParent;
    @BindView(R.id.album_photview)
    PhotoView mAlbumPhotview;

    private ArrayList<AlbumModel> list = new ArrayList<>();
    private AlbumAdapter mAdapter;
    private String title;
    private String describe;
    private Context mContext;

    private Info mInfo;

    @Override
    protected void onCreate() {
        mContext = this;
        setSupportActionBar(mAlbumToolbar);
        final AlbumModel model = (AlbumModel) getIntent().getSerializableExtra("album");
        title = model.getTitle();
        describe = model.getIntroduce();
        //获取数据
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
        //有部图片加上滤镜
        Glide.with(this).load(model.getLink()).into(new ViewTarget<ThumbnailView, GlideDrawable>(mAlbumBg) {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                resource.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                this.view.setImageDrawable(resource);
            }
        });
        mAlbumCollbar.setTitle(title);
        mAlbumPhotview.enable();
        mAlbumPhotview.setScaleType(ImageView.ScaleType.FIT_CENTER);
    }

    @Override
    protected Integer setLayout() {
//        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        return R.layout.album_layout;
    }

    @Override
    protected void initClick() {
        //图片缩放
        mAlbumPhotview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null == mInfo)
                    mAlbumParent.setVisibility(View.GONE);
                else
                    mAlbumPhotview.animaTo(mInfo, new Runnable() {
                        @Override
                        public void run() {
                            mAlbumParent.setVisibility(View.GONE);
                        }
                    });
            }
        });

        //recycleView滑动时 不加载
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
    public void onItemClick(View v, int position) {
        AlbumModel model = list.get(position);
        String link = model.getLink();
        if (!StringUtils.isEmpty(link)) {
            //当前是否已经缓存
            ImageView view = (ImageView) v;
            Drawable drawable = view.getDrawable();
            mAlbumParent.setVisibility(View.VISIBLE);
            if (null != drawable) {
                mInfo = PhotoView.getImageViewInfo(view);
                mAlbumPhotview.setImageDrawable(drawable);
                mAlbumPhotview.animaFrom(mInfo);
            } else {
                mAlbumPhotview.setImageDrawable(null);
                Glide.with(mContext).load(link).into(new ViewTarget<PhotoView, GlideDrawable>(mAlbumPhotview) {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        this.view.setImageDrawable(resource);
                    }
                });
            }
        } else {
            Toast.makeText(mContext, "图片地址失效", Toast.LENGTH_SHORT).show();
        }
        /**
         * 5.0系统适用的图片原地址打开方法 暂时不用
         if (!StringUtils.isEmpty(link)) {
         ImageView view = (ImageView) v;
         Intent intent = new Intent(AlbumActivity.this, ImageDetailActivity.class);
         intent.putExtra("link", link);
         Bitmap bitmap = ((GlideBitmapDrawable) view.getDrawable()).getBitmap();
         if (null != bitmap) {
         BaseApplication.setBitmap(bitmap);
         }
         //判断当前的系统版本 5.0以上使用makeSceneTransitionAnimation
         // 5.0一下的使用makeScaleUpAnimation
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
         //5.0以上系统
         try {
         //图片原位置放大
         ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, view, "image");
         ActivityCompat.startActivity(this, intent, options.toBundle());
         } catch (Exception e) {
         e.printStackTrace();
         return;
         }
         } else {
         ActivityOptionsCompat options =
         ActivityOptionsCompat.makeScaleUpAnimation(view,
         view.getWidth() / 2, view.getHeight() / 2, 0, 0);
         ActivityCompat.startActivity(this, intent, options.toBundle());
         }
         } else {

         }
         */
    }

    @Override
    public void onBackPressed() {
        if (mAlbumParent.getVisibility() == View.VISIBLE) {
            mAlbumPhotview.animaTo(mInfo, new Runnable() {
                @Override
                public void run() {
                    mAlbumParent.setVisibility(View.GONE);
                }
            });
        } else {
            super.onBackPressed();
        }
    }
}
