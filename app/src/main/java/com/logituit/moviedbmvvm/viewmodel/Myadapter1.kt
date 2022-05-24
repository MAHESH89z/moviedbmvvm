package com.logituit.moviedbmvvm.viewmodel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.logituit.moviedbmvvm.R
import com.logituit.moviedbmvvm.databinding.ItemViewBinding
import com.logituit.mvvm.models.Result
import com.logituit.mvvm.models.ResultX

class Myadapter1(val context: Context, val MoviesList: ArrayList<ResultX>) : RecyclerView.Adapter<Myadapter1.MyViewHolder>(){
lateinit var binding: ItemViewBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myadapter1.MyViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_view, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: Myadapter1.MyViewHolder, position: Int) {
        holder.bind(MoviesList[position])
//        val movies = MoviesList[position]
//        holder.name.text=movies.title
//        Glide.with(context).load("https://image.tmdb.org/t/p/w500"+movies.poster_path).into(holder.poster)

    }

    override fun getItemCount(): Int {
        return MoviesList.size
    }
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var binding=ItemViewBinding.bind(itemView)
        private var currentMovieDetails: ResultX? = null
        val poster=binding.image
        val name=binding.text
       // val poster: ImageView = itemView.findViewById(R.id.image)
        //val name: TextView = itemView.findViewById(R.id.text)
       fun bind(movieDetails: ResultX) {
           currentMovieDetails = movieDetails
           binding.text.text = movieDetails.title
           Glide.with(binding.image.context)
               .load("https://image.tmdb.org/t/p/w500"+movieDetails.poster_path)
               .into(poster)
       }
    }
    fun addData(list: List<ResultX>) =
        MoviesList.addAll(list)

    fun clearData() =
        MoviesList.clear()

}