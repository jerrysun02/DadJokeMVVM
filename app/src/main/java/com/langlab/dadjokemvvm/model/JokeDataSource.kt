package com.langlab.dadjokemvvm.model

import android.content.Context
import com.langlab.dadjokemvvm.data.OperationResult

interface JokeDataSource {
    suspend fun retrieveJokes(): OperationResult<Joke>
    suspend fun saveJokes(jokes: List<Joke>, context: Context)
    suspend fun readJokes(context: Context): List<Joke>
}