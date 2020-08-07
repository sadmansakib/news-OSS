package com.sadmansakib.newstime.ui.fragments


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.sadmansakib.newstime.R
import com.sadmansakib.newstime.ui.viewmodels.TopNewsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TopNewsFragment : Fragment(R.layout.fragment_top_news) {

    private val topNewsViewModel: TopNewsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("topnews", "fragment started")
        topNewsViewModel.breakingNews.observe(viewLifecycleOwner, Observer { news ->
            Log.d("info", news.status)
        })
    }

}