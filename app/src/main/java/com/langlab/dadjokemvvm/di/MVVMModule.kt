package com.langlab.dadjokemvvm.di

import com.langlab.dadjokemvvm.model.JokeDataSource
import com.langlab.dadjokemvvm.model.JokeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object MVVMModule {
    @Provides
    @ViewModelScoped
    fun providerRepository(): JokeDataSource {
        return JokeRepository()
    }
}