package com.lara.mvvmmtvshows.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.lara.mvvmmtvshows.R
import com.lara.mvvmmtvshows.databinding.ActivityTvShowDetailsBinding
import com.lara.mvvmmtvshows.models.TVShow
import com.lara.mvvmmtvshows.viewmodels.TVShowDetailsViewModel

class TvShowDetailsActivity: AppCompatActivity() {

    private lateinit var activityTVShowDetailsBinding: ActivityTvShowDetailsBinding
    private lateinit var tvShowDetailsViewModel: TVShowDetailsViewModel
    private var tvShow: TVShow? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityTVShowDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_tv_show_details)
        doInitialization()
    }

    private fun doInitialization() {
        tvShowDetailsViewModel = ViewModelProvider(this).get(TVShowDetailsViewModel::class.java)
        tvShow = intent.getParcelableExtra<TVShow>("tvShow")
        getTvShowDetails()
    }

    private fun getTvShowDetails() {
        tvShow?.let {
            activityTVShowDetailsBinding.isLoading = true
            val tvShowId: String = it.id.toString()
            tvShowDetailsViewModel.getTvShowDetails(tvShowId).observe(this, { tvShowDetailResponse ->
                activityTVShowDetailsBinding.isLoading = false
                Toast.makeText(this, tvShowDetailResponse.tvShowDetails.url, Toast.LENGTH_SHORT).show()
            })
        } ?: run {
            Toast.makeText(this, "Ocurrió un error, intentelo nuevamente", Toast.LENGTH_LONG).show()
            finish()
        }


    }

}