package com.example.moviemania.auth.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import com.example.moviemania.R



class PasswordResetFrag : DialogFragment() {

    lateinit var views:View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        views=inflater.inflate(R.layout.fragment_password_reset, container, false)

        dialog?.setCancelable(false)

        views.findViewById<ImageView>(R.id.cancelButton).setOnClickListener {
            dialog?.cancel()
        }
        return views
    }




}