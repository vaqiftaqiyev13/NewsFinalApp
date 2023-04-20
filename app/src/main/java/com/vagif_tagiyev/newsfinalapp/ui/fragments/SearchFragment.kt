package com.vagif_tagiyev.newsfinalapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vagif_tagiyev.newsfinalapp.R
import com.vagif_tagiyev.newsfinalapp.databinding.FragmentSearchBinding
import com.vagif_tagiyev.newsfinalapp.ui.MainActivity
import com.vagif_tagiyev.newsfinalapp.ui.viewmodel.NewsViewModel


class SearchFragment : Fragment() {
    lateinit var searchBinding : FragmentSearchBinding
    lateinit var searchModel : NewsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        val searchBinding = FragmentSearchBinding.inflate(layoutInflater,container,false)
        searchModel = (requireActivity() as MainActivity).newsModel
        return searchBinding.root
    }

}