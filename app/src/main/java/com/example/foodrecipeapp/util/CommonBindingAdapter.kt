package com.example.foodrecipeapp.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.example.foodrecipeapp.R

@BindingAdapter("app:loadImage")
fun loadImage(imageView: ImageView?, url: String?){
    imageView?.load(url){
        crossfade(true)
        placeholder(R.drawable.paceholder)
        error(R.drawable.image_not_found)
    }
}