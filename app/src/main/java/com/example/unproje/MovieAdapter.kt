package com.example.unproje

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieAdapter(var movielist:MutableList<Movies>): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var removedItemPosition=0
    private var removedItem=""


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movielist.size
    }

    override fun onBindViewHolder(holder: MovieAdapter.ViewHolder, position: Int) {
        val movie=movielist[position]
        holder.bindTo(movie)
    }

    fun removeItemfromList(viewholder: RecyclerView.ViewHolder){

        removedItemPosition=viewholder.adapterPosition
        removedItem= movielist[viewholder.adapterPosition].toString()
        movielist.removeAt(viewholder.adapterPosition)
        notifyItemRemoved(viewholder.adapterPosition)
        Snackbar.make(viewholder.itemView,"$removedItem listeden silindi.",Snackbar.LENGTH_SHORT).setAction("OK"){
            movielist.add(removedItemPosition,removedItem as Movies)
            notifyItemInserted(removedItemPosition)
        }.show()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val titletxt by lazy { itemView.findViewById<TextView>(R.id.titleTextView) }
        //val voteaverage by lazy { itemView.findViewById<TextView>(R.id.voteaverageitem) }
        val releasetxt by lazy { itemView.findViewById<TextView>(R.id.releaseDateTextView) }
        val posterBasePath = "https://image.tmdb.org/t/p/w500/"

        fun bindTo(mv: Movies) {

            titletxt.text = mv.getTitle()
            releasetxt.text = mv.getReleaseDate()
           // voteaverage.text = mv.getVoteAverage().toString()

            Glide.with(itemView.context)
                .load(posterBasePath + mv.getPosterPath())
                .placeholder(R.drawable.abc_ic_go_search_api_material)
                //.thumbnail(Glide.with(itemView.context).load(R.drawable.abc_ic_go_search_api_material))
                .into(itemView.posterImageView)

        }
    }
}