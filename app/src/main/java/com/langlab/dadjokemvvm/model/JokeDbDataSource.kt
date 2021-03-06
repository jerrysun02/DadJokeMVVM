package com.langlab.dadjokemvvm.model

import android.content.Context
import com.langlab.dadjokemvvm.data.db.DbDataSource
import com.langlab.dadjokemvvm.data.db.JokeDTO
import com.langlab.dadjokemvvm.data.db.JokeDao
import com.langlab.dadjokemvvm.data.db.JokeDatabase

class JokeDbDataSource(context: Context): DbDataSource {
    private lateinit var jokeDao:JokeDao
    init {
        val db = JokeDatabase.getInstance(context)
        db?.let {
            jokeDao = it.jokeDao()
        }
    }

    override fun jokes(): List<JokeDTO> {
        return jokeDao.jokes()
    }

    override suspend fun addJokes(jokes: List<JokeDTO>) {
        return jokeDao.add(jokes)
    }
}