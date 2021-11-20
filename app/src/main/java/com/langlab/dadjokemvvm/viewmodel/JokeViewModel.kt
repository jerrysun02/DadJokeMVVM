package com.langlab.dadjokemvvm.viewmodel

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.langlab.dadjokemvvm.data.OperationResult
import com.langlab.dadjokemvvm.model.Joke
import com.langlab.dadjokemvvm.model.JokeDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class JokeViewModel @ViewModelInject constructor (private val repository: JokeDataSource) : ViewModel() {

    private val _jokes = MutableLiveData<List<Joke>>().apply { value = emptyList()}
    val jokes: LiveData<List<Joke>> = _jokes

    fun loadJokes(context: Context) {
        viewModelScope.launch {
            var result:OperationResult<Joke> = withContext(Dispatchers.IO) {
                repository.retrieveJokes()
            }
            when(result) {
                is OperationResult.Success -> {
                    val jokeList = mutableListOf<Joke>()
                    jokeList.add(result.data)
                    _jokes.value = jokeList
                    repository.saveJokes(jokeList, context)
                    readDBJokes(context)
                }
            }
        }
    }

    private fun readDBJokes(context: Context) {
        viewModelScope.launch {
            var result:List<Joke> = withContext(Dispatchers.IO) {
                repository.readJokes(context)
            }
            when(result) {
                is List<Joke> -> {
                    _jokes.value = result
                }
            }
        }
    }
}