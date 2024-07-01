package com.example.zozamax_app.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zozamax_app.ModelProvider
import com.example.zozamax_app.MovieViewModel
import com.example.zozamax_app.NowPlayingRepository
import com.example.zozamax_app.R
import com.example.zozamax_app.RecyclerViewAdapter
import com.example.zozamax_app.databinding.NowplayingfragmentBinding

class NowPlayingFragment: Fragment()  {
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: NowplayingfragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = NowplayingfragmentBinding.inflate(inflater, container, false)

        val repo = NowPlayingRepository()
        val v by viewModels<MovieViewModel> {ModelProvider(repo) }
        v.nowPlayingMovies.observe(viewLifecycleOwner, Observer {
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
