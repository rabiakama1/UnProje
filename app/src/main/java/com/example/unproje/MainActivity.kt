package com.example.unproje

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.*
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

        repository = Repository(ApiClient.getClient()!!.create(ApiService::class.java))

        var mLayoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        recylerView_main.layoutManager = mLayoutManager

        if (savedInstanceState != null) {
            displayData()
        } else {
            initViews()
        }
        loadNews()
    }

/*    val itemTouchHelperCallback=object:ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT){
        override fun onMove(
            p0: RecyclerView,
            p1: RecyclerView.ViewHolder,
            p2: RecyclerView.ViewHolder
        ): Boolean {
                return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, position: Int) {

            newsAdapter!!.removeItemfromList(viewHolder)
        }

    }*/

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
                    //showError("Error")

                }
            })
        } catch (e: Exception) {
            //showError("Error")
        }
    }

    }

