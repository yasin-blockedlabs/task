package com.example.task.repository.remote

import com.example.task.repository.database.entity.GotCharacter
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface RemoteService {

    @GET("api/v2/Characters")
    suspend fun getGotCharacters(): List<GotCharacter>

}