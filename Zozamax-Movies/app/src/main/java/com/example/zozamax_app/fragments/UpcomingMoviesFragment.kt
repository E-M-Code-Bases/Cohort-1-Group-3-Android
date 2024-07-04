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
import com.example.zozamax_app.R
import com.example.zozamax_app.RecyclerViewAdapter
import com.example.zozamax_app.UpComingModelProvider
import com.example.zozamax_app.UpcomingRepository
import com.example.zozamax_app.UpcomingViewModel
import com.example.zozamax_app.databinding.UpcomingfragmentBinding

class UpcomingMoviesFragment: Fragment()  {
    private lateinit var binding: UpcomingfragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
       binding=UpcomingfragmentBinding.inflate(inflater,container,false)
val upComingRepo=UpcomingRepository()
        val v by viewModels<UpcomingViewModel> { UpComingModelProvider(upComingRepo) }
        v.upComingMovies.observe(viewLifecycleOwner,Observer{
            if (it.isNotEmpty()){
                val adp=RecyclerViewAdapter(it)
                binding.RecyclerView.apply {
                    layoutManager=GridLayoutManager(requireContext(),2)
                    adapter=adp
                    setHasFixedSize(true)
                }
            }
            Log.d ("Upcoming Movies",it.toString())
        })

        return binding.root
    }
}