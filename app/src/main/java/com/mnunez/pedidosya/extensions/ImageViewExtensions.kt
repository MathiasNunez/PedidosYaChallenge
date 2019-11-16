package com.mnunez.pedidosya.extensions


import android.widget.ImageView
import com.mnunez.pedidosya.utils.RoundedCornersTransformation
import com.squareup.picasso.Picasso

fun ImageView.loadImage(imageUrl: String?) = Picasso.get().load(imageUrl).into(this)

fun ImageView.loadImageRoundedCorners(imageUrl: String) =
    Picasso.get().load(imageUrl).transform(RoundedCornersTransformation()).into(this)
