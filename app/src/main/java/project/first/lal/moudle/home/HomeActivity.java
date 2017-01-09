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

import java.util.ArrayList;

import butterknife.BindView;
import project.first.lal.common.base.BaseActivity;
import project.first.lal.common.utils.HotDecoration;
import project.first.lal.common.utils.banner.BannerView;
import project.first.lal.moudle.hot.HotAdapter;
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

    private int[] banners = {
            R.mipmap.img1,
            R.mipmap.img2,
            R.mipmap.img3,
            R.mipmap.img4,
            R.mipmap.img5
    };

    private ArrayList<HotModel> list = new ArrayList<>();
    private HotAdapter mAdapter;

    private Context mContext;

    @Override
    protected void onCreate() {
        mContext = this;
        for (int i = 0; i < 5; i++) {
            HotModel model = new HotModel();
            model.setTitle("维多利亚的秘密");
            model.setDescribe("最性感的天使都在这里");
            model.setLink("http://imgsrc.baidu.com/forum/pic/item/2fed159b033b5bb5f3dce6a234d3d539b700bcb8.jpg");
            model.setTotal("1024");
            list.add(model);
        }
        mAdapter = new HotAdapter(list,mContext);
        mHomeRecycle.setLayoutManager(new LinearLayoutManager(this));
        mHomeRecycle.addItemDecoration(new HotDecoration(mContext,HotDecoration.HORIZONTAL_LIST));
        mHomeRecycle.setAdapter(mAdapter);
        mHomeBanner.setAdapter(new BannerView.Adapter() {
            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public View getView(int position) {
                ImageView item = new ImageView(HomeActivity.this);
                item.setLayoutParams(new ViewGroup.LayoutParams(ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.MATCH_PARENT));
                item.setImageResource(banners[position]);
                item.setScaleType(ImageView.ScaleType.CENTER_CROP);
                return item;
            }

            @Override
            public int getCount() {
                return banners.length;
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
}
