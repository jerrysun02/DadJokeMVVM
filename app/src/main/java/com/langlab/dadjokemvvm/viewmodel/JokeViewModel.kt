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

            if (result is OperationResult.Success) {
                val jokeList = mutableListOf<Joke>()
                jokeList.add(result.data)
                repository.saveJokes(jokeList, context)
            }

            _jokes.value = withContext(Dispatchers.IO) {
                repository.readJokes(context)
            }
        }
    }
}