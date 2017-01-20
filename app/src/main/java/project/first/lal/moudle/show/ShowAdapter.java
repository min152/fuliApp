package project.first.lal.moudle.show;

import android.content.Context;
import android.first.lal.R;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import java.util.ArrayList;

import project.first.lal.common.Glide.GlideListener;
import project.first.lal.common.utils.recycle.FootHolderAdapter;
import project.first.lal.common.utils.recycle.LoadMoreInterface;
import project.first.lal.common.utils.recycle.RecycleInterface;

/**
 * user:zhuwt
 *
 * @Description: ${todo}(这里用一句话描述这个类的作用)
 * @ClassName: ${type_name}.java
 * 2017/1/11
 * @说明 代码版权归 作者 所有
 */
public class ShowAdapter extends FootHolderAdapter<ShowAdapter.ShowHolder> {

    private ArrayList<AlbumModel> list;
    private Context mContext;
    private RecycleInterface mInterface;
    private LoadMoreInterface mLoadMoreInterface;

    public ShowAdapter(ArrayList<AlbumModel> data, ShowActivity showActivity) {
        setLayoutId(R.layout.show_item);
        setList(data);
        setBaseActivity(showActivity);
        this.list = data;
        this.mContext = showActivity;
        this.mInterface = showActivity;
        this.mLoadMoreInterface = showActivity;
    }

    //刷新数据 重新设置数据源
    void setData(ArrayList<AlbumModel> data) {
        list.addAll(data);
        setList(list);
        onLoadMore(false);
        notifyDataSetChanged();
    }


    @Override
    public ShowHolder createViewHolder(ViewGroup parent) {
        return new ShowHolder(parent, mInterface);
    }

    @Override
    public void onLoadMore(boolean loadMore) {
        mLoadMoreInterface.loadMore(loadMore);
    }

    @Override
    public void onBindHolder(ShowHolder holder, int position) {
        AlbumModel model = list.get(position);
        holder.title.setText(model.getTitle());
        //根据原图的大小来显示图片 width为屏幕的长度 height根据屏幕跟width的比例缩放
        Glide.with(mContext).load(model.getLink())
                .skipMemoryCache(true)
                .listener(new GlideListener(holder.showImg, mContext))
                .into(new GlideDrawableImageViewTarget(holder.showImg));
        holder.label.setText("写真");
        holder.describe.setText(model.getIntroduce());
    }

    static class ShowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView showImg;

        private TextView title;

        private TextView describe;

        private Button label;

        private CardView showItem;

        private RecycleInterface mInterface;

        public ShowHolder(View itemView, RecycleInterface recycleInterface) {
            super(itemView);
            showImg = (ImageView) itemView.findViewById(R.id.show_img);
            title = (TextView) itemView.findViewById(R.id.show_item_title);
            describe = (TextView) itemView.findViewById(R.id.show_item_describe);
            label = (Button) itemView.findViewById(R.id.show_item_label);
            showItem = (CardView) itemView.findViewById(R.id.show_item);
            showItem.setOnClickListener(this);
            this.mInterface = recycleInterface;
        }

        @Override
        public void onClick(View v) {
            if (null != mInterface) {
                mInterface.onItemClick(v.getId(), getAdapterPosition());
            }
        }
    }
}
