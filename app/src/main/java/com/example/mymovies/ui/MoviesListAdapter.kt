package com.example.mymovies.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymovies.R
import data.api.POSTER_BASE_URL
import data.model.Movie


class MoviesListAdapter(val context: Context): RecyclerView.Adapter<MoviesListAdapter.ViewHolder>() {

    private val allMovies = ArrayList<Movie>()

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val posterImageView: ImageView = itemView.findViewById(R.id.cv_iv_movie_poster)
        val titleTextView: TextView = itemView.findViewById(R.id.cv_movie_title)
        val releaseDateTextView: TextView = itemView.findViewById(R.id.cv_movie_release_date)
    }

    fun updateList(newList: List<Movie>) {
        allMovies.clear()
        allMovies.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.movie_list_item, parent, false)

        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return allMovies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleTextView.text = allMovies[position].title
        holder.releaseDateTextView.text = allMovies[position].releaseDate

        val moviePosterURL = POSTER_BASE_URL + allMovies[position].posterPath
        Glide.with(holder.itemView.context)
            .load(moviePosterURL)
            .into(holder.posterImageView)
    }
}