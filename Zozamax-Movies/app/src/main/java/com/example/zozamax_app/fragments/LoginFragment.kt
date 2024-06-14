package com.example.zozamax_app.fragments

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.zozamax_app.R
import com.example.zozamax_app.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.login.setOnClickListener{
            val navController = binding.login.findNavController()
            navController.navigate(R.id.action_loginFragment_to_homeFragment)
            Toast.makeText(requireContext(), "login successful", Toast.LENGTH_LONG).show()
        }
        binding.signup.setOnClickListener{
            val navController = binding.signup.findNavController()
            navController.navigate(R.id.action_loginFragment_to_registerFragment)
        }
        return binding.root
    }

}