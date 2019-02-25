package com.wb.weibao.utils.Timeline;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


/**
 * Created by akshay.kale on 22/08/2017.
 */

public class ImageLoad implements ImageLoadingEngine {

    Context context;

    public ImageLoad(Context context) {
        this.context = context;
    }

    @Override
    public void onLoadImage(ImageView imageView, String uri) {


        Glide.with(context).load(uri).into(imageView);
    }
}
