package com.example.zozamax_app.adapters

import android.widget.ImageView
import com.bumptech.glide.Glide
import androidx.databinding.BindingAdapter
import com.example.zozamax_app.R

// this is for binding the poster image to the trailer url
@BindingAdapter("posterPath binding")
fun bindImage(imageView: ImageView, posterPath: String?) {
    val fullPosterPath = posterPath?.let { "https://image.tmdb.org/t/p/w500$it" }
    Glide.with(imageView.context)
        .load(fullPosterPath)
        .placeholder(R.drawable.ic_placeholder_image)
        .error(R.drawable.ic_placeholder_image)
        .into(imageView)
}


@BindingAdapter("favoriteIcon bidning")
fun setFavoriteIcon(view: ImageView, isFavorite: Boolean) {
    val drawable = if (isFavorite) {
        R.drawable.ic_favorite
    } else {
        R.drawable.ic_favorite_border
    }
    view.setImageResource(drawable)
}