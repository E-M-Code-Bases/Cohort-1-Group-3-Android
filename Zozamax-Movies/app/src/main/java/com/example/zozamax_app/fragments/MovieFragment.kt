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
import com.example.zozamax_app.data.Result
import com.example.zozamax_app.databinding.FragmentMovieBinding
import com.skydoves.transformationlayout.TransformationLayout
import com.skydoves.transformationlayout.onTransformationEndContainer


class   MovieFragment : Fragment() {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val params = arguments?.getParcelable<TransformationLayout.Params>("TransformationParams")
        onTransformationEndContainer(params)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val poster = arguments?.getParcelable<Result>(posterKey)
        poster?.let {
            // [Step2]: sets a transition name to the target view.
            binding.detailContainer.transitionName = poster.title
        }
        // Set up the VideoView
        val videoView: VideoView = binding.movie
        val videoUri: Uri = Uri.parse("android.resource://" + requireActivity().packageName + "/" + R.raw.movie)

        videoView.setVideoURI(videoUri)
        videoView.setMediaController(MediaController(requireContext()))
        videoView.requestFocus()
        videoView.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "LibraryFragment"
        const val posterKey = "posterKey"
        const val paramsKey = "paramsKey"
    }
}
