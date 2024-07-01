package com.example.zozamax_app

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zozamax_app.databinding.MyholderBinding
import com.example.zozamax_app.Data.models.Result

class RecyclerViewAdapter(val movies:List<Result>):RecyclerView.Adapter<RecyclerViewAdapter.MyHolder>() {
    class MyHolder(val binding:MyholderBinding):RecyclerView.ViewHolder(binding.root) {
        fun bindData(currentMovie: Result) {
            binding.title.text = currentMovie.title
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.MyHolder {
       val view=MyholderBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.MyHolder, position: Int) {
        val currentMovie = movies[position]
        holder.bindData(currentMovie)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

}