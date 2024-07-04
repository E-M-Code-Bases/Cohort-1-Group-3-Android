package com.example.zozamax_app.fragments

import android.os.Bundle
import android.util.Log
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.zozamax_app.MoviePlayer
import com.example.zozamax_app.PopularModelProvider
import com.example.zozamax_app.PopularRepository
import com.example.zozamax_app.PopularViewModel
import com.example.zozamax_app.R
import com.example.zozamax_app.RecyclerViewAdapter
import com.example.zozamax_app.databinding.PopularmoviesfragmentBinding



class PopularMoviesFragment: Fragment()  {
    private lateinit var binding:PopularmoviesfragmentBinding
    private lateinit var player: ExoPlayer
    private lateinit var playerView: PlayerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding=PopularmoviesfragmentBinding.inflate(inflater,container,false)
        player = ExoPlayer.Builder(requireContext()).build()
        playerView = requireActivity().findViewById(R.id.VideoView)
        MoviePlayer.initializePlayer(requireContext(), playerView, "https://atunwadigital.streamguys1.com/kiss100fm")
        YoutubeExtractor(this) {
            override fun onExtractionComplete(ytFiles: SparseArray<YtFile>, vMeta: YtVideoDetails?) {
                super.onExtractionComplete(ytFiles, vMeta)
                if (ytFiles != null && vMeta != null) {
                    val itag = 22 // Example itag, adjust according to your needs
                    val downloadUrl = ytFiles.get(itag)?.url ?: ""
                    // Setup ExoPlayer with the YouTube video URL
                    val mediaItem = MediaItem.fromUri(downloadUrl)
                    player.setMediaItem(mediaItem)
                    player.prepare()
                    player.playWhenReady = true
                }
            }
        }.extract("YOUR_YOUTUBE_VIDEO_URL")

        val popularRepo=PopularRepository()
        val v by viewModels<PopularViewModel> { PopularModelProvider(popularRepo) }
        v.popularPlayingMovies.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()){
                val adp = RecyclerViewAdapter(it)
                binding.RecyclerView.apply {
                    layoutManager = GridLayoutManager(requireContext(), 2)
                    adapter = adp
                    setHasFixedSize(true)
                }
            }
            Log.d("Movies",it.toString())
        })
        return binding.root
    }

}