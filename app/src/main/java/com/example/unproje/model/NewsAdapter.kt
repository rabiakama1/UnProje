package com.example.unproje.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.unproje.R
import kotlinx.android.synthetic.main.movie_item.view.*


class NewsAdapter(var newsList:MutableList<News>): RecyclerView.Adapter<NewsAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news=newsList[position]
        holder.bindTo(news)
    }

    fun removeItem(position: Int) {
        newsList.removeAt(position)
        notifyItemRemoved(position)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val titletxt by lazy { itemView.findViewById<TextView>(R.id.titleTextView) }
        val desctxt by lazy { itemView.findViewById<TextView>(R.id.txtDesc) }

        fun bindTo(nw: News) {

            titletxt.text = nw.getTitle()
            desctxt.text = nw.getDesc()

            Glide.with(itemView.context)
                .load(nw.getPos())
                .placeholder(R.drawable.abc_ic_go_search_api_material)
                //.thumbnail(Glide.with(itemView.context).load(R.drawable.abc_ic_go_search_api_material))
                .into(itemView.posterImageView)

        }
    }

}
