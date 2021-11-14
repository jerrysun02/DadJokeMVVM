package com.langlab.dadjokemvvm.di

import com.langlab.dadjokemvvm.model.JokeDataSource
import com.langlab.dadjokemvvm.model.JokeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object MVVMModule {
    @Singleton
    @Provides
    fun providerRepository(): JokeDataSource {
        return JokeRepository()
    }
}