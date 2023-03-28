package com.example.mymovies.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import data.api.MovieDBClient
import data.api.MovieDBInterface
import data.model.Movie
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MoviesListViewModel(application: Application) : AndroidViewModel(application) {

    private val apiService : MovieDBInterface = MovieDBClient.getRetrofitClient()

//    private fun <T : Any?> MutableLiveData<T>.default(initialValue: T) = apply { setValue(initialValue) }
//    val mockMovies = MutableLiveData<List<Movie>>().default(listOf(Movie(1,"", "1995", "VladoS")))

    var movies = MutableLiveData<List<Movie>>()

    // Different pages can shown with more time for implementing.
    // Api service usage have to be in repository class.
    private fun updatePopularMovies() {
        viewModelScope.launch {
            movies.value = apiService.getPopularMovie(1).movieList
        }
    }


    init {
        updatePopularMovies()
    }

}