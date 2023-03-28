package com.example.mymovies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovies.R

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MoviesListViewModel
    lateinit var moviesListRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        moviesListRecyclerView = findViewById(R.id.rv_movie_list)

        moviesListRecyclerView.layoutManager = GridLayoutManager(this, 3)
        moviesListRecyclerView.hasFixedSize()

        val movieRecyclerViewAdapter = MoviesListAdapter(this)
        moviesListRecyclerView.adapter = movieRecyclerViewAdapter

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[MoviesListViewModel::class.java]

        //viewModel.mockMovies.value?.let { movieRecyclerViewAdapter.updateList(it) }

        viewModel.movies.observe(this, Observer { list ->
            list?.let {
                movieRecyclerViewAdapter.updateList(it)
            }
        })
    }


}