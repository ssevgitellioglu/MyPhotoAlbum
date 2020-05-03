package com.example.myphotoalbum.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myphotoalbum.model.Photo
import com.example.myphotoalbum.model.RecentPhotoList
import com.example.myphotoalbum.repo.PhotoListRepository

class PhotoListViewModel: ViewModel {
    private var photoList = PhotoListRepository()

    constructor(page: Int = 1){
        photoList.loadList(page)
    }

    fun getPage(page: Int){
        photoList.loadList(page)
    }

    fun getList(): MutableLiveData<RecentPhotoList> {
        return photoList.getList()
    }

    fun getItemById(id: String): Photo? {
        return photoList.getList().value?.photos?.photo?.find { it.id == id }
    }
}