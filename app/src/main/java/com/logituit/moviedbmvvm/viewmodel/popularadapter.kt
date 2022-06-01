package com.logituit.moviedbmvvm.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.logituit.moviedbmvvm.R
import com.logituit.moviedbmvvm.databinding.ItemViewBinding
import com.logituit.moviedbmvvm.models.Result
import javax.inject.Inject

class popularadapter @Inject constructor (private val onClick: (Result) -> Unit): PagingDataAdapter<Result, popularadapter.MyViewHolder>(DiffUtilCallBack()) {


    class MyViewHolder(itemView: View,val onClick: (Result) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemViewBinding.bind(itemView)
        private var currentMovieDetails: Result? = null
        val poster=binding.image
        // val poster: ImageView = itemView.findViewById(R.id.image)
        // val name: TextView = itemView.findViewById(R.id.text)
        init {
            binding.container.setOnClickListener{
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

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        getItem(position)?.let { Result->holder.bind(Result) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater=
            LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)
        return MyViewHolder(inflater,onClick)
    }

}

    class DiffUtilCallBack: DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.title==newItem.title
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.title==newItem.title
        }

    }
