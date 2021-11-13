package com.langlab.dadjokemvvm.model

import com.langlab.dadjokemvvm.data.OperationCallback

class JokeRepository(private val jokeDataSource: JokeDataSource) {

    fun fetchJokes(callback: OperationCallback<Joke>) {
        jokeDataSource.retrieveJokes(callback)
    }

    fun cancel() {
        jokeDataSource.cancel()
    }
}