package com.example.zozamax_app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.zozamax_app.R
import com.example.zozamax_app.databinding.UpcomingfragmentBinding

class UpcomingMoviesFragment: Fragment()  {
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: UpcomingfragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
       binding=UpcomingfragmentBinding.inflate(inflater,container,false)
        recyclerView=binding.RecyclerView

        return binding.root
    }
}