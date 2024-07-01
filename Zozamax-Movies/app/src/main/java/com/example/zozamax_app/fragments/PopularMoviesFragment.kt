package com.example.zozamax_app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.zozamax_app.R
import com.example.zozamax_app.databinding.PopularmoviesfragmentBinding

class PopularMoviesFragment: Fragment()  {
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding:PopularmoviesfragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding=PopularmoviesfragmentBinding.inflate(inflater,container,false)
        recyclerView=binding.RecyclerView
        return binding.root
    }
}