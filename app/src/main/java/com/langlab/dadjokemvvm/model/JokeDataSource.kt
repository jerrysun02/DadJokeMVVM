package com.langlab.dadjokemvvm.model

import android.content.Context
import androidx.lifecycle.LiveData
import com.langlab.dadjokemvvm.data.OperationResult
import com.langlab.dadjokemvvm.data.db.JokeDTO

interface JokeDataSource {
    suspend fun retrieveJokes(): OperationResult<Joke>
    suspend fun saveJokes(jokes: List<Joke>, context: Context)
    suspend fun readJokes(context: Context): List<Joke>
}