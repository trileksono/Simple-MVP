package example.tri.com.simplemvp.view.activity.one;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import example.tri.com.simplemvp.R;
import example.tri.com.simplemvp.base.BaseHolder;
import example.tri.com.simplemvp.base.BaseRecycle;
import example.tri.com.simplemvp.model.DataSearch;

/**
 * Created by tri on 10/23/16.
 */

public class OneVHolder extends BaseHolder<DataSearch> {

    @Bind(R.id.img_hp)
    ImageView mImgHp;
    @Bind(R.id.merk_hp)
    TextView mMerkHp;

    public OneVHolder(View itemView, Context mContext, BaseRecycle.OnLongItemclickListener onLongItemclickListener, BaseRecycle.OnItemClickListener onItemClickListener) {
        super(itemView, mContext, onLongItemclickListener, onItemClickListener);
        this.mContext = mContext;
    }

    @Override
    public void bind(DataSearch dataSearch) {
        mMerkHp.setText(dataSearch.getTitle());
        Picasso.with(mContext).load(dataSearch.getImg()).fit().centerCrop().into(mImgHp);
    }
}
