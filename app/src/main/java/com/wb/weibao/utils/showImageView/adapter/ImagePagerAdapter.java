package com.wb.weibao.utils.showImageView.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.wb.weibao.R;
import com.wb.weibao.common.MyApplication;
import com.wb.weibao.ui.home.MaxPictureActivity;
import com.wb.weibao.utils.showImageView.controls.PhotoView;
import com.wb.weibao.utils.showImageView.controls.TransferImage;
import java.util.ArrayList;


/**
 * Created by Kate on 2016/9/6.
 */
public class ImagePagerAdapter extends PagerAdapter {
    ArrayList<View> viewContainter = new ArrayList<View>();
    ArrayList<String> imageList = new ArrayList<String>();
    Context context;

    public ImagePagerAdapter(ArrayList<View> viewArrayList, Context context, ArrayList<String> imgList) {
        super();
        this.viewContainter = viewArrayList;
        this.context = context;
        this.imageList = imgList;
    }

    @Override
    public int getCount() {
        return viewContainter.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position,
                            Object object) {
        container.removeView(viewContainter.get(position));
    }

    public static Bitmap convertViewToBitmap(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();

        return bitmap;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewContainter.get(position % viewContainter.size()), 0);
        final PhotoView imageView = (PhotoView) ((MaxPictureActivity) context).findViewById(R.id.imageView);
        final ProgressBar progressBar = (ProgressBar) ((MaxPictureActivity) context).findViewById(R.id.progressBar2);
        imageView.setClickable(true);

        GlideUrl glideUrl = new GlideUrl(imageList.get(position), new LazyHeaders.Builder()
                .addHeader("Cookie", "JSESSIONID=" + MyApplication.getInstance().getJSESSIONID())
                .build());
        Glide.with(context).load(glideUrl).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL).thumbnail(0.1f).error(R.drawable.image_error).into(
                new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
                        imageView.setImageBitmap(bitmap);
//                        imageView.imageBitmap(bitmap);
                        imageView.enable();
                        progressBar.setVisibility(View.GONE);
                        imageView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mOnItemDeleteListener.onDeleteClick(v);
                            }
                        });
                        imageView.setOnLongClickListener(new View.OnLongClickListener() {
                            @Override
                            public boolean onLongClick(View v) {
                                mOnItemlongListener.onlongClick(v);
                                return true;
                            }
                        });
                    }
                }
        );
        return viewContainter.get(position % viewContainter.size());
    }


    /**
     *  取消按钮的监听接口
     */
    public interface onItemDeleteListener {
        void onDeleteClick(View view);
    }

    private onItemDeleteListener mOnItemDeleteListener;

    public void setOnItemDeleteClickListener(onItemDeleteListener mOnItemDeleteListener) {
        this.mOnItemDeleteListener = mOnItemDeleteListener;
    }



    /**
     *  取消按钮的监听接口
     */
    public interface onItemlongListener {
        void onlongClick(View view);
    }

    private onItemlongListener mOnItemlongListener;

    public void setOnItemlongClickListener(onItemlongListener mOnItemlongListener) {
        this.mOnItemlongListener = mOnItemlongListener;
    }


    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }
}