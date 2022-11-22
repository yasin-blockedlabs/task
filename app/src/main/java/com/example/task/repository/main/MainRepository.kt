package com.example.task.repository.main

import com.example.task.repository.database.DatabaseRepository
import com.example.task.repository.remote.RemoteRepository
import com.example.task.repository.BaseRepository
import com.example.task.repository.DataState
import com.example.task.repository.database.entity.GotCharacter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepository @Inject constructor(
    val database: DatabaseRepository,
    val remote: RemoteRepository
): BaseRepository() {

    init {
        println("Main Initialized")
    }

    fun getGotCharacters(): Flow<DataState<List<GotCharacter>>> = flow {
        try {
            emit(DataState.Loading)
            val gotCharacterList = remote.getGotCharacters()
            database.updateGotCharacters(gotCharacterList)
        } catch (e: Exception) {
            emit(DataState.Error)
        }
        val gotCharactersDb = database.getAllGotCharacters()
        emit(DataState.Success(gotCharactersDb))
    }

    fun loadGotCharacters(): Flow<DataState<List<GotCharacter>>> = flow {
        emit(DataState.Loading)
        val gotCharacterList = database.getAllGotCharacters()
        emit(DataState.Success(gotCharacterList))
    }

}