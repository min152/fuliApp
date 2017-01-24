package project.first.lal.moudle.album;

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
import project.first.lal.common.utils.recycle.RecycleInterface;
import project.first.lal.moudle.show.AlbumModel;

/**
 * user:zhuwt
 *
 * @Description: ${todo}(这里用一句话描述这个类的作用)
 * @ClassName: ${type_name}.java
 * 2017/1/19
 * @说明 代码版权归 作者 所有
 */
public class AlbumAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<AlbumModel> mList;
    private RecycleInterface mInterface;
    private Context mContext;
    private String describe;
    //头部状态
    private final int HEADER_VIEW = 12;

    public AlbumAdapter(String str, ArrayList<AlbumModel> data, AlbumActivity albumActivity) {
        this.mList = data;
        mInterface = albumActivity;
        mContext = albumActivity;
        this.describe = str;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewGroup = null;
        if (viewType == HEADER_VIEW) {
            viewGroup = LayoutInflater.from(mContext).inflate(R.layout.album_item_header, parent, false);
            return new AlbumHeadHolder(viewGroup, mInterface);
        } else {
            viewGroup = LayoutInflater.from(mContext).inflate(R.layout.album_item, parent, false);
            return new AlbumHolder(viewGroup, mInterface);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof AlbumHeadHolder) {
            //头部介绍
            ((AlbumHeadHolder) holder).describe.setText(describe);
        } else if (holder instanceof AlbumHolder) {
            AlbumModel model = mList.get(position);
            Glide.with(mContext).load(model.getLink())
                    .skipMemoryCache(true)
                    .listener(new GlideListener(((AlbumHolder) holder).photo, mContext))
                    .into(new GlideDrawableImageViewTarget(((AlbumHolder) holder).photo));
        }
    }

    @Override
    public int getItemCount() {
        if (null != mList && mList.size() > 0)
            return mList.size() - 1;
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return HEADER_VIEW;
        return super.getItemViewType(position);
    }

    static class AlbumHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView photo;
        private RecycleInterface mRecycleInterface;

        public AlbumHolder(View itemView, RecycleInterface recycleInterface) {
            super(itemView);
            mRecycleInterface = recycleInterface;
            photo = (ImageView) itemView.findViewById(R.id.album_item_img);
            photo.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (null != mRecycleInterface)
                mRecycleInterface.onItemClick(v, getAdapterPosition());
        }
    }

    static class AlbumHeadHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView describe;
        private RecycleInterface mRecycleInterface;

        public AlbumHeadHolder(View itemView, RecycleInterface recycleInterface) {
            super(itemView);
            mRecycleInterface = recycleInterface;
            describe = (TextView) itemView.findViewById(R.id.album_header);
            describe.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (null != mRecycleInterface)
                mRecycleInterface.onItemClick(v, getAdapterPosition());
        }
    }

}
