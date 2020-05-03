package com.example.myphotoalbum


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.myphotoalbum.adapter.PhotoAdapter
import com.example.myphotoalbum.model.Photo
import com.example.myphotoalbum.viewmodel.PhotoListViewModel
import kotlinx.android.synthetic.*

class MainActivity : AppCompatActivity() {
   private lateinit var  customAdapter: PhotoAdapter
    var recentList: PhotoListViewModel=PhotoListViewModel(1)
    private lateinit var rvPhoto: RecyclerView
    var currentPage = 1
    private lateinit var refreshLayout: SwipeRefreshLayout
    var photoList:MutableList<Photo> = mutableListOf()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

      rvPhoto = findViewById<RecyclerView>(R.id.rvPhoto)

      findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
      refreshLayout = findViewById(R.id.refresherSR)
      refreshLayout.setOnRefreshListener {
          refreshLayout.isRefreshing = true
          currentPage = 1
          recentList.getPage(currentPage) }

      customAdapter= PhotoAdapter(applicationContext,photoList)

      recentList = PhotoListViewModel(currentPage)
      recentList.getList().observe(this, Observer {
          //check first SwipeRefreshLayout
          if (refreshLayout.isRefreshing){
             it.photos?.photo?.let {list->
                 customAdapter= PhotoAdapter(applicationContext, list?.toMutableList())
             }
              rvPhoto.adapter = customAdapter
              refreshLayout.isRefreshing = false
              customAdapter.notifyDataSetChanged()
          }else{ // if null initialized adapter

              it.photos?.photo?.let {list->
                  customAdapter= PhotoAdapter(applicationContext, list?.toMutableList())
              }
              rvPhoto.adapter = customAdapter
              customAdapter.notifyDataSetChanged()
              customAdapter.notifier = customAdapterNotifier
              findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
          }
      })
      findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE

      rvPhoto.layoutManager = GridLayoutManager(this, 2)
  }


  private fun startFullScreenActivity(item: Photo) {
    var i = Intent(this, FullScreenPhotoActivity::class.java)
    i.putExtra(Const.KEY_OBJECT, item)
    i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    startActivity(i)
  }


  var customAdapterNotifier = object : PhotoAdapter.RecyclerViewNotifier {
    override fun onClick(item: Photo) {
      startFullScreenActivity(item)
    }

    override fun onListEnd() {
      currentPage++
      recentList.getPage(currentPage)
      findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
      Toast.makeText(applicationContext, "Page: $currentPage", Toast.LENGTH_SHORT).show()
    }
  }

}
