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
import com.logituit.mvvm.models.ResultX
import javax.inject.Inject

class upcomingadapter @Inject constructor(private val onClick: (ResultX) -> Unit):PagingDataAdapter<ResultX,upcomingadapter.MyViewHolder>(diffCallback) {
    class MyViewHolder(itemView: View,val onClick: (ResultX) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemViewBinding.bind(itemView)
        private var currentMovieDetails: ResultX? = null
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
        fun bind(movieDetails: ResultX) {
            currentMovieDetails = movieDetails
            binding.text.text = movieDetails.title
            Glide.with(binding.image.context)
                .load("https://image.tmdb.org/t/p/w500"+movieDetails.poster_path)
                .into(poster)
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        getItem(position)?.let { ResultX->holder.bind(ResultX) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater=
            LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)
        return MyViewHolder(inflater,onClick)
    }
}

object diffCallback : DiffUtil.ItemCallback<ResultX>() {
    override fun areItemsTheSame(oldItem: ResultX, newItem: ResultX): Boolean {
        return oldItem.title==newItem.title
    }

    override fun areContentsTheSame(oldItem: ResultX, newItem: ResultX): Boolean {
        return oldItem.title==newItem.title
    }


}
