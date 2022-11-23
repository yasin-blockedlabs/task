package com.example.task.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.task.repository.DataState
import com.example.task.repository.database.entity.GotCharacter
import com.example.task.ui.base.BaseViewModel
import com.example.task.ui.base.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): BaseViewModel() {

    val dataState: MutableLiveData<DataState<List<GotCharacter>>> = MutableLiveData()
    private val _gotCharacterList: MutableLiveData<List<GotCharacter>> = MutableLiveData()
    val gotCharacterList: LiveData<List<GotCharacter>> = _gotCharacterList

    override fun sendUiEvent(uiEvent: UiEvent) {
        viewModelScope.launch {
            when(uiEvent){
                is MainEvent.LoadCharactersApi -> {
                    main.getGotCharacters()
                        .onEach {
                            dataState.value = it
                            if (it is DataState.Success<List<GotCharacter>>)
                                it.data.let { data -> _gotCharacterList.value = data }
                        }.launchIn(viewModelScope)
                }

                is MainEvent.LoadCharactersData -> {
                    main.loadGotCharacters().onEach {
                        dataState.value = it
                        if (it is DataState.Success<List<GotCharacter>>)
                            it.data.let { data -> _gotCharacterList.value = data }
                    }.launchIn(viewModelScope)
                }
            }
        }
    }

    sealed class MainEvent: UiEvent{
        object LoadCharactersApi: MainEvent()
        object LoadCharactersData: MainEvent()
    }
}