package com.abs.clase09

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_piccaso_example.*


class PicassoExample : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_piccaso_example, container, false)
        val imageView = view.findViewById<ImageView>(R.id.picasso_image_view)
        Picasso.get()
            .load("http://i.imgur.com/DvpvklR.png")
            .into(imageView)
        return view
    }

    companion object {
        fun newInstance() = PicassoExample()
    }
}
