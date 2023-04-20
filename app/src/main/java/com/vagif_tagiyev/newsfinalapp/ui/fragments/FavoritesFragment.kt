package com.vagif_tagiyev.newsfinalapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vagif_tagiyev.newsfinalapp.R
import com.vagif_tagiyev.newsfinalapp.databinding.FragmentFavoritesBinding
import com.vagif_tagiyev.newsfinalapp.ui.MainActivity
import com.vagif_tagiyev.newsfinalapp.ui.viewmodel.NewsViewModel


class FavoritesFragment : Fragment() {
    lateinit var favoritesModel: NewsViewModel
    lateinit var favoritesBinding: FragmentFavoritesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val favoritesBinding = FragmentFavoritesBinding.inflate(layoutInflater, container, false)

        favoritesModel = (requireActivity() as MainActivity).newsModel

        return favoritesBinding.root
    }

}