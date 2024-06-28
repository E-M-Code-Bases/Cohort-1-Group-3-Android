package com.example.zozamax_app.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.zozamax_app.R
import com.example.zozamax_app.data.Result
import com.example.zozamax_app.databinding.MovieRecBinding
import com.example.zozamax_app.databinding.MovieRightBinding
import com.example.zozamax_app.util.BASE_URL
import com.example.zozamax_app.util.IMAGE_URL
import com.squareup.picasso.Picasso;

private const val TAG = "movies"
class  MovieAdapter(private val movies:List<Result>,val cont: Context): RecyclerView.Adapter<MovieAdapter.MyHolder>() {
    private var layoutType: Int? = null

    inner class MyHolder(val binding: ViewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindData(model: Result) {
            when (binding) {
                is MovieRecBinding -> {
                    binding.title.text = model.title
                    binding.description.text = "Watch date: ${model.release_date}"

                    binding.transformationLayout.transitionName = model.title
                    if(model.poster_path.isNotEmpty()){
                        Picasso.get().load(IMAGE_URL+model.poster_path).into(binding.logo)
                    }

                    binding.root.setOnClickListener{
                        val navCont = binding.root.findNavController()
                        navCont.navigate(R.id.action_movie1Fragment_to_movieFragment)

                    }
                }
                is MovieRightBinding -> {
                    binding.title.text = model.title
                    binding.description.text = "Watch date: ${model.release_date}"

                    binding.transformationLayout.transitionName = model.title

                    if(model.poster_path.isNotEmpty()){
                        Log.d(TAG, model.poster_path)

                        Picasso.get().load(IMAGE_URL+model.poster_path).into(binding.logo)
                    }
                    binding.root.setOnClickListener{
                        val navCont = binding.root.findNavController()
                        navCont.navigate(R.id.action_movie1Fragment_to_movieFragment)

                    }
                }
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.MyHolder {
        val binding = if (layoutType == 0) {
            MovieRecBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        } else {
            MovieRightBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        }
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieAdapter.MyHolder, position: Int) {
        layoutType = position % 2

        holder.bindData(movies[position])

    }

    override fun getItemCount() = movies.size
}