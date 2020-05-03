package com.example.myphotoalbum.service

import com.example.myphotoalbum.model.PhotoInfo
import com.example.myphotoalbum.model.RecentPhotoList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IPhotoService {

    companion object{
        public const val BASE_URL = "https://api.flickr.com/"
        private const val API_KEY = "3352b5819a104fa87f37b17206b2fbc9" // secret =ab4c698bb28baf21
        private const val FORMAT = "json"

        //
    }


    @GET("services/rest")
    fun getRecentPhotos(@Query("per_page")perPage: String,
                        @Query("page")page: String,
                        @Query("api_key")api: String = API_KEY,
                        @Query("method") method: String = "flickr.photos.getRecent",
                        @Query("format")format: String = FORMAT,
                        @Query("nojsoncallback")nojsoncallback: Int = 1): Call<RecentPhotoList>

//    @GET("services/rest")
//    fun getInfo(@Query("photo_id")id: String,
//                @Query("api_key")api: String = API_KEY,
//                @Query("method") method: String = "flickr.photos.getInfo",
//                @Query("format")format: String = FORMAT,
//                @Query("nojsoncallback")nojsoncallback: Int = 1): Call<PhotoInfo>
}