package project.first.lal.common.utils.recycle;

import android.content.Context;
import android.first.lal.R;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;

import project.first.lal.common.base.BaseActivity;

/**
 * user:zhuwt
 *
 * @Description: ${todo}(这里用一句话描述这个类的作用)
 * @ClassName: ${type_name}.java
 * 2017/1/19
 * @说明 代码版权归 作者 所有
 */
public abstract class FootHolderAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {

    ArrayList<? extends Object> mList;

    BaseActivity mActivity;

    int layoutId;

    final int FOOT_INDEX = 1;
    final int ITEM_INDEX = 2;

    public void setList(ArrayList<? extends Object> list) {
        mList = list;
    }

    public void setBaseActivity(BaseActivity activity) {
        mActivity = activity;
    }

    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }


    @Override
    public T onCreateViewHolder(ViewGroup parent, int viewType) {
        //判断是否为foot
        ViewGroup viewGroup = null;
        if (viewType == FOOT_INDEX) {
            onLoadMore(true);
            viewGroup = (ViewGroup) LayoutInflater.from((Context) mActivity).inflate(R.layout.recycle_foot_view, parent, false);
            return (T) new FootViewHolder(viewGroup);
        } else {
            onLoadMore(false);
            viewGroup = (ViewGroup) LayoutInflater.from((Context) mActivity).inflate(layoutId, parent, false);
            return createViewHolder(viewGroup);
        }
    }

    public abstract T createViewHolder(ViewGroup parent);

    public abstract void onLoadMore(boolean loadMore);

    @Override
    public void onBindViewHolder(T holder, int position) {
        if (holder instanceof FootViewHolder) {
        } else if (holder instanceof RecyclerView.ViewHolder) {
            onBindHolder(holder, position);
        }
    }

    public abstract void onBindHolder(T holder, int position);


    @Override
    public int getItemViewType(int position) {
        //不足一页时  不加载数据
        if ((position + 1 == getItemCount()) && getItemCount() > 20) {
            return FOOT_INDEX;
        } else
            return ITEM_INDEX;
    }

    @Override
    public int getItemCount() {
        return mList.size() + 1;
    }

    static class FootViewHolder extends RecyclerView.ViewHolder {

        private ProgressBar mProgressWheel;

        public FootViewHolder(View itemView) {
            super(itemView);
            mProgressWheel = (ProgressBar) itemView.findViewById(R.id.progress_wheel);
        }
    }
}
