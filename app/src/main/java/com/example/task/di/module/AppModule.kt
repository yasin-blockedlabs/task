package com.example.task.di.module

import android.content.Context
import com.example.task.repository.database.DatabaseRepository
import com.example.task.repository.main.MainRepository
import com.example.task.repository.remote.RemoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideMainRepository(database: DatabaseRepository,
                              remote: RemoteRepository
    ): MainRepository =
        MainRepository(database, remote)

    @Provides
    @Singleton
    fun provideDatabaseRepository(@ApplicationContext context: Context): DatabaseRepository = DatabaseRepository(context)

    @Provides
    @Singleton
    fun provideRemoteRepository(@ApplicationContext context: Context): RemoteRepository = RemoteRepository()

}