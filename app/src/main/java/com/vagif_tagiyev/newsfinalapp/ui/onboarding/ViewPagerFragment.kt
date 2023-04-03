package com.vagif_tagiyev.newsfinalapp.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vagif_tagiyev.newsfinalapp.R
import com.vagif_tagiyev.newsfinalapp.databinding.FragmentViewPagerBinding
import com.vagif_tagiyev.newsfinalapp.ui.adapter.ViewPagerAdapter
import com.vagif_tagiyev.newsfinalapp.ui.onboarding.screens.FirstScreen
import com.vagif_tagiyev.newsfinalapp.ui.onboarding.screens.SecondScreen
import com.vagif_tagiyev.newsfinalapp.ui.onboarding.screens.ThirdScreen


class ViewPagerFragment : Fragment() {
    private lateinit var viewPagerBinding: FragmentViewPagerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewPagerBinding = FragmentViewPagerBinding.inflate(layoutInflater, container, false)

        val screenList = arrayListOf<Fragment>(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen()
        )

        val adapter =
            ViewPagerAdapter(
                screenList,
                requireActivity().supportFragmentManager,
                lifecycle
            )
        viewPagerBinding.viewpager.adapter = adapter

        return viewPagerBinding.root
    }

}