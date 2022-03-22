package com.example.moviesapp.presentation.trend_movies_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.databinding.FragmentTrendMoviesBinding
import com.example.moviesapp.domain.util.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrendMoviesFragment : Fragment() {

    private var _binding: FragmentTrendMoviesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TrendMoviesViewModel by viewModels()

    lateinit var trendMoviesAdapter: TrendMoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTrendMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewModel.getTrendMovies(viewModel.pageNumber)

        subscribeToLiveData()

        binding.trendMoviesList.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.getTrendMovies(viewModel.pageNumber + 1)
                }
            }
        })
    }

    private fun setupRecyclerView() {
        binding.trendMoviesList.apply {
            trendMoviesAdapter = TrendMoviesAdapter { movieId ->
                val action = TrendMoviesFragmentDirections.actionTrendMoviesFragmentToMovieDetailsFragment(movieId)

                findNavController().navigate(action)
            }

            trendMoviesAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            adapter = trendMoviesAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    private fun subscribeToLiveData() {
        viewModel.currentMoviesList.observe(viewLifecycleOwner) { resource ->

            when(resource.status) {
                Status.SUCCESS -> {

                    if(trendMoviesAdapter.currentList.size > 0) {
                        binding.newPageProgressBar.visibility = View.GONE
//                      val movies = trendMoviesAdapter.currentList + resource.data!!.movies
//                      trendMoviesAdapter.submitList(movies)

                        trendMoviesAdapter.addNewMovies(trendMoviesAdapter.currentList + resource.data!!.movies)
                    } else {
                        binding.progressBar.visibility = View.INVISIBLE
                        trendMoviesAdapter.submitList(resource.data!!.movies)
                    }
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    Toast.makeText(context, "${resource.errorMessage}", Toast.LENGTH_SHORT).show()
                }

                Status.LOADING -> {
                    if(trendMoviesAdapter.currentList.size > 0) {
                        binding.newPageProgressBar.visibility = View.VISIBLE
                    } else {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}