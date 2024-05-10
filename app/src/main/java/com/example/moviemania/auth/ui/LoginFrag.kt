package com.example.moviemania.auth.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.moviemania.R
import com.example.moviemania.auth.component.LayoutAnimator
import com.example.moviemania.components.FragmentNavigator
import com.example.moviemania.databinding.FragmentLoginBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
    private lateinit var  layoutAnimator:LayoutAnimator


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        views= FragmentLoginBinding.inflate(inflater, container, false)

//   setting animation for the child views in layout
         layoutAnimator=LayoutAnimator(views.loginMainLayout,requireContext())
         layoutAnimator.settingUpAnimationConfigs()

        return views.root
    }

    override fun onResume() {
        super.onResume()

        lifecycleScope.launch(Dispatchers.Main) {
        layoutAnimator.startEnteranceAnimationForViews(views.loadingSpinner)

        }

        views.loginButton.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main) {
                layoutAnimator.startButtonClickAnimation(it as Button,views.loadingSpinner)
            }

        }

        views.createAccountButton.setOnClickListener {
            fragActivityViewModel.fragmentNavigator?.replaceFragment(SignupFrag())
        }


        views.resetPasswordButton.setOnClickListener {
            PasswordResetFrag().show(childFragmentManager,"PasswordResetFrag")
        }
    }


}