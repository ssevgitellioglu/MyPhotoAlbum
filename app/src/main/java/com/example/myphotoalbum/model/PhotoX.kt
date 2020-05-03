package com.example.myphotoalbum.model

import com.google.gson.annotations.SerializedName

data class PhotoX(
    @SerializedName("id")
    val id: String?,
    @SerializedName("secret")
    val secret: String?,
    @SerializedName("server")
    val server: String?,
    @SerializedName("farm")
    val farm: Int?,
    @SerializedName("dateuploaded")
    val dateuploaded: String?,
    @SerializedName("isfavorite")
    val isfavorite: Int?,
    @SerializedName("owner")
    val owner:String?,
    @SerializedName("title")
    val title: String?)