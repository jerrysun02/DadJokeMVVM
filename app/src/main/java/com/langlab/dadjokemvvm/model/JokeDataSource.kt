package com.langlab.dadjokemvvm.model

import com.langlab.dadjokemvvm.data.OperationCallback

interface JokeDataSource {
    fun retrieveJokes(callback: OperationCallback<Joke>)
    fun cancel()
}