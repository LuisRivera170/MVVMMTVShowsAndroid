package com.lara.mvvmmtvshows.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.lara.mvvmmtvshows.R
import com.lara.mvvmmtvshows.repositories.MostPopularTVShowsRepository
import com.lara.mvvmmtvshows.viewmodels.MostPopularViewModel
import kotlin.coroutines.EmptyCoroutineContext

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MostPopularViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MostPopularViewModel::class.java)

        getMostPopularTvShows()
    }

    private fun getMostPopularTvShows() {
        viewModel.getMostPopularShows(0).observe(this, { mostPopularTVShowsResponse ->
            Toast.makeText(this, "Total Pages: " + mostPopularTVShowsResponse.totalPages, Toast.LENGTH_LONG).show()
        })
    }

}