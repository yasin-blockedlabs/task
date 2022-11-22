package com.example.task.ui.base

import androidx.lifecycle.ViewModel
import com.example.task.repository.main.MainRepository
import javax.inject.Inject

abstract class BaseViewModel: ViewModel() {

    @Inject
    protected lateinit var main: MainRepository

    abstract fun sendUiEvent(uiEvent: UiEvent)
}