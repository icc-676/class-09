package com.abs.clase09

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import com.abs.clase09.dummy.DummyContent
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val movies = mutableListOf<Movie>()
    val titles = arrayOf(
        "American",
        "Iron Man",
        "Toy story",
        "Cars"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            reloadMovies()
            //Este esta aqui para que espere la respuesta de la consulta
            //OJO ES MUY MALA PRACTICA SOLO PARA DAR EL EJEMPLO
            Thread.sleep(1_000)
            supportFragmentManager.beginTransaction()
                .add(
                    R.id.root_layout,
                    MovieFragment.newInstance(movies = ArrayList(movies)),
                    "movies"
                )
                .commit()
            //Ejemplo de Picasso
//            supportFragmentManager.beginTransaction()
//                .add(R.id.root_layout, PicassoExample.newInstance(), "piccaso")
//                .commit()
        }
        //No se utiliza ya que carga solo
//        reload_list.setOnClickListener {
//
//        }
    }

    private fun reloadMovies() {
        titles.forEach {
           Fuel.get("http://www.omdbapi.com/?t=${it}&apikey=7c4a7e7")
                .responseObject(Movie.Deserializer()) {request, response, result ->
                    val (movie, error) = result
                    if (movie != null) {
                        println(movie)
                        movies.add(movie)
                    }
                }
        }
    }
}

@Parcelize
data class Movie(
    val Title: String,
    val Poster: String
):Parcelable {
    class Deserializer : ResponseDeserializable<Movie> {
        override fun deserialize(content: String): Movie? =
            Gson().fromJson(content, Movie::class.java)
    }
}