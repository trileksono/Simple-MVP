package example.tri.com.simplemvp.util;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import example.tri.com.simplemvp.R;
import timber.log.Timber;

/**
 * Created by tri on 10/29/16.
 */

public class ImgZoom extends Dialog {

    String path;
    Context mContext;

    public ImgZoom(Context context, String path) {
        super(context);
        this.path = path;
        this.mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.img_zoom);
        ImageView img = (ImageView) findViewById(R.id.img);
        Timber.d(path);
        Picasso.with(mContext).load(path).into(img);
    }
}