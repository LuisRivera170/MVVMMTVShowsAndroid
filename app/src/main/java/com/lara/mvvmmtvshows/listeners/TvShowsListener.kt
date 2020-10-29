package com.lara.mvvmmtvshows.listeners

import com.lara.mvvmmtvshows.models.TVShow

interface TvShowsListener {

    fun onTvShowClicked(tvShow: TVShow)

}