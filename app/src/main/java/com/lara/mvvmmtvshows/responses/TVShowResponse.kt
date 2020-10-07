package com.lara.mvvmmtvshows.responses

import com.google.gson.annotations.SerializedName
import com.lara.mvvmmtvshows.models.TVShow

data class TVShowResponse (

    @SerializedName("page")
    val page: Int,

    @SerializedName("pages")
    val totalPages: Int,

    @SerializedName("tv_shows")
    val tvShows: List<TVShow>

)