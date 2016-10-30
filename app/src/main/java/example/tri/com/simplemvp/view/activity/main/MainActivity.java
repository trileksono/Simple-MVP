package example.tri.com.simplemvp.view.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import example.tri.com.simplemvp.R;
import example.tri.com.simplemvp.base.BaseRecycle;
import example.tri.com.simplemvp.base.BaseActivity;
import example.tri.com.simplemvp.base.BaseView;
import example.tri.com.simplemvp.model.DataSearch;
import example.tri.com.simplemvp.view.activity.one.OneBrandActivity;
import timber.log.Timber;

public class MainActivity extends BaseActivity implements BaseView<List<DataSearch>> {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.recycle_view)
    RecyclerView mRecycleView;
    @Bind(R.id.loading)
    ProgressBar mLoading;

    private MainRecycle mAdapter;

    @Override
    protected int getResourceLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onViewReady(Bundle savedInstance) {
        mToolbar.setTitle("Example");
        setupAdapter();
        setupRecycle();
        new MainPresenterImpl(this).requestAllBrand();
    }

    private void setupAdapter() {
        mAdapter = new MainRecycle(this);
        mAdapter.setOnItemClickListener(new BaseRecycle.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                List<DataSearch> item = mAdapter.getData();
                Timber.d("click" + item.get(position));
                Intent i = new Intent(MainActivity.this,OneBrandActivity.class);
                i.putExtra("slug",item.get(position).getSlug());
                startActivity(i);
            }
        });

        mAdapter.setOnLongItemClickListener(new BaseRecycle.OnLongItemclickListener() {
            @Override
            public void onItemLongClick(View v, int position) {
                List item = mAdapter.getData();
                Timber.d("Long click" + item.get(position));
            }
        });
    }

    private void setupRecycle() {
        mRecycleView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(llm);
        mRecycleView.setAdapter(mAdapter);
    }

    @Override
    public void onShowLoading() {
        mLoading.setVisibility(View.VISIBLE);
        mRecycleView.setVisibility(View.GONE);
    }

    @Override
    public void onDismisLoading() {
        mLoading.setVisibility(View.GONE);
        mRecycleView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccess(List<DataSearch> listData) {
        mAdapter.add(listData);
    }

    @Override
    public void onError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}
