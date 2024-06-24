package com.example.zozamax_app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.example.zozamax_app.R
import com.example.zozamax_app.databinding.FragmentListBinding


class ListFragment: Fragment() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: FragmentListBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentListBinding.inflate(inflater, container, false)
        binding.popular.setOnClickListener {
            val navController = binding.popular.findNavController()
            navController.navigate(R.id.action_listFragment_to_movieFragment)
        }
            binding.favourite.setOnClickListener {
                val navController = binding.favourite.findNavController()
                navController.navigate(R.id.action_listFragment_to_movieFragment)


            }
        return binding.root
    }
}
