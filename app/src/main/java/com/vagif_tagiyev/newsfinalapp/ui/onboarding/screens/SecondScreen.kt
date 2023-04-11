package com.vagif_tagiyev.newsfinalapp.ui.onboarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.vagif_tagiyev.newsfinalapp.R
import com.vagif_tagiyev.newsfinalapp.databinding.FragmentSecondScreenBinding

class SecondScreen : Fragment() {
    private lateinit var secondScreenBinding: FragmentSecondScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        val secondScreenBinding = FragmentSecondScreenBinding.inflate(layoutInflater,container,false)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewpager)

        secondScreenBinding.novbetiSecond.setOnClickListener {
            viewPager?.currentItem = 2
        }

        return secondScreenBinding.root
    }

}