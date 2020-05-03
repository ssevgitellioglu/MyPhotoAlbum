package com.example.myphotoalbum.model

import com.google.gson.annotations.SerializedName

data class RecentPhotoList(
    @SerializedName("photos")
    var photos: Photos?,
    @SerializedName("stat")
    val stat: String?
)