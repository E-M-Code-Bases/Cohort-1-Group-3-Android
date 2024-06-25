package com.example.zozamax_app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.GridLayoutManager
import com.example.zozamax_app.R
import com.example.zozamax_app.adapters.MovieAdapter
import com.example.zozamax_app.adapters.items
import com.example.zozamax_app.databinding.FragmentHomeBinding
import com.example.zozamax_app.repository.PopularMovieRepo
import com.example.zozamax_app.viewmodel.PopularModelProvider
import com.example.zozamax_app.viewmodel.PopularViewModel
import com.google.android.material.navigation.NavigationView

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        drawerLayout = binding.drawerLayout
        navigationView = binding.navView

        val repo = PopularMovieRepo()
        val productViewModel: PopularViewModel by viewModels {
            PopularModelProvider(repo)
        }

        productViewModel.popularMovies.observe(viewLifecycleOwner, Observer { movies ->
            // Update UI with the list of movies
        })

        val movies = items().listMovies

        // Initializing the recycler view
        binding.recView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = MovieAdapter(movies, requireContext())
            setHasFixedSize(true)
        }

        // Set up NavigationView with NavController
        val navController = findNavController()
        NavigationUI.setupWithNavController(navigationView, navController)

        return binding.root
    }
}
