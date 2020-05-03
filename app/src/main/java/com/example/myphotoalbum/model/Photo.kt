package com.example.myphotoalbum.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Photo(
    @SerializedName("id")
    val id: String?,
    @SerializedName("owner")
    val owner: String?,
    @SerializedName("secret")
    val secret: String?,
    @SerializedName("server")
    val server: String?,
    @SerializedName("farm")
    val farm: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("ispublic")
    val ispublic: Int?,
    @SerializedName("isfriend")
    val isfriend: Int?,
    @SerializedName("isfamily")
    val isfamily: Int?
): Serializable