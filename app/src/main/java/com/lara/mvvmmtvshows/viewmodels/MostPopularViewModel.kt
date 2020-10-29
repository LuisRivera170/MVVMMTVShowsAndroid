package com.lara.mvvmmtvshows.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lara.mvvmmtvshows.repositories.MostPopularTVShowsRepository
import com.lara.mvvmmtvshows.responses.TVShowResponse

class MostPopularViewModel: ViewModel() {

    private val mostPopularTVShowsRepository: MostPopularTVShowsRepository = MostPopularTVShowsRepository()

    fun getMostPopularShows(page: Int): LiveData<TVShowResponse> {
        return mostPopularTVShowsRepository.getMostPopularTVShows(page)
    }

}