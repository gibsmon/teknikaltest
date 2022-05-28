package com.example.danstest.util

import android.graphics.drawable.Drawable
import android.os.Build
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.danstest.R
import com.squareup.picasso.Picasso


@BindingAdapter("setImage")
fun setImage(view:ImageView, url:String){
    val mDefaultBackground: Drawable =
        ContextCompat.getDrawable(view.context, R.drawable.ic_baseline_account_circle_24)!!;

    Picasso.get()
        .load(url)
        .placeholder(mDefaultBackground)
        .into(view);
}

@BindingAdapter("setHTML")
fun setHTML(view:TextView, text:String){
    view.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT)
    } else {
        Html.fromHtml(text)
    }
}