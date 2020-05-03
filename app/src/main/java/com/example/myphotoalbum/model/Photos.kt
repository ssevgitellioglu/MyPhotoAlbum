package com.example.myphotoalbum.model

import com.google.gson.annotations.SerializedName

data class Photos(
    @SerializedName("page")
    var page: Int?,
    @SerializedName("pages")
    val pages: Int?,
    @SerializedName("perpage")
    val perpage: Int?,
    @SerializedName("total")
    val total: Int?,
    @SerializedName("photo")
    var photo: List<Photo>
)