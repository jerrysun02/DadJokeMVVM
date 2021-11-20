package com.langlab.dadjokemvvm.data.db

interface DbDataSource {
    fun jokes(): List<JokeDTO>
    suspend fun addJokes(jokes: List<JokeDTO>)
}