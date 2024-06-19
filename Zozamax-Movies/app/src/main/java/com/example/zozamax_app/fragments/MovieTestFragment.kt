package com.example.zozamax_app.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zozamax_app.adapters.MoviesAdapter
import com.example.zozamax_app.api_services.Movie
import com.example.zozamax_app.api_services.MovieResponse
import com.example.zozamax_app.api_services.RetrofitInstance
import com.example.zozamax_app.databinding.FragmentMovieTestBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieTestFragment : Fragment() {

    private var _binding: FragmentMovieTestBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieTestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchMovies()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun fetchMovies() {
        val call = RetrofitInstance.api.getFavoriteMovies()
        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { movieResponse ->
                        setupRecyclerView(movieResponse.results)
                    }
                } else {
                    Log.e("MovieTestFragment", "Failed to get movies: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("MovieTestFragment", "Error: ${t.message}")
            }
        })
    }

    private fun setupRecyclerView(movies: List<Movie>) {
        val sortedMovies = movies.sortedByDescending { it.popularity }
        val adapter = MoviesAdapter(sortedMovies)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }
}
