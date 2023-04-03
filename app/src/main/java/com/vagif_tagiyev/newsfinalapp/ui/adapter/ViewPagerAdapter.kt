package com.vagif_tagiyev.newsfinalapp.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(
    fragmentScreenList: ArrayList<Fragment>,
    fm: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fm, lifecycle) {

    private val screenList = fragmentScreenList

    override fun getItemCount(): Int {
        return screenList.size
    }

    override fun createFragment(position: Int): Fragment {
        return screenList[position]
    }
}