package example.tri.com.simplemvp.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by TI04 on 10/20/2016.
 */

public abstract class BaseHolder<Data> extends RecyclerView.ViewHolder
    implements View.OnClickListener, View.OnLongClickListener{

    protected Context mContext;
    private BaseRecycle.OnItemClickListener onItemClickListener;
    private BaseRecycle.OnLongItemclickListener onLongItemclickListener;

    public BaseHolder(View itemView, Context mContext, BaseRecycle.OnLongItemclickListener onLongItemclickListener, BaseRecycle.OnItemClickListener onItemClickListener) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        this.mContext = mContext;
        this.onItemClickListener = onItemClickListener;
        this.onLongItemclickListener = onLongItemclickListener;
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public abstract void bind(Data data);

    @Override
    public void onClick(View v) {
        onItemClickListener.onItemClick(v,getAdapterPosition());
    }

    @Override
    public boolean onLongClick(View v) {
        if(onLongItemclickListener  != null){
            onLongItemclickListener.onItemLongClick(v,getAdapterPosition());
            return true;
        }
        return false;
    }
}
