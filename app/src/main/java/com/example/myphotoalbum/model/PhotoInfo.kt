package com.example.myphotoalbum.model

import com.google.gson.annotations.SerializedName

data class PhotoInfo(
    @SerializedName("photo")
    val photo: PhotoX,
    @SerializedName("stat")
    val stat: String?
)