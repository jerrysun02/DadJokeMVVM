package com.langlab.dadjokemvvm.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.langlab.dadjokemvvm.data.OperationCallback
import com.langlab.dadjokemvvm.model.Joke
import com.langlab.dadjokemvvm.model.JokeRepository

class JokeViewModel(private val repository: JokeRepository) : ViewModel() {

    private val _jokes = MutableLiveData<List<Joke>>()//.apply { value = emptyList()}
    val jokes: LiveData<List<Joke>> = _jokes


    fun loadJokes() {
        repository.fetchJokes(object : OperationCallback<Joke> {
            override fun onSuccess(id: String?, joke: String?, status: String?) {
                val joke = Joke(id, joke)
                val jokeList = mutableListOf<Joke>()
                jokeList.add(joke)
                _jokes.value = jokeList
            }

            override fun onError(error: String?) {

            }
        })
    }
}