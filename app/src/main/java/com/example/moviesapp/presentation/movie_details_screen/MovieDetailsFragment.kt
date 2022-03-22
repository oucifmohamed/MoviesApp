package com.example.moviesapp.presentation.movie_details_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import com.example.moviesapp.databinding.FragmentMovieDetailsBinding
import com.example.moviesapp.domain.util.Status
import com.example.moviesapp.presentation.util.Constants.IMAGE_BASE_URL
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: MovieDetailsFragmentArgs by navArgs()
    private val viewModel: MovieDetailsViewModel by viewModels()

    @Inject
    lateinit var glide: RequestManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieId = args.movieId
        viewModel.getMovieDetails(movieId)

        subscribeToLiveData()
    }

    private fun subscribeToLiveData() {
        viewModel.movieDetails.observe(viewLifecycleOwner) { resource ->

            when(resource.status) {
                Status.SUCCESS -> {
                    binding.apply {
                        progressbar.visibility = View.INVISIBLE
                        glide.load(IMAGE_BASE_URL + resource.data!!.poster_path).into(movieImage)
                        movieName.text = resource.data.title
                        movieYear.text = resource.data.release_date.split("-")[0]
                        duration.text = "Duration: ${resource.data.runtime}"
                        rate.text = "Rating: ${resource.data.vote_average}/10"
                        adult.text = if(resource.data.adult) {
                            "Not appropriate for kids."
                        } else {
                            "Appropriate for kids."
                        }

                        overview.text = resource.data.overview
                    }
                }
                Status.ERROR -> {
                    binding.progressbar.visibility = View.INVISIBLE
                    Toast.makeText(context, "${resource.errorMessage}", Toast.LENGTH_SHORT).show()
                }
                Status.LOADING -> {
                    binding.progressbar.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}