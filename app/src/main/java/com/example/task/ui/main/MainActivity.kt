package com.example.task.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import com.example.task.databinding.ActivityMainBinding
import com.example.task.repository.DataState
import com.example.task.repository.database.entity.GotCharacter
import com.example.task.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), CharacterAdapter.Action {

    private var loadedFromDb = false
    private var loaded = false
    private var adapter: CharacterAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = CharacterAdapter(this)
        binding.recyclerView.adapter = adapter!!
    }

    override fun configureBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun configureViewModel(): MainViewModel = viewModels<MainViewModel>().value

    override fun subscribeEvents() {

        connectionLiveData.observe(this){
            if (!loaded) {
                if (it) {
                    viewModel.sendUiEvent(MainViewModel.MainEvent.LoadCharactersApi)
                } else {
                    loadedFromDb = true
                    viewModel.sendUiEvent(MainViewModel.MainEvent.LoadCharactersData)
                }
            }
        }

        viewModel.dataState.observe(this){
            when(it){
                is DataState.Loading -> binding.textView.text = "Loading!"
                is DataState.Error -> binding.textView.text = "Error!"
                is DataState.Success -> {
                    loaded = !it.data.isEmpty() && !loadedFromDb
                    if (it.data.isEmpty())
                        binding.textView.text = if (loadedFromDb) "Check your network!" else "No Data!"
                    else {
                        binding.textView.text = ""
                        adapter?.notifyDataSetChanged()
                    }
                }
            }
        }

    }

    override fun getGotCharacters(): List<GotCharacter>? = viewModel.gotCharacterList.value
}