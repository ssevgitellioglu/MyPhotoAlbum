package com.example.myphotoalbum.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.myphotoalbum.R
import com.example.myphotoalbum.model.Photo
import com.example.myphotoalbum.service.PhotoServiceManager
import com.squareup.picasso.Picasso

class PhotoAdapter(private val context: Context, private val list: MutableList<Photo>): RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {
     var notifier: RecyclerViewNotifier?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.photo_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bindItem(list[position])
        if (position == list.size - 1)
            notifier?.onListEnd()
    }


    inner class PhotoViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var photo = v.findViewById<ImageView>(R.id.ivPhoto)

        fun bindItem(item: Photo) {
            Picasso.get()?.load(PhotoServiceManager.getPhotoJPEG(item.server, item.secret, item.id))
                ?.into(photo)

            photo.setOnClickListener {
                notifier?.onClick(item)
            }
        }
    }

    interface RecyclerViewNotifier {
        fun onListEnd()
        fun onClick(item: Photo)
    }
}