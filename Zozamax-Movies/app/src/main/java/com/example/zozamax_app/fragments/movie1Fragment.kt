package com.example.zozamax_app.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.zozamax_app.R
import com.example.zozamax_app.databinding.FragmentMovie1Binding


class Movie1Fragment : Fragment() {

    private lateinit var binding: FragmentMovie1Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovie1Binding.inflate(inflater, container, false)

        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        val navController = navHostFragment.navController
        binding.navView.bringToFront()
        binding.navView.setupWithNavController(navHostFragment.navController)
        binding.navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.toprated -> {
                    navController.navigate(R.id.TopFragment)
                    true
                }

                else -> false
            }

        }

        binding.image.setOnClickListener {
            binding.drawerNav.openDrawer(GravityCompat.START)
        }

        return binding.root
    }
}


