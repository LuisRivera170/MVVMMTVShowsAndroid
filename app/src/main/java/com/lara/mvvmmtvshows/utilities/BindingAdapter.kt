package com.lara.mvvmmtvshows.utilities

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception


@BindingAdapter("android:imageURL")
fun setImageUrl(imageView: ImageView, URL: String?) {
    imageView.alpha = 0f
    Picasso
        .get()
        .load(URL)
        .noFade()
        .into(imageView, object: Callback {
            override fun onSuccess() {
                imageView
                    .animate()
                    .setDuration(300)
                    .alpha(1f)
                    .start()
            }

            override fun onError(e: Exception?) {}

        })
}

