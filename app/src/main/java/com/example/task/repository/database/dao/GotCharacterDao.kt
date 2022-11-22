package com.example.task.repository.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.task.repository.database.entity.GotCharacter

@Dao
interface GotCharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(gotCharacters: List<GotCharacter>)

    @Insert
    suspend fun insert(gotCharacter: GotCharacter)

    @Update
    suspend fun update(gotCharacter: GotCharacter)

    @Delete
    suspend fun delete(gotCharacter: GotCharacter)

    @Query("delete from got_character")
    suspend fun deleteAll()

    @Query("select * from got_character where id = :id")
    suspend fun get(id: Int): GotCharacter?

    @Query("select * from got_character order by id")
    suspend fun getAll(): List<GotCharacter>

}