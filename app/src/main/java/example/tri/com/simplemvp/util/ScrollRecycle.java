package example.tri.com.simplemvp.util;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by tri on 10/23/16.
 */

public abstract class ScrollRecycle extends RecyclerView.OnScrollListener {

    int page =0;
    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 3;
    private int firstVisibleItem;
    private int visibleItemCount;
    private int totalItemCount;
    LinearLayoutManager mLayoutManager;

    public ScrollRecycle(LinearLayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mLayoutManager.getItemCount();
        firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();

        if(loading && totalItemCount > previousTotal){
            loading = false;
            previousTotal = totalItemCount;
        }

        if(!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)){
            page++;
            onLoadMore(page);
            loading = true;
        }
    }

    public abstract void onLoadMore(int page);
}
