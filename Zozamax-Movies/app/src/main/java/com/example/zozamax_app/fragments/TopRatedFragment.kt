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
import com.example.zozamax_app.RecyclerViewAdapter
import com.example.zozamax_app.TopRatedModelProvider
import com.example.zozamax_app.TopRatedRepository
import com.example.zozamax_app.TopRatedViewModel
import com.example.zozamax_app.databinding.TopratedfragmentBinding

class TopRatedFragment: Fragment()  {
    private lateinit var binding: TopratedfragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding=TopratedfragmentBinding.inflate(inflater,container,false)
        val topRatedRepo=TopRatedRepository()
        val v by viewModels<TopRatedViewModel>{ TopRatedModelProvider(topRatedRepo) }
        v.topRatedMovies.observe(viewLifecycleOwner, Observer{
            if (it.isNotEmpty()){
//                val adp= RecyclerViewAdapter(it)
                binding.RecyclerView.apply {
                    layoutManager=GridLayoutManager(requireContext(),2)
//                    adapter=adp
                    setHasFixedSize(true)
                }
            }
            Log.d("Movies",it.toString())

        })

        return binding.root

    }
}