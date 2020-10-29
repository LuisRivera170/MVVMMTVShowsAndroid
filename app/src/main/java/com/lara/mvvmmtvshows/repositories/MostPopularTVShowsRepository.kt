package com.lara.mvvmmtvshows.repositories

import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lara.mvvmmtvshows.network.ApiClient
import com.lara.mvvmmtvshows.responses.TVShowResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MostPopularTVShowsRepository {

    fun getMostPopularTVShows(page: Int): LiveData<TVShowResponse> {
        val data: MutableLiveData<TVShowResponse> = MutableLiveData()
        ApiClient.apiService.getMostPopularTvShows(page).enqueue(object: Callback<TVShowResponse> {
            override fun onResponse(@NonNull call: Call<TVShowResponse>, @NonNull response: Response<TVShowResponse>) {
                data.value = response.body()
            }

            override fun onFailure(@NonNull call: Call<TVShowResponse>, @NonNull t: Throwable) {
                data.value = null
            }

        })
        return data
    }

}