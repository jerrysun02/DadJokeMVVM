package com.langlab.dadjokemvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.langlab.dadjokemvvm.R
import com.langlab.dadjokemvvm.model.Joke
import com.langlab.dadjokemvvm.viewmodel.JokeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val jokeViewModel: JokeViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: JokeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()
        setupUI()
    }

    private fun setupUI() {
        recyclerView = findViewById(R.id.recyclerView)
        adapter = JokeAdapter(jokeViewModel.jokes.value ?: emptyList())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun setupViewModel() {
        jokeViewModel.jokes.observe(this, renderJokes)
    }

    private val renderJokes = Observer<List<Joke>> {
        Log.d("ddd","renderJokes")
        adapter.update(it)
    }

    override fun onResume() {
        super.onResume()
        jokeViewModel.loadJokes()
    }
}
