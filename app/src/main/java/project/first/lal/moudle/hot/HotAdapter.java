package project.first.lal.moudle.hot;

import android.content.Context;
import android.first.lal.R;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;

import java.util.ArrayList;

import project.first.lal.common.utils.ThumbnailView;
import project.first.lal.common.utils.recycle.RecycleInterface;
import project.first.lal.moudle.home.HomeActivity;

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
    private RecycleInterface click;

    public HotAdapter(ArrayList<HotModel> date, HomeActivity activity) {
        this.mList = date;
        this.mContext = activity;
        this.click = activity;
    }

    @Override
    public HotHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.hot_item, parent, false);
        return new HotHolder(view, click);
    }

    @Override
    public void onBindViewHolder(final HotHolder holder, int position) {
        HotModel hotModel = mList.get(position);
        Glide.with(mContext).load(hotModel.getLink()).into(new ViewTarget<ThumbnailView,GlideDrawable>(holder.hotLink) {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                resource.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                this.view.setImageDrawable(resource);
            }
        });
        holder.hotDescribe.setText(hotModel.getDescribe());
        holder.hotName.setText(hotModel.getTitle());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class HotHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView hotName;
        private TextView hotDescribe;
        private ThumbnailView hotLink;

        private RecycleInterface mInterface;

        public HotHolder(View itemView, RecycleInterface recycleInterface) {
            super(itemView);
            this.mInterface = recycleInterface;
            hotName = (TextView) itemView.findViewById(R.id.hot_title);
            hotDescribe = (TextView) itemView.findViewById(R.id.hot_describe);
            hotLink = (ThumbnailView) itemView.findViewById(R.id.hot_link);
            hotLink.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (null != mInterface)
                mInterface.onItemClick(v, getAdapterPosition());
        }
    }
}
