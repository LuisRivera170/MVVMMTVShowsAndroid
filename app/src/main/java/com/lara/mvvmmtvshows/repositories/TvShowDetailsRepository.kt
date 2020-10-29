package com.lara.mvvmmtvshows.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lara.mvvmmtvshows.network.ApiClient
import com.lara.mvvmmtvshows.responses.TVShowDetailsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvShowDetailsRepository {

    fun getTvShowDetails(tvShowId: String): LiveData<TVShowDetailsResponse> {
        val data: MutableLiveData<TVShowDetailsResponse> = MutableLiveData()
        ApiClient.apiService.getTvShowDetail(tvShowId).enqueue(object: Callback<TVShowDetailsResponse> {
            override fun onResponse(call: Call<TVShowDetailsResponse>, response: Response<TVShowDetailsResponse>) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<TVShowDetailsResponse>, t: Throwable) {
                data.value = null
            }

        })
        return data
    }

}