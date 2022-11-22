package com.example.task.repository.remote

import com.example.task.config.AppConfig
import com.example.task.repository.database.entity.GotCharacter
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RemoteRepository @Inject constructor() {

    var service: RemoteService

    init {
        println("Remote Initialized")

        val client = OkHttpClient.Builder()
        client.connectTimeout(30, TimeUnit.MILLISECONDS)
        client.readTimeout(10, TimeUnit.SECONDS)

        service = Retrofit.Builder().baseUrl(AppConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            //.client(client.build())
            .build().create(RemoteService::class.java)
    }

    suspend fun getGotCharacters(): List<GotCharacter> = service.getGotCharacters()

}