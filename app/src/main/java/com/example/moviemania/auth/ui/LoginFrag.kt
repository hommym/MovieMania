package com.example.moviemania.auth.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.moviemania.auth.component.LayoutAnimator
import com.example.moviemania.databinding.FragmentLoginBinding
import com.example.moviemania.home.ui.HomeActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LoginFrag : Fragment() {


    lateinit var views:FragmentLoginBinding
    private val fragActivityViewModel:MainViewModel by activityViewModels()
    private lateinit var  layoutAnimator:LayoutAnimator

//    the disableEditText when called either allows(when true is passed into it) or prevent(when nothing is passed into it)
//    users from interacting with the editText the function was called on
    private fun EditText.disableEditText(enable:Boolean=false):EditText{
        this.isEnabled=enable
        this.isFocusable=enable
        this.isFocusableInTouchMode=enable
        this.isCursorVisible=enable
        return  this
    }


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

//            checking if the relevant text fields are not empty before sending request
            if(views.loginEmail.text.toString().isNotEmpty()&&views.loginPassword.text.toString().isNotEmpty()){

//                disabling the email and password text field
                views.loginEmail.disableEditText()
                views.loginPassword.disableEditText()

                lifecycleScope.launch(Dispatchers.Main) {
//                  starting button to make button dissappear
                    layoutAnimator.startButtonClickAnimation(it as Button,views.loadingSpinner)

                    try {
                        // the request sent to server
                        val remoteJwt= fragActivityViewModel.getJwtRemotely(views.loginEmail.text.toString(),views.loginPassword.text.toString())
                        if(remoteJwt!=null){
                            // storing the jwt locally
                            fragActivityViewModel.storeJwtLocally(remoteJwt as String)

                            Toast.makeText(requireContext(),"Login Successfull",Toast.LENGTH_SHORT).show()

                            //                moving to home activity
                            startActivity(Intent(requireContext(),HomeActivity::class.java))
                            requireActivity().finish()
                        }
                        else{
//                         showing messages relating to network error
                            Toast.makeText(requireContext(),"Network Error,Check Internet connection",Toast.LENGTH_SHORT).show()
                        }
                    }
                    catch (err:Exception){
//                        showing message relating failed request
                        Toast.makeText(requireContext(),"Login Failed:${err.message}",Toast.LENGTH_SHORT).show()
                    }
                    finally {
                        //                      reversing button animation to bring back button
                        layoutAnimator.startButtonClickAnimation(it as Button,views.loadingSpinner,true)

//                        enabling email and password text fields
                        views.loginEmail.disableEditText(true)
                        views.loginPassword.disableEditText(true)
                    }
                }
            }
            else{
                Toast.makeText(requireContext(),"All Fields must be filled",Toast.LENGTH_SHORT).show()
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