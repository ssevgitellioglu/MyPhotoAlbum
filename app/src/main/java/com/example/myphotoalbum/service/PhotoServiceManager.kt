package com.example.myphotoalbum.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOError

class PhotoServiceManager {
    companion object{
        private var instance: IPhotoService? = null

        fun getInstance(): IPhotoService? {
            if(instance == null) {
                instance = Retrofit.Builder()
                    .baseUrl(IPhotoService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(IPhotoService::class.java)
            }
            return instance
        }

        fun getPhotoJPEG(server: String?, secret: String?, id: String?): String{
            return "https://live.staticflickr.com/${server}/${id}_${secret}_c.jpg"
        }
    }
}