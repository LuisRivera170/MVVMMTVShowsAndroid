package com.lara.mvvmmtvshows.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
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

    @SerializedName("status")
    val status: String,

    @SerializedName("image_thumbnail_path")
    val thumbnail: String,

): Parcelable