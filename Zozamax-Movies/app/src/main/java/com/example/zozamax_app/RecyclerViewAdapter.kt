// RecyclerViewAdapter.kt
package com.example.zozamax_app

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.zozamax_app.databinding.MyholderBinding
import com.example.zozamax_app.Data.models.Result
import com.example.zozamax_app.utilities.image_url

class RecyclerViewAdapter(
    private val movies: List<Result>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<RecyclerViewAdapter.MyHolder>() {

    interface OnItemClickListener {
        fun onItemClick(movie: Result)
    }

    inner class MyHolder(val binding: MyholderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(currentMovie: Result) {
            binding.MovieTitle.text = currentMovie.title
            Glide.with(binding.ImageLoad.context)
                .load(image_url + currentMovie.poster_path)
                .override(400, 400)
                .into(binding.ImageLoad)
            binding.releaseDate.text = currentMovie.release_date

            binding.root.setOnClickListener {
                listener.onItemClick(currentMovie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = MyholderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val currentMovie = movies[position]
        holder.bindData(currentMovie)
    }

    override fun getItemCount(): Int = movies.size
}
