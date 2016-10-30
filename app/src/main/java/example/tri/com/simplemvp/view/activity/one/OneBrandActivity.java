package example.tri.com.simplemvp.view.activity.one;

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
import example.tri.com.simplemvp.base.BaseActivity;
import example.tri.com.simplemvp.base.BaseRecycle;
import example.tri.com.simplemvp.model.DataSearch;
import example.tri.com.simplemvp.util.ScrollRecycle;
import example.tri.com.simplemvp.view.activity.detail.DetailActivity;
import example.tri.com.simplemvp.base.BaseView;

/**
 * Created by tri on 10/23/16.
 */

public class OneBrandActivity extends BaseActivity implements BaseView<List<DataSearch>> {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.recycle_brand)
    RecyclerView mRecycleBrand;
    @Bind(R.id.progress)
    ProgressBar mProgress;

    private OneRecycle mAdapter;
    private String slug;
    private OnePresenterImpl mOnePresenter;


    @Override
    protected int getResourceLayout() {
        return R.layout.activity_one_brand;
    }

    @Override
    protected void onViewReady(Bundle savedInstance) {
        slug = getIntent().getStringExtra("slug");
        mToolbar.setTitle(slug);
        setupAdapter();
        setupRecycle();
        mOnePresenter = new OnePresenterImpl(this);
        mOnePresenter.requestBrand(slug, 0);
    }

    void setupAdapter() {
        mAdapter = new OneRecycle(this);
        mAdapter.setOnItemClickListener(new BaseRecycle.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent i = new Intent(OneBrandActivity.this, DetailActivity.class);
                i.putExtra("slug", mAdapter.getData().get(position).getSlug());
                startActivity(i);
            }
        });
    }

    void setupRecycle() {
        mRecycleBrand.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        mRecycleBrand.setLayoutManager(llm);
        mRecycleBrand.setAdapter(mAdapter);
        mRecycleBrand.setOnScrollListener(new ScrollRecycle(llm) {
            @Override
            public void onLoadMore(int page) {
                if (page >= mOnePresenter.maxPage) {
                    Toast.makeText(OneBrandActivity.this, "Tidak ada lagi data", Toast.LENGTH_SHORT).show();
                    return;
                }
                mOnePresenter.requestBrand(slug, page);
            }
        });
    }

    @Override
    public void onShowLoading() {
        mProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDismisLoading() {
        mProgress.setVisibility(View.GONE);
    }

    @Override
    public void onSuccess(List<DataSearch> data) {
        mAdapter.add(data);
    }

    @Override
    public void onError(String error) {
        Toast.makeText(OneBrandActivity.this, "Error : " + error, Toast.LENGTH_SHORT).show();
    }
}
