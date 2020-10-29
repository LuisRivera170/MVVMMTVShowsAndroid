package com.lara.mvvmmtvshows.network

import com.lara.mvvmmtvshows.responses.TVShowDetailsResponse
import com.lara.mvvmmtvshows.responses.TVShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("most-popular")
    fun getMostPopularTvShows(@Query("page") page: Int): Call<TVShowResponse>

    @GET("show-details")
    fun getTvShowDetail(@Query("q") tvShowId: String): Call<TVShowDetailsResponse>

}