package com.example.task.repository

import android.provider.ContactsContract

sealed class DataState<out T> {
    object Loading: DataState<Nothing>()
    data class Success<out T>(val data: T): DataState<T>()
    data class Fail<out T>(val data: T): DataState<T>()
    object Error: DataState<Nothing>()
}