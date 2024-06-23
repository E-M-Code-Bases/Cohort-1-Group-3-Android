package com.example.zozamax_app.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView.Orientation
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.zozamax_app.adapters.MovieAdapter
import com.example.zozamax_app.adapters.items
import com.example.zozamax_app.databinding.FragmentHomeBinding
import com.example.zozamax_app.viewmodel.PopularViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        val productViewModel: PopularViewModel by viewModels {
            ProductProvider(repo)
        }

        val trailerFragment = TrailerFragment()
        childFragmentManager.beginTransaction().apply {
            replace(binding.trailer.id, trailerFragment)
            addToBackStack(null)
            commit()
        }

        val listFragment = ListFragment()
        childFragmentManager.beginTransaction().apply {
            replace(binding.list.id, listFragment)
            addToBackStack(null)
            commit()
        }

        val movies = items().listMovies

        // initialising the recycler view
        binding.recView.apply {
            layoutManager = LinearLayoutManager(requireContext(), VERTICAL, false)
            adapter = MovieAdapter(movies, requireContext())
            setHasFixedSize(true)
        }

        return binding.root
    }
}
