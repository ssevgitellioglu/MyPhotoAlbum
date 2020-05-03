package com.example.myphotoalbum.repo

import androidx.lifecycle.MutableLiveData
import com.example.myphotoalbum.model.RecentPhotoList
import com.example.myphotoalbum.service.PhotoServiceManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotoListRepository {
    private var list = MutableLiveData<RecentPhotoList>()

    constructor(){
        loadList(1)
    }

    //FIRST LOAD LIST
    fun loadList(page: Int){
        PhotoServiceManager.getInstance()?.getRecentPhotos("20", page.toString())?.enqueue(object :
            Callback<RecentPhotoList> {
            override fun onFailure(call: Call<RecentPhotoList>, t: Throwable) {
                list.postValue(null)
            }

            override fun onResponse(call: Call<RecentPhotoList>,response: Response<RecentPhotoList>) {
                if (response?.isSuccessful!!)
                    list.postValue(response?.body())
                else
                    list.postValue(null)
            }
        })
    }

    //APPEND NEW ITEMS TO LIST
    fun appendToList(page: Int){
        PhotoServiceManager.getInstance()?.getRecentPhotos("20", page.toString())?.enqueue(object :
            Callback<RecentPhotoList> {
            override fun onFailure(call: Call<RecentPhotoList>, t: Throwable) {
            }

            override fun onResponse(call: Call<RecentPhotoList>,response: Response<RecentPhotoList>) {
                if (response.isSuccessful){
                    val newList = list.value?.photos?.photo?.toMutableList()
                    response.body()!!.photos?.photo?.let { newList?.addAll(it) }
                    val p = list.value?.photos?.copy()
                    newList?.toList()?.let {
                        p?.photo = it
                    }

                    list.postValue(RecentPhotoList(p, list.value?.stat))
                }
                else
                    list.postValue(null)
            }
        })
    }

    fun getList(): MutableLiveData<RecentPhotoList> {
        return list
    }
}