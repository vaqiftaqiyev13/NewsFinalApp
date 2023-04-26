package com.vagif_tagiyev.newsfinalapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.vagif_tagiyev.newsfinalapp.R
import com.vagif_tagiyev.newsfinalapp.databinding.FragmentAccountBinding

class accountFragment : Fragment() {
    val accArgs : accountFragmentArgs by navArgs()
    lateinit var accountBinding: FragmentAccountBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        accountBinding = FragmentAccountBinding.inflate(layoutInflater, container, false)
        val emailBundle = accArgs.eMailAccount.toString()
        val passwordBundle = accArgs.passwordAccount.toString()

        accountBinding.bundText.text = emailBundle.toString()
        return accountBinding.root

    }

}