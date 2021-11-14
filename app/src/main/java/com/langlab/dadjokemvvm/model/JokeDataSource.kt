package com.langlab.dadjokemvvm.model

import com.langlab.dadjokemvvm.data.OperationResult

interface JokeDataSource {
    suspend fun retrieveJokes(): OperationResult<Joke>
}