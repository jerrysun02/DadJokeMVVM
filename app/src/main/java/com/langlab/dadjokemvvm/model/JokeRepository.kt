package com.langlab.dadjokemvvm.model

import com.langlab.dadjokemvvm.data.ApiClient
import com.langlab.dadjokemvvm.data.OperationResult
import java.lang.Exception

class JokeRepository:JokeDataSource {

    override suspend fun retrieveJokes(): OperationResult<Joke> {
        try {
            val response = ApiClient.build()?.jokes()
            response?.let {
                val jokeResponse = it.body()
                val joke = Joke(jokeResponse?.id, jokeResponse?.joke)
                return OperationResult.Success(joke)
            }?:run {
                return OperationResult.Success(Joke("1111", "00"))
            }
        } catch (e:Exception) {
            return OperationResult.Success(Joke("2222", "00"))
        }
        return OperationResult.Success(Joke("3333", "00"))
    }
}