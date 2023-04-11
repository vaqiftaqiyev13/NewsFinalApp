package com.vagif_tagiyev.newsfinalapp.ui.splashscreen

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.vagif_tagiyev.newsfinalapp.R
import com.vagif_tagiyev.newsfinalapp.databinding.FragmentSplashScreenBinding
import kotlinx.coroutines.*

class SplashScreenFragment : Fragment() {
    private lateinit var splashScreenBinding: FragmentSplashScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val splashBinding = FragmentSplashScreenBinding.inflate(layoutInflater, container, false)

        val scope = CoroutineScope(Dispatchers.Main)

        scope.launch {
            delay(3000L)
            if (onBoardingFinished()) {
                splashToHome()
            } else {
                splashToViewPager()
            }
        }

        return splashBinding.root
    }

    private fun splashToViewPager() {
        val action = SplashScreenFragmentDirections.splashViewpagerScreen()
        findNavController().navigate(action)
    }

    private fun onBoardingFinished(): Boolean {
        val sharedPreferences =
            requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("Finish", false)

    }

    private fun splashToHome() {
        val action = SplashScreenFragmentDirections.splashToHome()
        findNavController().navigate(action)
    }


}