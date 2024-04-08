package com.example.moviemania.video.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.moviemania.R
import com.example.moviemania.databinding.FragmentMovieBinding


class MovieFragment : Fragment() {



    lateinit var views:FragmentMovieBinding
    val uiStateHolder:MovieViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        views= FragmentMovieBinding.inflate(inflater, container, false)
        return views.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      uiStateHolder.startVideo(views.videoView,requireActivity())

    }


}