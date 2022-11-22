package com.example.task.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.task.R
import com.example.task.databinding.ViewGotCharacterBinding
import com.example.task.repository.database.entity.GotCharacter

class CharacterAdapter(val action: Action): RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder
        = CharacterViewHolder(DataBindingUtil.inflate<ViewGotCharacterBinding>(
            LayoutInflater.from(parent.context),
        R.layout.view_got_character, parent, false
        ))

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.update(action.getGotCharacters()?.get(position))
    }

    override fun getItemCount(): Int = action.getGotCharacters()?.size ?: 0

    inner class CharacterViewHolder(val binding: ViewGotCharacterBinding):
        RecyclerView.ViewHolder(binding.root){
        fun update(gotCharacter: GotCharacter?){
            binding.character = gotCharacter
        }
    }

    interface Action{
        fun getGotCharacters(): List<GotCharacter>?
    }
}