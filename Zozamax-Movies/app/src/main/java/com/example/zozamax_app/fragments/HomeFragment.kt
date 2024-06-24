package com.example.zozamax_app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.zozamax_app.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        val listFragment = ListFragment()
        childFragmentManager.beginTransaction().apply {
            replace(binding.list.id, listFragment)
            addToBackStack(null)
            commit()
        }

        val trailerFragment = TrailerFragment()
        childFragmentManager.beginTransaction().apply {
            replace(binding.trailer.id, trailerFragment)
            addToBackStack(null)
            commit()
        }


        return binding.root
    }
}
