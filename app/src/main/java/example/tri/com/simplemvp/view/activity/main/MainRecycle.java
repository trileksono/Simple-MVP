package example.tri.com.simplemvp.view.activity.main;

import android.content.Context;
import android.view.ViewGroup;

import example.tri.com.simplemvp.R;
import example.tri.com.simplemvp.base.BaseRecycle;
import example.tri.com.simplemvp.model.DataSearch;

/**
 * Created by TI04 on 10/20/2016.
 */

public class MainRecycle extends BaseRecycle<DataSearch, MainVHolder> {

    public MainRecycle(Context mContext) {
        super(mContext);
    }

    @Override
    protected int getItemResourceLayout(int viewType) {
        return R.layout.content_all_brand;
    }

    @Override
    public MainVHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainVHolder(getView(parent,viewType),mContext,onItemClickListener,onLongItemclickListener);
    }
}
