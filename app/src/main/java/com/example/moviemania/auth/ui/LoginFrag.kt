package com.example.moviemania.auth.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.moviemania.R
import com.example.moviemania.components.FragmentNavigator
import com.example.moviemania.databinding.FragmentLoginBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFrag.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFrag : Fragment() {


    lateinit var views:FragmentLoginBinding
    private val fragActivityViewModel:MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        views= FragmentLoginBinding.inflate(inflater, container, false)

        return views.root
    }

    override fun onResume() {
        super.onResume()


        views.loginButton.setOnClickListener {

        }

        views.createAccountButton.setOnClickListener {
            val fragNav=FragmentNavigator(parentFragmentManager,SignupFrag(),R.id.main_layout,fragActivityViewModel,"SignUpTag")
            fragNav.replaceFragment()
        }
    }


}