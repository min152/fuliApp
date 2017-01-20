package project.first.lal.moudle.home;

import android.content.Context;
import android.content.Intent;
import android.first.lal.R;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import project.first.lal.common.Constants;
import project.first.lal.common.base.BaseActivity;
import project.first.lal.common.http.HttpInterface;
import project.first.lal.common.utils.HotDecoration;
import project.first.lal.common.utils.banner.BannerView;
import project.first.lal.common.utils.recycle.RecycleInterface;
import project.first.lal.common.utils.statusBar.StatusBarUtil;
import project.first.lal.moudle.banner.BannerDateHandel;
import project.first.lal.moudle.banner.BannerModel;
import project.first.lal.moudle.fanhao.FanhaoActivity;
import project.first.lal.moudle.hot.HotAdapter;
import project.first.lal.moudle.hot.HotDateHandle;
import project.first.lal.moudle.hot.HotModel;
import project.first.lal.moudle.show.ShowActivity;

/**
 * user:zhuwt
 *
 * @Description: ${todo}(这里用一句话描述这个类的作用)
 * @ClassName: ${type_name}.java
 * 2017/1/9
 * @说明 代码版权归 作者 所有
 */
public class HomeActivity extends BaseActivity implements RecycleInterface {
    @BindView(R.id.home_banner)
    BannerView mHomeBanner;
    @BindView(R.id.home_recycle)
    RecyclerView mHomeRecycle;
    //    @BindView(R.id.home_toolbar)
//    Toolbar mHomeToolbar;
    @BindView(R.id.home_collbar)
    CollapsingToolbarLayout mHomeCollbar;
    @BindView(R.id.home_appbar)
    AppBarLayout mHomeAppbar;

    private HotAdapter mAdapter;
    private BannerAdapter mBannerAdapter;
    private Context mContext;
    private ArrayList<HotModel> mList;

    @Override
    protected void onCreate() {
        mContext = this;
        //获取热门数据
        HotDateHandle.getInstance().hotDate(new HttpInterface<HotModel>() {
            @Override
            public void onNext(ArrayList<HotModel> data) {
                if (null != data && data.size() > 0) {
                    mList = data;
                    mAdapter = new HotAdapter(data, HomeActivity.this);
                    mHomeRecycle.setLayoutManager(new LinearLayoutManager(mContext));
                    mHomeRecycle.addItemDecoration(new HotDecoration(mContext, HotDecoration.HORIZONTAL_LIST));
                    mHomeRecycle.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFail(String code, String msg) {

            }
        });
        //获取banner数据
        BannerDateHandel.getInstance().banner(new HttpInterface<BannerModel>() {
            @Override
            public void onNext(ArrayList<BannerModel> data) {
                if (null != data && data.size() > 0) {
                    mBannerAdapter = new BannerAdapter(data, mContext);
                    mHomeBanner.setAdapter(mBannerAdapter);
                }
            }

            @Override
            public void onFail(String code, String msg) {

            }
        });
    }

    @Override
    protected Integer setLayout() {
        return R.layout.home_layout;
    }

    @Override
    protected void initClick() {
    }

    @Override
    public void onItemClick(int viewId, int position) {
        HotModel model = mList.get(position);
        if (1 == model.getHotId()) {
            Intent mIntent = new Intent(mContext, ShowActivity.class);
            mIntent.putExtra("hot", model);
            startActivity(mIntent);
        } else if (2 == model.getHotId()) {
            Intent mIntent = new Intent(mContext, FanhaoActivity.class);
            mIntent.putExtra("fanhao", model);
            startActivity(mIntent);
        }

    }

    private class BannerAdapter implements BannerView.Adapter {

        private Context mContext;
        private ArrayList<BannerModel> list;

        public BannerAdapter(ArrayList<BannerModel> data, Context context) {
            this.mContext = context;
            this.list = data;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public View getView(int position) {
            ImageView item = new ImageView(mContext);
            item.setLayoutParams(new ViewGroup.LayoutParams(ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.MATCH_PARENT));
            item.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(mContext).load(list.get(position).getLink()).into(item);
            return item;
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }

    @Override
    protected void setStatusBar() {
        StatusBarUtil.setTranslucentForCoordinatorLayout(this, Constants.TRANTSPARENT);
    }
}
