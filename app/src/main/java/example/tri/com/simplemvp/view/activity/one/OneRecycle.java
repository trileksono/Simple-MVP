package example.tri.com.simplemvp.view.activity.one;

import android.content.Context;
import android.view.ViewGroup;

import example.tri.com.simplemvp.R;
import example.tri.com.simplemvp.base.BaseRecycle;
import example.tri.com.simplemvp.model.DataSearch;

/**
 * Created by tri on 10/23/16.
 */

public class OneRecycle extends BaseRecycle<DataSearch, OneVHolder>{

    public OneRecycle(Context mContext) {
        super(mContext);
    }

    @Override
    protected int getItemResourceLayout(int viewType) {
        return R.layout.content_one_brand;
    }

    @Override
    public OneVHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OneVHolder(getView(parent,viewType),mContext,onLongItemclickListener,onItemClickListener);
    }
}
