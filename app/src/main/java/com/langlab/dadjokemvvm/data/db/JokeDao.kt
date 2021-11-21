package com.langlab.dadjokemvvm.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface JokeDao {
    @Query("SELECT * from tb_joke")
    fun jokes(): List<JokeDTO>

    @Insert(onConflict = REPLACE)
    suspend fun add(joke: List<JokeDTO>)
}