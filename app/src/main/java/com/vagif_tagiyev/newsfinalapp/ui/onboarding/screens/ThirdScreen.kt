package com.vagif_tagiyev.newsfinalapp.ui.onboarding.screens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.vagif_tagiyev.newsfinalapp.R
import com.vagif_tagiyev.newsfinalapp.databinding.FragmentThirdScreenBinding

class ThirdScreen : Fragment() {
    lateinit var thirdScreenBinding: FragmentThirdScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val thirdScreenBinding =
            FragmentThirdScreenBinding.inflate(layoutInflater, container, false)
        thirdScreenBinding.finishThird.setOnClickListener {
            findNavController().navigate(R.id.pager_to_home)
            onBoardingFinished()
        }

        return thirdScreenBinding.root
    }

    private fun onBoardingFinished() {
        val sharedPreferences =
            requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val sharedEditor = sharedPreferences.edit()
        sharedEditor.putBoolean("Finish", true)
        sharedEditor.apply()

    }

}