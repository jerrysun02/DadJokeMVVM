package com.langlab.dadjokemvvm.viewmodel

import android.content.Context
import androidx.lifecycle.*
import com.langlab.dadjokemvvm.data.OperationResult
import com.langlab.dadjokemvvm.model.Joke
import com.langlab.dadjokemvvm.model.JokeDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class JokeViewModel @Inject constructor (
    @ApplicationContext private val context: Context,
    private val repository: JokeDataSource
    ) : ViewModel() {

    private val _jokes = MutableLiveData<List<Joke>>().apply { value = emptyList()}
    val jokes: LiveData<List<Joke>> = _jokes

    fun loadJokes() {
        viewModelScope.launch {
            var result:OperationResult<Joke> = withContext(Dispatchers.IO) {
                repository.retrieveJokes()
            }

            if (result is OperationResult.Success) {
                repository.saveJokes(listOf(result.data), context)
            }

            _jokes.value = withContext(Dispatchers.IO) {
                repository.readJokes(context)
            }
        }
    }
}