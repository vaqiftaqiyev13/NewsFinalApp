package com.vagif_tagiyev.newsfinalapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.vagif_tagiyev.newsfinalapp.R
import com.vagif_tagiyev.newsfinalapp.databinding.FragmentDescriptionBinding
import com.vagif_tagiyev.newsfinalapp.ui.MainActivity
import com.vagif_tagiyev.newsfinalapp.ui.viewmodel.NewsViewModel


class DescriptionFragment : Fragment() {
    lateinit var descBinding : FragmentDescriptionBinding
    lateinit var descModel : NewsViewModel
    val bundleFragmentArgs : DescriptionFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
         descBinding = FragmentDescriptionBinding.inflate(layoutInflater,container,false)
        descModel = (requireActivity() as MainActivity).newsModel

        val articleArg = bundleFragmentArgs.article

        descBinding.newsWebView.apply {
            webViewClient = WebViewClient()
            loadUrl(articleArg.url)
        }
        return descBinding.root
    }

}