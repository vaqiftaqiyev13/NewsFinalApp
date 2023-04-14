package com.vagif_tagiyev.newsfinalapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vagif_tagiyev.newsfinalapp.R
import com.vagif_tagiyev.newsfinalapp.databinding.FragmentFavoritesBinding


class FavoritesFragment : Fragment() {
    lateinit var favoritesBinding: FragmentFavoritesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        val favoritesBinding = FragmentFavoritesBinding.inflate(layoutInflater,container,false)

        return favoritesBinding.root
    }

}