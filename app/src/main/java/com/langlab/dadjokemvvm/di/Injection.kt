package com.langlab.dadjokemvvm.di

import com.langlab.dadjokemvvm.data.ApiClient
import com.langlab.dadjokemvvm.data.JokeRemoteDataSource
import com.langlab.dadjokemvvm.model.JokeDataSource
import com.langlab.dadjokemvvm.model.JokeRepository
import com.langlab.dadjokemvvm.viewmodel.ViewModelFactory

object Injection {

    private var jokeDataSource: JokeDataSource? = null
    private var jokeRepository: JokeRepository? = null
    private var jokeViewModelFactory: ViewModelFactory? = null

    private fun createJokeDataSource(): JokeDataSource {
        val dataSource = JokeRemoteDataSource(ApiClient)
        jokeDataSource = dataSource
        return dataSource
    }

    private fun createJokeRepository(): JokeRepository {
        val repository = JokeRepository(provideDataSource())
        jokeRepository = repository
        return repository
    }

    private fun createFactory(): ViewModelFactory {
        val factory = ViewModelFactory(provideRepository())
        jokeViewModelFactory = factory
        return factory
    }

    private fun provideDataSource() = jokeDataSource ?: createJokeDataSource()
    private fun provideRepository() = jokeRepository ?: createJokeRepository()

    fun provideViewModelFactory() = jokeViewModelFactory ?: createFactory()
}