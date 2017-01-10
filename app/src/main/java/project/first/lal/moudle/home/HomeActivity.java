package project.first.lal.moudle.home;

import android.content.Context;
import android.first.lal.R;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import project.first.lal.common.base.BaseActivity;
import project.first.lal.common.http.HttpInterface;
import project.first.lal.common.utils.HotDecoration;
import project.first.lal.common.utils.banner.BannerView;
import project.first.lal.moudle.banner.BannerDateHandel;
import project.first.lal.moudle.banner.BannerModel;
import project.first.lal.moudle.hot.HotAdapter;
import project.first.lal.moudle.hot.HotDateHandle;
import project.first.lal.moudle.hot.HotModel;

/**
 * user:zhuwt
 *
 * @Description: ${todo}(这里用一句话描述这个类的作用)
 * @ClassName: ${type_name}.java
 * 2017/1/9
 * @说明 代码版权归 作者 所有
 */
public class HomeActivity extends BaseActivity {
    @BindView(R.id.home_banner)
    BannerView mHomeBanner;
    @BindView(R.id.radio_home)
    RadioButton mRadioHome;
    @BindView(R.id.radio_subsuribe)
    RadioButton mRadioSubsuribe;
    @BindView(R.id.radio_group)
    RadioGroup mRadioGroup;
    @BindView(R.id.home_recycle)
    RecyclerView mHomeRecycle;

    private HotAdapter mAdapter;
    private BannerAdapter mBannerAdapter;
    private Context mContext;

    @Override
    protected void onCreate() {
        mContext = this;
        //获取热门数据
        HotDateHandle.getInstance().hotDate(new HttpInterface<HotModel>() {
            @Override
            public void onNext(ArrayList<HotModel> data) {
                if (null != data && data.size() > 0) {
                    mAdapter = new HotAdapter(data, mContext);
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
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

            }
        });
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
}
