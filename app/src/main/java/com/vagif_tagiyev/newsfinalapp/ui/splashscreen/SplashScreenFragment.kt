package com.vagif_tagiyev.newsfinalapp.ui.splashscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vagif_tagiyev.newsfinalapp.R
import com.vagif_tagiyev.newsfinalapp.databinding.FragmentSplashScreenBinding

class SplashScreenFragment : Fragment() {
    private lateinit var splashScreenBinding : FragmentSplashScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val splashBinding = FragmentSplashScreenBinding.inflate(layoutInflater,container,false)

        return splashBinding.root
    }

}