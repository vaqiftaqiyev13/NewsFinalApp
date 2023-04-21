package com.vagif_tagiyev.newsfinalapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.vagif_tagiyev.newsfinalapp.R
import com.vagif_tagiyev.newsfinalapp.databinding.FragmentFavoritesBinding
import com.vagif_tagiyev.newsfinalapp.ui.MainActivity
import com.vagif_tagiyev.newsfinalapp.ui.adapter.NewsAdapter
import com.vagif_tagiyev.newsfinalapp.ui.viewmodel.NewsViewModel


class FavoritesFragment : Fragment() {
    lateinit var favoritesModel: NewsViewModel
    lateinit var favoritesBinding: FragmentFavoritesBinding
    lateinit var favAdapter: NewsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        favoritesBinding = FragmentFavoritesBinding.inflate(layoutInflater, container, false)
        favoriteRecyclerView()

        favAdapter.setOnItemClickListener {
            val bundle = bundleOf("article" to it)
            findNavController().navigate(R.id.favorites__desc,bundle)
        }

        favoritesModel = (requireActivity() as MainActivity).newsModel


        favoritesModel.getSavedNews().observe(viewLifecycleOwner, Observer {
            favAdapter.differList.submitList(it)
        })

        val itemTouchHelperCallBack = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    val article = favAdapter.differList.currentList[position]
                favoritesModel.deleteNews(article)
                Snackbar.make(view!!,"Xəbər favoritlərdən silindi.",Snackbar.LENGTH_SHORT).apply {
                    setAction("Geri Qaytar"){
                        favoritesModel.saveNews(article)
                    }
                    show()
                }
            }



        }


        ItemTouchHelper(itemTouchHelperCallBack).apply {
            attachToRecyclerView(favoritesBinding.FavoritesAdapter)
        }

        return favoritesBinding.root
    }

    private fun favoriteRecyclerView() {
        favAdapter = NewsAdapter()

        favoritesBinding.FavoritesAdapter.apply {
            favoritesBinding.FavoritesAdapter.adapter = favAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }


}