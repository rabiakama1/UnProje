package com.example.unproje

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.AdapterView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.*
import com.example.unproje.client.ApiClient
import com.example.unproje.data.Repository
import com.example.unproje.model.News
import com.example.unproje.model.NewsAdapter
import com.example.unproje.service.ApiService
import com.example.unproje.model.NewsResponse
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback

class MainActivity : AppCompatActivity() {

    private lateinit var repository: Repository
    private var arraylistNews: ArrayList<News> = arrayListOf()
    private var newsAdapter: NewsAdapter? = null

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repository =
            Repository(ApiClient.getClient()!!.create(ApiService::class.java))

        var mLayoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        recylerView_main.layoutManager = mLayoutManager

        if (savedInstanceState != null) {
            displayData()
        } else {
            initViews()
        }
        loadNews()

        val swipeHandler = object : SwipeforDeleteCallback(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = recylerView_main.adapter as NewsAdapter
                adapter.removeItem(viewHolder.adapterPosition)
                Snackbar.make(
                    layout,
                    "Haber listeden silindi!",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recylerView_main)
    }

    private fun displayData() {

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            recylerView_main.layoutManager = GridLayoutManager(this, 2)
        } else {
            recylerView_main.layoutManager = GridLayoutManager(this, 4)
        }
        recylerView_main.itemAnimator = DefaultItemAnimator()
        restoreLayoutManagerPosition()
        recylerView_main.adapter = newsAdapter
        newsAdapter?.notifyDataSetChanged()

    }

    private fun restoreLayoutManagerPosition() {
        val savedRecyclerLayoutState: Parcelable? = null
        if (savedRecyclerLayoutState != null) {
            recylerView_main.layoutManager?.onRestoreInstanceState(savedRecyclerLayoutState)
        }
    }

    private fun initViews() {
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            recylerView_main.layoutManager = GridLayoutManager(this, 2)

        } else {
            recylerView_main.layoutManager = GridLayoutManager(this, 4)
        }
        recylerView_main.itemAnimator = DefaultItemAnimator()
        loadNews()
    }


    fun loadNews() {

        try {
            repository.getNews().enqueue(object : Callback<NewsResponse> {
                val recyclerView=findViewById<RecyclerView>(R.id.recylerView_main)
                override fun onResponse(call: Call<NewsResponse>, response: retrofit2.Response<NewsResponse>) {
                    if (response.isSuccessful && response.body() != null) {
                        arraylistNews.addAll(response.body()!!.totalResults!!)
                        recyclerView.layoutManager =
                            LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
                        newsAdapter = NewsAdapter(arraylistNews)
                        recyclerView.adapter = newsAdapter
                        recyclerView.smoothScrollToPosition(0)
                    }

                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    showError("Hata")

                }
            })
        } catch (e: Exception) {
            showError("Hata")
        }
    }

    private fun showError(s: String) {

        Toast.makeText(this,"Hata.",Toast.LENGTH_SHORT).show()
    }

}

