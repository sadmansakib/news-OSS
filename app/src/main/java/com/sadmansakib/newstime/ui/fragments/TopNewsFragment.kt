package com.sadmansakib.newstime.ui.fragments


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sadmansakib.newstime.R
import com.sadmansakib.newstime.models.NewsResponse
import com.sadmansakib.newstime.network.DataState
import com.sadmansakib.newstime.ui.adapters.NewsAdapter
import com.sadmansakib.newstime.ui.viewmodels.TopNewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_top_news.*
import javax.inject.Inject


@AndroidEntryPoint
class TopNewsFragment : Fragment(R.layout.fragment_top_news) {

    @Inject lateinit var newsAdapter: NewsAdapter

    private val topNewsViewModel: TopNewsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("topnews", "fragment started")
        setupRecyclerView()
        topNewsViewModel.breakingNews.observe(viewLifecycleOwner, Observer { responseState ->
            when(responseState){
                is DataState.SUCCESS<NewsResponse> -> {
                    hideProgressBar()
                    responseState.data.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is DataState.LOADING -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
    }


    private fun setupRecyclerView() {
        TopNews_recycler.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}