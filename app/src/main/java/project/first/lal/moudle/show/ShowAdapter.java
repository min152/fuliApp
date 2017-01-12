package project.first.lal.moudle.show;

import android.content.Context;
import android.first.lal.R;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import java.util.ArrayList;

import project.first.lal.common.Glide.GlideListener;

/**
 * user:zhuwt
 *
 * @Description: ${todo}(这里用一句话描述这个类的作用)
 * @ClassName: ${type_name}.java
 * 2017/1/11
 * @说明 代码版权归 作者 所有
 */
public class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.ShowHolder> {

    private ArrayList<PictureModel> list;
    private Context mContext;

    public ShowAdapter(ArrayList<PictureModel> data, Context context) {
        this.list = data;
        this.mContext = context;
    }

    @Override
    public ShowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShowHolder(LayoutInflater.from(mContext).inflate(R.layout.show_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final ShowHolder holder, int position) {
        PictureModel model = list.get(position);
        holder.title.setText(model.getTitle());
        //根据原图的大小来显示图片 width为屏幕的长度 height根据屏幕跟width的比例缩放
        Glide.with(mContext).load(model.getLink())
                .skipMemoryCache(true)
                .listener(new GlideListener(holder.showImg, mContext))
                .into(new GlideDrawableImageViewTarget(holder.showImg));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ShowHolder extends RecyclerView.ViewHolder {

        private ImageView showImg;

        private TextView title;

        public ShowHolder(View itemView) {
            super(itemView);
            showImg = (ImageView) itemView.findViewById(R.id.show_img);
            title = (TextView) itemView.findViewById(R.id.show_item_title);
        }
    }
}
