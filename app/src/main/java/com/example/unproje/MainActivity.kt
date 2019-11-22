package com.example.unproje

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback

class MainActivity : AppCompatActivity() {

    private var arraylistmovies: MutableList<Movies> = arrayListOf()
    private lateinit var repository: Repository
    private var moviesAdapter: MovieAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repository = Repository(ApiClient.getClient()!!.create(ApiService::class.java))

        loadMovies()
    }

    val itemTouchHelperCallback=object:ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT){
        override fun onMove(
            p0: RecyclerView,
            p1: RecyclerView.ViewHolder,
            p2: RecyclerView.ViewHolder
        ): Boolean {
                return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, position: Int) {

            moviesAdapter!!.removeItemfromList(viewHolder)
        }

    }

    fun loadMovies() {

        try {
            repository.getPopularMovies().enqueue(object : Callback<MovieResponse> {
                override fun onResponse(call: Call<MovieResponse>, response: retrofit2.Response<MovieResponse>) {
                    if (response.isSuccessful && response.body() != null) {
                        arraylistmovies.addAll(response.body()!!.results!!)
                        recylerView_main.layoutManager =
                            LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
                        moviesAdapter = MovieAdapter(arraylistmovies)
                        recylerView_main.adapter = moviesAdapter
                        recylerView_main.smoothScrollToPosition(0)
                    }

                }

                override fun onFailure(call: retrofit2.Call<MovieResponse>, t: Throwable) {
                    //showError("Error")
                    recylerView_main.visibility = View.INVISIBLE
                    recylerView_main.visibility = View.GONE
                }
            })
        } catch (e: Exception) {
            //showError("Error")
        }
    }
}
