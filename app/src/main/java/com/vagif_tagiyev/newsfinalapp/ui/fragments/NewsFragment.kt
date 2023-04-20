package com.vagif_tagiyev.newsfinalapp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vagif_tagiyev.newsfinalapp.R
import com.vagif_tagiyev.newsfinalapp.databinding.FragmentNewsBinding
import com.vagif_tagiyev.newsfinalapp.ui.MainActivity
import com.vagif_tagiyev.newsfinalapp.ui.adapter.NewsAdapter
import com.vagif_tagiyev.newsfinalapp.ui.viewmodel.NewsViewModel
import com.vagif_tagiyev.newsfinalapp.util.NewsResponse

class NewsFragment : Fragment() {
    lateinit var newsBinding: FragmentNewsBinding
    val NewsTag = "NewsFragment"

    lateinit var newsModel: NewsViewModel

    lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        newsBinding = FragmentNewsBinding.inflate(layoutInflater,container,false)

        newsModel = (requireActivity() as MainActivity).newsModel
        newsRecyclerView()

        newsModel.topNews.observe(viewLifecycleOwner, Observer { newsResponse ->
            when (newsResponse) {
                is NewsResponse.SuccessResponse -> {
                    newsResponse.data?.let {
                        newsAdapter.differList.submitList(it.articles)
                    }
                }

                is NewsResponse.ErrorResponse -> {
                    newsResponse.message?.let {
                        Log.e(NewsTag,"Error: $it")
                    }
                }

                is NewsResponse.LoadResponse -> {
                }
            }
        })


        return  newsBinding.root
    }




    private fun newsRecyclerView() {
        newsAdapter = NewsAdapter()

        newsBinding.recyclerRow.apply {
            newsBinding.recyclerRow.adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)


        }
    }
}