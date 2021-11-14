package com.langlab.dadjokemvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.langlab.dadjokemvvm.databinding.ActivityMainBinding
import com.langlab.dadjokemvvm.model.Joke
import com.langlab.dadjokemvvm.viewmodel.JokeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val jokeViewModel: JokeViewModel by viewModels()
    private lateinit var adapter: JokeAdapter
    private lateinit var binding: ActivityMainBinding
    private var jokeList = mutableListOf<Joke>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupUI()
    }

    private fun setupUI() {
        adapter = JokeAdapter(jokeViewModel.jokes.value ?: emptyList())
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        binding.swipeRefreshLayout.setOnRefreshListener {
            jokeViewModel.loadJokes()
        }
    }

    private fun setupViewModel() {
        jokeViewModel.jokes.observe(this, renderJokes)
    }

    private val renderJokes = Observer<List<Joke>> {
        jokeList.addAll(it)
        jokeList.reverse()
        adapter.update(jokeList)
        binding.swipeRefreshLayout.isRefreshing = false
    }

    override fun onResume() {
        super.onResume()
        jokeViewModel.loadJokes()
    }
}