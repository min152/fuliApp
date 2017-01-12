package project.first.lal.moudle.hot;

import android.content.Context;
import android.first.lal.R;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * user:zhuwt
 *
 * @Description: ${todo}(这里用一句话描述这个类的作用)
 * @ClassName: ${type_name}.java
 * 2017/1/9
 * @说明 代码版权归 作者 所有
 */
public class HotAdapter extends RecyclerView.Adapter<HotAdapter.HotHolder> {

    private ArrayList<HotModel> mList;
    private Context mContext;

    public HotAdapter(ArrayList<HotModel> date, Context context) {
        this.mList = date;
        this.mContext = context;
    }

    @Override
    public HotHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.hot_item, parent, false);
        return new HotHolder(view);
    }

    @Override
    public void onBindViewHolder(HotHolder holder, int position) {
        HotModel hotModel = mList.get(position);
        Glide.with(mContext).load(hotModel.getLink()).bitmapTransform(new RoundedCornersTransformation(mContext,3,0)).into(holder.hotLink);
        holder.hotTotal.setText(hotModel.getTotal());
        holder.hotDescribe.setText(hotModel.getDescribe());
        holder.hotName.setText(hotModel.getTitle());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class HotHolder extends RecyclerView.ViewHolder {
        private TextView hotName;
        private TextView hotDescribe;
        private TextView hotTotal;
        private Button hotSubsrcibe;
        private ImageView hotLink;

        private RelativeLayout hotItem;

        public HotHolder(View itemView) {
            super(itemView);
            hotName = (TextView) itemView.findViewById(R.id.hot_title);
            hotDescribe = (TextView) itemView.findViewById(R.id.hot_describe);
            hotTotal = (TextView) itemView.findViewById(R.id.hot_total);
            hotSubsrcibe = (Button) itemView.findViewById(R.id.hot_subsrcibe);
            hotLink = (ImageView) itemView.findViewById(R.id.hot_link);
            hotItem = (RelativeLayout) itemView.findViewById(R.id.hot_item);
        }
    }
}
