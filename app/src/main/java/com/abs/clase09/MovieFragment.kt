package com.abs.clase09

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.abs.clase09.dummy.DummyContent
import com.abs.clase09.dummy.DummyContent.DummyItem

class MovieFragment : Fragment() {

    private var movies = listOf<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            movies = it.getParcelableArrayList<Movie>(MOVIES)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = MyMovieRecyclerViewAdapter(movies)
            }
        }
        return view
    }


    companion object {

        const val MOVIES = "movies"

        fun newInstance(movies: ArrayList<Movie>) =
            MovieFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(MOVIES,movies)
                }
            }
    }
}
