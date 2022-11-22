package com.example.task.utils

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import coil.load
import kotlinx.android.synthetic.main.view_got_character.view.*

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?){
    imgUrl?.let {
        val imageUri = it.toUri().buildUpon().scheme("https").build()
        imgView.load(imageUri)
    }
}