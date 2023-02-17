package com.example.senya.utils

import android.widget.ImageView
import com.example.senya.R
import com.squareup.picasso.Picasso

fun ImageView.loadImageByPicasso(
    url: String?,
    placeholder: Int,
    error: Int = R.drawable.ic_broken_image_24
) {

//    Picasso.get().isLoggingEnabled = true
    Picasso.get()
        .load(url)
        .placeholder(placeholder)
        .error(error)
        .into(this)

}