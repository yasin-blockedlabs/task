package com.example.task.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.task.repository.database.dao.GotCharacterDao
import com.example.task.repository.database.entity.GotCharacter

@Database(entities = [GotCharacter::class], version = 1)
abstract class RoomDb: RoomDatabase() {

    abstract fun characterDao(): GotCharacterDao

    companion object{

        @Volatile
        private var INSTANCE: RoomDb? = null

        @Synchronized
        fun getInstance(context: Context): RoomDb {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context, RoomDb::class.java, "room_db")
                        .fallbackToDestructiveMigration()
                        .addCallback(roomCallback)
                        .build()
                INSTANCE = instance
                instance
            }
        }

        private val roomCallback = object: Callback(){
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                println("Room Db Created")
            }
        }

    }
}