package com.example.zozamax_app.fragments


import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.VideoView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.zozamax_app.R
import com.example.zozamax_app.data.Result
import com.example.zozamax_app.databinding.FragmentMovieBinding
import com.skydoves.transformationlayout.TransformationLayout
import com.skydoves.transformationlayout.onTransformationEndContainer


class   MovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieBinding
    private val args: MovieFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        val movie = args.movie

        //binding data to the view
        binding.title.text = movie.title
        binding.overview.text = movie.overview
        binding.releaseDate.text = movie.release_date

        return binding.root
    }



}
