package com.lara.mvvmmtvshows.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.lara.mvvmmtvshows.R
import com.lara.mvvmmtvshows.adapters.TVShowsAdapter
import com.lara.mvvmmtvshows.databinding.ActivityMainBinding
import com.lara.mvvmmtvshows.models.TVShow
import com.lara.mvvmmtvshows.viewmodels.MostPopularViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MostPopularViewModel
    private lateinit var activityMainBinding: ActivityMainBinding
    private val tvShows = mutableListOf<TVShow>()
    private lateinit var tvShowsAdapter: TVShowsAdapter
    private var currentPage: Int = 1
    private var totalAvailablePages= 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        tvShowsAdapter = TVShowsAdapter(tvShows)
        doInitialization()
    }

    private fun doInitialization() {
        activityMainBinding.tvShowRecyclerView.setHasFixedSize(true)
        viewModel = ViewModelProvider(this).get(MostPopularViewModel::class.java)
        activityMainBinding.tvShowRecyclerView.adapter = tvShowsAdapter
        activityMainBinding.tvShowRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!activityMainBinding.tvShowRecyclerView.canScrollVertically(1)) {
                    if (currentPage <= totalAvailablePages) {
                        currentPage ++;
                        getMostPopularTvShows()
                    }
                }
            }
        })
        getMostPopularTvShows()
    }

    private fun getMostPopularTvShows() {
        toggleLoading()
        viewModel.getMostPopularShows(currentPage).observe(this, { mostPopularTVShowsResponse ->
            toggleLoading()
            mostPopularTVShowsResponse.let { tvShowsResponse ->
                totalAvailablePages = tvShowsResponse.totalPages
                mostPopularTVShowsResponse.tvShows.let { tvShows ->
                    val oldCount = tvShows.size
                    this.tvShows.addAll(tvShows)
                    tvShowsAdapter.notifyItemRangeInserted(oldCount, tvShows.size)
                }
            }
        })
    }

    private fun toggleLoading() {
        if (currentPage == 1) {
            activityMainBinding.isLoading = !(activityMainBinding.isLoading != null && activityMainBinding.isLoading == true)
        } else {
            activityMainBinding.isLoadingMore = !(activityMainBinding.isLoadingMore != null && activityMainBinding.isLoadingMore == true)
        }
    }

}