package com.example.moviesapp.presentation.trend_movies_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviesapp.R
import com.example.moviesapp.databinding.MovieItemBinding
import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.presentation.util.Constants.IMAGE_BASE_URL

class TrendMoviesAdapter(
    private val click: (Int) -> Unit
) : ListAdapter<Movie, TrendMoviesAdapter.MovieViewHolder>(DiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding, click)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }

    fun addNewMovies(newList: List<Movie>) {
        submitList(currentList + newList)
    }

    inner class MovieViewHolder(
        private val binding: MovieItemBinding,
        private val click: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {


        fun bind(item: Movie) {
            binding.apply {
                movieName.text = item.title
                val year = item.release_date.split('-')[0]
                movieYear.text = year

                Glide.with(itemView)
                    .setDefaultRequestOptions(
                        RequestOptions()
                            .placeholder(R.drawable.ic_launcher_background)
                            .error(R.drawable.ic_launcher_background)
                    ).load(IMAGE_BASE_URL + item.poster_path).into(movieImage)

                root.setOnClickListener {
                    click.invoke(item.id)
                }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}