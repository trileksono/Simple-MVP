package example.tri.com.simplemvp.view.activity.main;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import example.tri.com.simplemvp.R;
import example.tri.com.simplemvp.base.BaseHolder;
import example.tri.com.simplemvp.base.BaseRecycle;
import example.tri.com.simplemvp.model.DataSearch;

/**
 * Created by tri on 10/16/16.
 */

public class MainVHolder extends BaseHolder<DataSearch> {

    @Bind(R.id.tv_brand)
    TextView mTvBrand;
    @Bind(R.id.tv_brand_count)
    TextView mTvBrandCount;

    public MainVHolder(View itemView, Context mContext, BaseRecycle.OnItemClickListener onItemClickListener,
                       BaseRecycle.OnLongItemclickListener onLongItemclickListener) {
        super(itemView,mContext,onLongItemclickListener,onItemClickListener);
        this.mContext = mContext;
    }

    @Override
    public void bind(DataSearch dataSearch) {
        mTvBrand.setText(dataSearch.getSlug().toUpperCase());
        mTvBrandCount.setText(dataSearch.getCount());
    }
}
