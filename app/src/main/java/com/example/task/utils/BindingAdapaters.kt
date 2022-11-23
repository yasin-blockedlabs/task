package com.example.task.utils

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.task.repository.database.entity.GotCharacter
import com.example.task.ui.main.CharacterAdapter
import kotlinx.android.synthetic.main.view_got_character.view.*

@BindingAdapter("setAdapter")
fun setAdapter(recyclerView: RecyclerView, adapter: CharacterAdapter?){
    adapter?.let { recyclerView.adapter = adapter }
}

@BindingAdapter("submitList")
fun submitList(recyclerView: RecyclerView, list: List<GotCharacter>?){
    println("Submit List Called! ${list?.size ?: "Null"}")
    val adapter = recyclerView.adapter as CharacterAdapter
    adapter.updateGotCharacterList(list)
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?){
    imgUrl?.let {
        val imageUri = it.toUri().buildUpon().scheme("https").build()
        imgView.load(imageUri)
    }
}