package com.example.senya.utils

import android.widget.ImageView
import coil.load
import coil.transform.CircleCropTransformation
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

fun ImageView.loadByCoil(imageUrl: String, placeholder: Int = R.drawable.ic_flight_24) {

    this.load(imageUrl){
        crossfade(true)
        placeholder(placeholder)
        transformations(CircleCropTransformation())
    }

}