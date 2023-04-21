package com.vagif_tagiyev.newsfinalapp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
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
            findNavController().navigate(R.id.search_desc,bundle)
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
                        searchNewsAdapter.differList.submitList(it.articles)
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

    private fun searchRecyclerView() {
        searchNewsAdapter = NewsAdapter()

        searchBinding.SearchAdapter.apply {
            searchBinding.SearchAdapter.adapter = searchNewsAdapter
            layoutManager = LinearLayoutManager(activity)

        }
    }

    private fun hideProgressBar() {
        searchBinding.searchProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        searchBinding.searchProgressBar.visibility = View.VISIBLE
    }

}