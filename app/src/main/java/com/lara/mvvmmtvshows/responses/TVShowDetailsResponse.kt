package com.lara.mvvmmtvshows.responses

import com.google.gson.annotations.SerializedName
import com.lara.mvvmmtvshows.models.TVShowDetails

data class TVShowDetailsResponse (

    @SerializedName("tvShow")
    val tvShowDetails: TVShowDetails

)