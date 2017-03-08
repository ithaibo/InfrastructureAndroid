package com.andy.infrastructure.demos.mvp;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Andy on 2017/3/8.
 */

public class ImageViewGlideBindAdapter {
    @BindingAdapter("android:src")
    public static void setSrc(ImageView imageView, Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }

    @BindingAdapter("android:src")
    public static void setSrc(ImageView imageView, int resId) {
        imageView.setImageResource(resId);
    }

    @BindingAdapter({"app:imageUrl"})
    public static void loadImage(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .into(imageView);
    }
}
