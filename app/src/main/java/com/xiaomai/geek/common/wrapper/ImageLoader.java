
package com.xiaomai.geek.common.wrapper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;

/**
 * Created by XiaoMai on 2017/3/9 16:01. 图片加载库封装 封装一个ImageLoader工具类来对外提供接口加载图片
 */

public class ImageLoader {

    public static void load(Context context, ImageView imageView, Object source, OnCustomImageCallback customImageCallback) {
        RequestBuilder<Drawable> builder = Glide.with(context)
                .load(source);
        if (customImageCallback != null) {
            customImageCallback.customImage(builder);
        }
        builder.into(imageView);
    }

    public static void load(Context context, ImageView imageView, Object source) {
        load(context, imageView, source, null);
    }

    public static void load(Object source, ImageView imageView) {
        load(imageView.getContext(), imageView, source);
    }

    public interface OnCustomImageCallback {
        /**
         * 自定义配置
         *
         * @param request
         */
        void customImage(RequestBuilder<Drawable> request);
    }
}
