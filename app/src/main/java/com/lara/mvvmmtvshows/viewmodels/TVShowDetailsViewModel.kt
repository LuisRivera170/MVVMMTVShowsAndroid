package com.lara.mvvmmtvshows.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lara.mvvmmtvshows.repositories.TvShowDetailsRepository
import com.lara.mvvmmtvshows.responses.TVShowDetailsResponse

class TVShowDetailsViewModel: ViewModel() {

    private val tvShowDetailsRepository: TvShowDetailsRepository = TvShowDetailsRepository()

    fun getTvShowDetails(tvShowId: String): LiveData<TVShowDetailsResponse> {
        return tvShowDetailsRepository.getTvShowDetails(tvShowId)
    }

}