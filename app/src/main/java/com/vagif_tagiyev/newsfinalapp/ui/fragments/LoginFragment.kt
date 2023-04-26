package com.vagif_tagiyev.newsfinalapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vagif_tagiyev.newsfinalapp.R
import com.vagif_tagiyev.newsfinalapp.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    lateinit var loginBinding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        loginBinding = FragmentLoginBinding.inflate(layoutInflater,container,false)



        return loginBinding.root
    }

}