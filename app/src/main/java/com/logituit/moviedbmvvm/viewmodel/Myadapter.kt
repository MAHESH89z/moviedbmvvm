package com.logituit.moviedbmvvm.viewmodel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.logituit.moviedbmvvm.R
import com.logituit.moviedbmvvm.databinding.ItemViewBinding
import com.logituit.moviedbmvvm.models.Result


class Myadapter(val context: Context, val MoviesList: ArrayList<Result>, private val onClick: (Result) -> Unit) :RecyclerView.Adapter<Myadapter.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_view, parent, false)
        return MyViewHolder(view,onClick)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(MoviesList[position])

//        val movies = MoviesList[position]
//        holder.name.text=movies.title
//        Glide.with(context).load("https://image.tmdb.org/t/p/w500"+movies.poster_path).into(holder.poster)

    }

    override fun getItemCount(): Int {
        return MoviesList.size
    }
    class MyViewHolder(itemView: View,val onClick: (Result) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemViewBinding.bind(itemView)
        private var currentMovieDetails: Result? = null
        val poster=binding.image
       // val poster: ImageView = itemView.findViewById(R.id.image)
       // val name: TextView = itemView.findViewById(R.id.text)
       init {
           binding.text.setOnClickListener{
currentMovieDetails?.let{
    onClick(it)
}
           }
               }

       fun bind(movieDetails: Result) {
           currentMovieDetails = movieDetails
           binding.text.text = movieDetails.title
           Glide.with(binding.image.context)
               .load("https://image.tmdb.org/t/p/w500"+movieDetails.poster_path)
               .into(poster)
       }
    }
    fun addData1(list: List<Result>) =
        MoviesList.addAll(list)

    fun clearData1() =
        MoviesList.clear()
}