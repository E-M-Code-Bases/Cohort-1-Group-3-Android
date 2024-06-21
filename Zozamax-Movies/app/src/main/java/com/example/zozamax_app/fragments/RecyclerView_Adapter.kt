package com.example.zozamax_app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zozamax_app.api_services.MoviesAdapter
import com.example.zozamax_app.data.Movie
import com.example.zozamax_app.databinding.ItemMovieBinding

class MoviesAdapter(private val movies: List<Movie>) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    private val sortedMovies = movies.sortedByDescending { it.popularity }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(sortedMovies[position])
    }

    override fun getItemCount() = sortedMovies.size

    class MovieViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.movie = movie
            binding.executePendingBindings() // This will force the binding to execute immediately
        }
    }
}
