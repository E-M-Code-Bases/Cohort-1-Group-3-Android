package com.example.zozamax_app.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.zozamax_app.Data.models.Result
import com.example.zozamax_app.MoviePlayer
import com.example.zozamax_app.PopularModelProvider
import com.example.zozamax_app.PopularRepository
import com.example.zozamax_app.PopularViewModel
import com.example.zozamax_app.R
import com.example.zozamax_app.RecyclerViewAdapter
import com.example.zozamax_app.databinding.PopularmoviesfragmentBinding
import kotlinx.coroutines.launch

class PopularMoviesFragment: Fragment(), RecyclerViewAdapter.OnItemClickListener  {
    private lateinit var binding:PopularmoviesfragmentBinding
    private lateinit var player: ExoPlayer
    private lateinit var playerView: PlayerView
    private var selectedMovieId: Int? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding=PopularmoviesfragmentBinding.inflate(inflater,container,false)
        player = ExoPlayer.Builder(requireContext()).build()
        playerView = requireActivity().findViewById(R.id.VideoView)
        MoviePlayer.initializePlayer(requireContext(), playerView, "https://atunwadigital.streamguys1.com/kiss100fm")

        val popularRepo=PopularRepository()
        val popularViewModel by viewModels<PopularViewModel> { PopularModelProvider(popularRepo) }

        popularViewModel.popularPlayingMovies.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()){
                val adp = RecyclerViewAdapter(it,this)
                binding.RecyclerView.apply {
                    layoutManager = GridLayoutManager(requireContext(), 2)
                    adapter = adp
                    setHasFixedSize(true)
                }
            }
            Log.d("Movies",it.toString())
        })
        binding.playButton.setOnClickListener {
            selectedMovieId?.let { movieId ->
                playMovie(movieId)
            }
        }

        return binding.root
    }

    override fun onItemClick(movie: Result) {
        selectedMovieId = movie.id
        binding.playButton.visibility = View.VISIBLE
        Log.d("Selected Movie ID", movie.id.toString())
    }

    private fun playMovie(id: Int) {
        Log.d("Playing Movie ID", id.toString())

        lifecycleScope.launch {
            val popularRepo = PopularRepository()
            val response = popularRepo.getVideos(id)

            if (response.isSuccessful && response.body() != null) {
                val videoKey = response.body()!!.results.firstOrNull()?.key
                if (videoKey != null) {
                    val videoUrl = "https://www.youtube.com/watch?v=$videoKey"
                    playVideo(videoUrl)
                } else {
                    Log.e("Video Error", "No video key found")
                }
            } else {
                Log.e("API Error", "Failed to fetch videos")
            }
        }
    }
    private fun playVideo(videoUrl: String) {
        MoviePlayer.initializePlayer(requireContext(), playerView, videoUrl)
        player.playWhenReady = true
    }

}