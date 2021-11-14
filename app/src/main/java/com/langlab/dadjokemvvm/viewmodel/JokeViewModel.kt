package com.langlab.dadjokemvvm.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.langlab.dadjokemvvm.data.OperationResult
import com.langlab.dadjokemvvm.model.Joke
import com.langlab.dadjokemvvm.model.JokeDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class JokeViewModel @ViewModelInject constructor (private val repository: JokeDataSource) : ViewModel() {

    private val _jokes = MutableLiveData<List<Joke>>().apply { value = emptyList()}
    val jokes: LiveData<List<Joke>> = _jokes

    fun loadJokes() {
        viewModelScope.launch {
            var result:OperationResult<Joke> = withContext(Dispatchers.IO) {
                repository.retrieveJokes()
            }
            when(result) {
                is OperationResult.Success -> {
                    val jokeList = mutableListOf<Joke>()
                    jokeList.add(result.data)
                    _jokes.value = jokeList
                }
            }
        }
    }
}