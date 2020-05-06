package com.abs.clase09

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.abs.clase09.dummy.DummyContent.DummyItem
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.fragment_movie.view.*

class MyMovieRecyclerViewAdapter(
    private val mValues: List<Movie>
) : RecyclerView.Adapter<MyMovieRecyclerViewAdapter.ViewHolder>() {


    init {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.binMoview(item)
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        lateinit var view: View
        init{
            this.view = mView
        }
        fun binMoview(item: Movie){
            view.movie_title.text = item.Title
            val imageView = view.movie_poster
            Picasso.get()
                .load(item.Poster)
                .into(imageView)
        }
    }
}
