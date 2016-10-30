package example.tri.com.simplemvp.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TI04 on 10/20/2016.
 */

public abstract class BaseRecycle <Data, Holder extends BaseHolder> extends RecyclerView.Adapter<Holder>{

    protected Context mContext;
    protected List<Data> mData;
    protected OnItemClickListener onItemClickListener;
    protected OnLongItemclickListener onLongItemclickListener;

    public BaseRecycle(Context mContext) {
        this.mContext = mContext;
        mData = new ArrayList<>();
    }

    public BaseRecycle(Context mContext, List<Data> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    protected View getView(ViewGroup parent, int viewType){
        return LayoutInflater.from(mContext).inflate(getItemResourceLayout(viewType),parent,false);
    }

    protected abstract int getItemResourceLayout(int viewType);

    @Override
    public abstract Holder onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        try {
            return mData.size();
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public List<Data> getData(){
        return mData;
    }

    public void add(Data data){
        this.mData.add(data);
        notifyItemInserted(mData.size() - 1);
    }

    public void add(final List<Data> items) {
        for (int i = 0; i < items.size(); i++) {
            mData.add(items.get(i));
        }
        notifyDataSetChanged();
    }

    public void add(Data item, int position){
        mData.add(position,item);
        notifyItemInserted(position);
    }

    public void addOrUpdate(Data item) {
        int i = mData.indexOf(item);
        if (i >= 0) {
            mData.set(i, item);
            notifyItemChanged(i);
        } else {
            add(item);
        }
    }

    public void addOrUpdate(final List<Data> items) {
        final int size = items.size();
        for (int i = 0; i < size; i++) {
            Data item = items.get(i);
            int x = mData.indexOf(item);
            if (x >= 0) {
                mData.set(x, item);
            } else {
                add(item);
            }
        }
        notifyDataSetChanged();
    }

    public void remove(int position) {
        if (position >= 0 && position < mData.size()) {
            mData.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void remove(Data item) {
        int position = mData.indexOf(item);
        remove(position);
    }

    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Data> dataList){
        add(dataList);
    }

    public interface OnItemClickListener{
        void onItemClick(View v, int position);
    }

    public interface OnLongItemclickListener{
        void onItemLongClick(View v, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnLongItemClickListener(OnLongItemclickListener onLongItemClickListener){
        this.onLongItemclickListener = onLongItemClickListener;
    }
}
