package com.example.zozamax_app.fragments


import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.VideoView
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

        val navHostFragment = childFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        binding.navView.bringToFront()
        binding.navView.setupWithNavController(navHostFragment.navController)
        binding.image.setOnClickListener{
            binding.drawerNav.openDrawer(GravityCompat.START)
        }


        return binding.root
    }


}
