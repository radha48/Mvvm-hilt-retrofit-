package com.example.mvvmwithhilt.ui.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmwithhilt.databinding.MovieItemBinding
import com.example.mvvmwithhilt.models.Movie
import com.example.mvvmwithhilt.models.Results
import com.example.mvvmwithhilt.utils.Constants

class MovieAdapter(val context: Context, val movieList: Movie) :
    RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movieResult = movieList.results!!.get(position)
        movieResult.let {
            holder.bind(it)
        }
    }

    inner class MyViewHolder(private val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movieResult: Results) {
            binding.tvTitle.text = movieResult.title

            Glide
                .with(context)
                .load(Constants.IMAGE_URL+ movieResult.poster_path)
                .fitCenter()
                .into(binding.ivPoster);
        }

    }

    override fun getItemCount(): Int {
        return movieList.results!!.size
    }
}