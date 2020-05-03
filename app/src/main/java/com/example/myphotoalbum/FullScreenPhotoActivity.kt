package com.example.myphotoalbum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.myphotoalbum.model.Photo
import com.example.myphotoalbum.service.PhotoServiceManager
import com.squareup.picasso.Picasso

class FullScreenPhotoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen_photo)
        val p = intent.getSerializableExtra(Const.KEY_OBJECT) as Photo

        Picasso.get().load(PhotoServiceManager.getPhotoJPEG(p.server, p.secret, p.id))
            .into(findViewById<ImageView>(R.id.ivFullScreen))
        }
    }


