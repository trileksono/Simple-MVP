package example.tri.com.simplemvp.view.activity.detail;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.OnClick;
import example.tri.com.simplemvp.R;
import example.tri.com.simplemvp.base.BaseActivity;
import example.tri.com.simplemvp.model.DetailProduk;
import example.tri.com.simplemvp.util.ImgZoom;
import example.tri.com.simplemvp.base.BaseView;

/**
 * Created by tri on 10/29/16.
 */

public class DetailActivity extends BaseActivity implements BaseView<DetailProduk> {
    @Bind(R.id.img_hp)
    ImageView mImgHp;
    @Bind(R.id.merk_hp)
    TextView mMerkHp;
    @Bind(R.id.announced)
    TextInputEditText mAnnounced;
    @Bind(R.id.status)
    TextInputEditText mStatus;
    @Bind(R.id.technologi)
    TextInputEditText mTechnologi;
    @Bind(R.id.threeg_bands)
    TextInputEditText mThreegBands;
    @Bind(R.id.fourg_bands)
    TextInputEditText mFourgBands;
    @Bind(R.id.speed)
    TextInputEditText mSpeed;
    @Bind(R.id.gprs)
    TextInputEditText mGprs;
    @Bind(R.id.progress)
    ProgressBar mProgress;
    @Bind(R.id.root_layout)
    ScrollView mRootLayout;

    DetailPresenterImpl mPresenter;
    String imgPath;

    @Override
    protected int getResourceLayout() {
        return R.layout.activity_detail;
    }

    @Override
    protected void onViewReady(Bundle savedInstance) {
        mPresenter = new DetailPresenterImpl(this);
        mPresenter.getDetailSlug(getIntent().getStringExtra("slug"));
    }


    @Override
    public void onShowLoading() {
        mProgress.setVisibility(View.VISIBLE);
        mRootLayout.setVisibility(View.GONE);
    }

    @Override
    public void onDismisLoading() {
        mProgress.setVisibility(View.GONE);
        mRootLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccess(DetailProduk data) {
        mMerkHp.setText(data.getTitle());
        mAnnounced.setText(data.getData().getLaunch().getAnnounced());
        mStatus.setText(data.getData().getLaunch().getStatus());
        mTechnologi.setText(data.getData().getNetwork().getTechnology());
        mThreegBands.setText(data.getData().getNetwork().getValue3g_bands());
        mFourgBands.setText(data.getData().getNetwork().getValue4g_bands() == null ?
                data.getData().getNetwork().getValue2g_bands() : data.getData().getNetwork().getValue2g_bands());
        mSpeed.setText(data.getData().getNetwork().getSpeed());
        mGprs.setText(data.getData().getNetwork().getGprs());

        Picasso.with(this).load(data.getImg()).fit().centerCrop().into(mImgHp);
        imgPath = data.getImg();
    }

    @Override
    public void onError(String error) {
        Toast.makeText(this,error,Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.img_hp)
    void doZoomImg(){
        ImgZoom zoom = new ImgZoom(this,imgPath);
        zoom.setCanceledOnTouchOutside(true);
        zoom.show();
    }
}
