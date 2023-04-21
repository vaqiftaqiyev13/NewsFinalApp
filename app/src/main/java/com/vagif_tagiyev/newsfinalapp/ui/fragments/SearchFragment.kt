package com.vagif_tagiyev.newsfinalapp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.core.os.bundleOf
import androidx.core.view.setPadding
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vagif_tagiyev.newsfinalapp.R
import com.vagif_tagiyev.newsfinalapp.databinding.FragmentSearchBinding
import com.vagif_tagiyev.newsfinalapp.model.Article
import com.vagif_tagiyev.newsfinalapp.ui.MainActivity
import com.vagif_tagiyev.newsfinalapp.ui.adapter.NewsAdapter
import com.vagif_tagiyev.newsfinalapp.ui.viewmodel.NewsViewModel
import com.vagif_tagiyev.newsfinalapp.util.NewsResponse
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SearchFragment : Fragment() {
    lateinit var searchBinding: FragmentSearchBinding

    lateinit var searchModel: NewsViewModel

    lateinit var searchNewsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        searchBinding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        searchModel = (requireActivity() as MainActivity).newsModel
        searchRecyclerView()


        searchNewsAdapter.setOnItemClickListener {
            val bundle = bundleOf("article" to it)
            findNavController().navigate(R.id.search_desc, bundle)
        }

        var searchJob: Job? = null

        searchBinding.searchView.addTextChangedListener {
            searchJob?.cancel()
            searchJob = MainScope().launch {
                delay(300L)
                it?.let {
                    if (it.toString().isNotEmpty()) {
                        searchModel.searchNews(it.toString())
                    }
                }
            }
        }


        searchModel.searchNews.observe(viewLifecycleOwner, Observer { newsResponse ->
            when (newsResponse) {
                is NewsResponse.SuccessResponse -> {
                    hideProgressBar()
                    newsResponse.data?.let {
                        searchNewsAdapter.differList.submitList(it.articles.toList())
                        val totalPage = it.totalResults / 20 + 2
                        isLastPage = searchModel.topNewsPage == totalPage

                        if (isLastPage){
                            searchBinding.SearchAdapter.setPadding(0,0,0,0)
                        }
                    }
                }

                is NewsResponse.ErrorResponse -> {
                    hideProgressBar()
                    newsResponse.message?.let {
                        Log.e("search_error", "Error: $it")
                    }
                }

                is NewsResponse.LoadResponse -> {
                    showProgressBar()
                }
            }
        })



        return searchBinding.root
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
            if (shouldPaginate) {
                searchModel.searchNews(searchBinding.searchView.text.toString())
                isScrolling = false
            } else {
                searchBinding.SearchAdapter.setPadding(0, 0, 0, 0)
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }
    }


    private fun searchRecyclerView() {
        searchNewsAdapter = NewsAdapter()

        searchBinding.SearchAdapter.apply {
            searchBinding.SearchAdapter.adapter = searchNewsAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@SearchFragment.scrollListener)


        }
    }

    private fun hideProgressBar() {
        searchBinding.searchProgressBar.visibility = View.INVISIBLE
        isLastPage = false
    }

    private fun showProgressBar() {
        searchBinding.searchProgressBar.visibility = View.VISIBLE
        isLoading = true
    }

}