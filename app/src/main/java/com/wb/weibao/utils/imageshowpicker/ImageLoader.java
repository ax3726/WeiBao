package com.wb.weibao.utils.imageshowpicker;

import android.content.Context;
import android.widget.ImageView;

/**
 * Author 姚智胜
 * Version V1.0版本
 * Description: 加载图片方式
 * Date: 2017/4/6
 */
public abstract class ImageLoader implements ImageLoaderInterface<ImageView> {

    @Override
    public ImageView createImageView(Context context) {
        return new ImageView(context);
    }

}
