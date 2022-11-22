package com.example.task.repository.database

import android.content.Context
import com.example.task.repository.BaseRepository
import com.example.task.repository.database.entity.GotCharacter
import javax.inject.Inject

class DatabaseRepository @Inject constructor(context: Context): BaseRepository() {

    private lateinit var roomDb: RoomDb

    init {
        println("Database Initialized")
        roomDb = RoomDb.getInstance(context)
    }

    suspend fun updateGotCharacters(gotCharacters: List<GotCharacter>){
        roomDb.characterDao().insertAll(gotCharacters)
    }

    suspend fun getAllGotCharacters(): List<GotCharacter> = roomDb.characterDao().getAll()


}