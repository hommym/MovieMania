package com.example.moviemania.auth.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.moviemania.auth.component.LayoutAnimator
import com.example.moviemania.databinding.FragmentLoginBinding
import com.example.moviemania.home.ui.HomeActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


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
//                the delay is simulate the request sent to server
               val remoteJwt= fragActivityViewModel.getJwtRemotely(views.loginEmail.text.toString(),views.loginPassword.text.toString())

               if(remoteJwt!=null){
                   //                moving to home activity
                   startActivity(Intent(requireContext(),HomeActivity::class.java))
                   requireActivity().finish()
               }
                else{
//                   This just temporary
                 Toast.makeText(requireContext(),"Invalid Credentials",Toast.LENGTH_SHORT).show()
               }


            }

        }

        views.createAccountButton.setOnClickListener {
            fragActivityViewModel.fragmentNavigator?.replaceFragment(SignupFrag())
        }


        views.resetPasswordButton.setOnClickListener {
            PasswordResetFrag().show(parentFragmentManager,"PasswordResetFrag")
        }
    }


}