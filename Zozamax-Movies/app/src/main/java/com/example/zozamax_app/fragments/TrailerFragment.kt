package com.example.zozamax_app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.zozamax_app.R

class TrailerFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_trailer, container, false)
    }

    companion object {

        fun newInstance(param1: String, param2: String) =
            TrailerFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}