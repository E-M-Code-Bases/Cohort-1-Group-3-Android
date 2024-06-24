package com.example.zozamax_app.fragments


import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.VideoView
import androidx.fragment.app.Fragment
import com.example.zozamax_app.R
import com.example.zozamax_app.databinding.FragmentTrailerBinding


class TrailerFragment : Fragment() {

    private var _binding: FragmentTrailerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTrailerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up the VideoView
        val videoView: VideoView = binding.trailerVideo
       // val videoUri: Uri = Uri.parse("android.resource://" + requireActivity().packageName + "/" + R.raw.trailer)
        val videoUri: Uri = Uri.parse("https://www.wootly.ch/?v=U7MAEEE4")

        videoView.setVideoURI(videoUri)
        videoView.setMediaController(MediaController(requireContext()))
        videoView.requestFocus()
        videoView.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
