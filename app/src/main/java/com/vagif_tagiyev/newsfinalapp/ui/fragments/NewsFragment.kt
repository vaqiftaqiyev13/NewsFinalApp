package com.vagif_tagiyev.newsfinalapp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vagif_tagiyev.newsfinalapp.R
import com.vagif_tagiyev.newsfinalapp.databinding.FragmentNewsBinding
import com.vagif_tagiyev.newsfinalapp.model.XeberchiResponse
import com.vagif_tagiyev.newsfinalapp.ui.MainActivity
import com.vagif_tagiyev.newsfinalapp.ui.adapter.NewsAdapter
import com.vagif_tagiyev.newsfinalapp.ui.viewmodel.NewsViewModel
import com.vagif_tagiyev.newsfinalapp.util.NewsResponse

class NewsFragment : Fragment() {
    lateinit var newsBinding: FragmentNewsBinding
    val newsTag = "NewsFragment"

    lateinit var newsModel: NewsViewModel

    lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        newsBinding = FragmentNewsBinding.inflate(layoutInflater,container,false)

        newsModel = (requireActivity() as MainActivity).newsModel
        newsRecyclerView()
        newsAdapter.setOnItemClickListener {
            val bundle = bundleOf("article" to it)
            findNavController().navigate(R.id.news_desc,bundle)
        }



        newsModel.topNews.observe(viewLifecycleOwner, Observer { newsResponse ->
            when (newsResponse) {
                is NewsResponse.SuccessResponse -> {
                    hideProgressBar()
                    newsResponse.data?.let {
                        newsAdapter.differList.submitList(it.articles.toList())
                        val totalPage = it.totalResults / 20 + 2
                        isLastPage = newsModel.topNewsPage == totalPage

                        if (isLastPage){
                            newsBinding.recyclerRow.setPadding(0,0,0,0)
                        }
                    }
                }

                is NewsResponse.ErrorResponse -> {
                    hideProgressBar()
                    newsResponse.message?.let {
                        Log.e(newsTag,"Error: $it")
                    }
                }

                is NewsResponse.LoadResponse -> {
                    showProgressBar()
                }
            }
        })


        return  newsBinding.root
    }

    private fun hideProgressBar() {
        newsBinding.newsProgressBar.visibility = View.INVISIBLE
        isLoading = false
    }

    private fun showProgressBar() {
        newsBinding.newsProgressBar.visibility = View.VISIBLE
        isLoading = true
    }

    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= 20
            val shouldPaginate = isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning &&
                    isTotalMoreThanVisible && isScrolling
            if(shouldPaginate) {
                newsModel.getAllNews("us")
                isScrolling = false
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }
    }



    private fun newsRecyclerView() {
        newsAdapter = NewsAdapter()

        newsBinding.recyclerRow.apply {
            newsBinding.recyclerRow.adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@NewsFragment.scrollListener)


        }
    }

}