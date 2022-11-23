package com.example.task.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.task.R
import com.example.task.databinding.ViewGotCharacterBinding
import com.example.task.repository.database.entity.GotCharacter

class CharacterAdapter(): RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    var gotCharacterList: List<GotCharacter> = listOf()

    fun updateGotCharacterList(gotCharacterList: List<GotCharacter>?){
        gotCharacterList?.let {
            println("Adapter Data Updated: ${it.size}")
            this.gotCharacterList = gotCharacterList
            notifyDataSetChanged()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder
        = CharacterViewHolder(DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
        R.layout.view_got_character, parent, false
        ))

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.update(gotCharacterList?.get(position))
    }

    override fun getItemCount(): Int = gotCharacterList?.size ?: 0

    inner class CharacterViewHolder(val binding: ViewGotCharacterBinding):
        RecyclerView.ViewHolder(binding.root){
        fun update(gotCharacter: GotCharacter?){
            binding.character = gotCharacter
            binding.executePendingBindings()
        }
    }
}