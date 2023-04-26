package com.vagif_tagiyev.newsfinalapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vagif_tagiyev.newsfinalapp.R
import com.vagif_tagiyev.newsfinalapp.database.NewsDatabase
import com.vagif_tagiyev.newsfinalapp.databinding.FragmentHomeBinding
import com.vagif_tagiyev.newsfinalapp.repository.NewsRepository
import com.vagif_tagiyev.newsfinalapp.ui.viewmodel.NewsProvider
import com.vagif_tagiyev.newsfinalapp.ui.viewmodel.NewsViewModel


class HomeFragment : Fragment() {
    lateinit var homeBinding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        /*val newsRepository = NewsRepository(NewsDatabase(requireContext()))
        val newsModelProviderFactory = NewsProvider(newsRepository)
        newsModel = ViewModelProvider(this,newsModelProviderFactory)[NewsViewModel ::class.java]*/


        //Navigation
        val homeBinding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        val navHostFragment = childFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        homeBinding.bottomNav.setupWithNavController(navHostFragment.findNavController())




        return homeBinding.root
    }

}