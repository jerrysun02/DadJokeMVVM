package com.langlab.dadjokemvvm.model

import android.content.Context
import com.langlab.dadjokemvvm.data.remote.ApiClient
import com.langlab.dadjokemvvm.data.OperationResult
import com.langlab.dadjokemvvm.data.db.JokeDTO
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
    }

    override suspend fun readJokes(context: Context): List<Joke> {
        val jokeDbDataSource = JokeDbDataSource(context)
        return jokeDbDataSource.jokes().map {
            Joke(it.title, it.content)
        }
    }

    override suspend fun saveJokes(jokes: List<Joke>, context: Context) {
        val jokeDTO = JokeDTO(jokes[0].title!!, jokes[0].content)
        val jokeDTOList = mutableListOf<JokeDTO>()
        jokeDTOList.add(jokeDTO)

        val jokeDbDataSource = JokeDbDataSource(context)
        jokeDbDataSource.addJokes(jokeDTOList)
    }
}