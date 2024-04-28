package com.example.moviemania.auth.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.moviemania.R
import com.example.moviemania.components.FragmentNavigator
import com.example.moviemania.databinding.FragmentSignupBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SignupFrag.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignupFrag : Fragment() {



    lateinit var views:FragmentSignupBinding
    val fragActivityViewModel:MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        views= FragmentSignupBinding.inflate(inflater, container, false)
        return views.root
    }


    override fun onResume() {
        super.onResume()

        views.signupBackButton.setOnClickListener {
            FragmentNavigator.back(parentFragmentManager)
        }
    }

}