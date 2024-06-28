package com.example.zozamax_app.fragments


import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.VideoView
import androidx.fragment.app.Fragment
import com.example.zozamax_app.R
import com.example.zozamax_app.databinding.FragmentMovie1Binding



class Movie1Fragment : Fragment() {

    private lateinit var binding: FragmentMovie1Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovie1Binding.inflate(inflater, container, false)


        return binding.root
    }


}
