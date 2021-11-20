package com.langlab.dadjokemvvm.data.db

import androidx.lifecycle.LiveData

interface DbDataSource {
    fun jokes(): List<JokeDTO>
    suspend fun addJokes(jokes: List<JokeDTO>)
}