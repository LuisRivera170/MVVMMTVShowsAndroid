package com.lara.mvvmmtvshows.models

import com.google.gson.annotations.SerializedName

data class TVShow(

    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("start_date")
    val startDate: String,

    @SerializedName("country")
    val country: String,

    @SerializedName("network")
    val network: String,

    @SerializedName("image_thumbnail_path")
    val thumbnail: String,

)