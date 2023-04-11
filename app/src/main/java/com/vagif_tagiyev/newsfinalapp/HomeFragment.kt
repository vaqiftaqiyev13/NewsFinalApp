package com.vagif_tagiyev.newsfinalapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vagif_tagiyev.newsfinalapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    lateinit var homeBinding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)


        return homeBinding.root
    }

}