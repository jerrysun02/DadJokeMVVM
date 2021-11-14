package com.langlab.dadjokemvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.langlab.dadjokemvvm.model.JokeDataSource

class ViewModelFactory(private val repository: JokeDataSource) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return JokeViewModel(repository) as T
    }
}