package com.example.mvvmwithhilt.ui.movie

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mvvmwithhilt.databinding.FragmentMovieBinding
import com.example.mvvmwithhilt.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieFragment : Fragment() {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!
    private val movieViewModel by viewModels<MovieViewModel>()
    private lateinit var  movieAdapter:  MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieViewModel.getAllMovies()
        bindObservers()
    }

    private fun bindObservers() {
        val mProgressDialog = ProgressDialog(requireContext())
        mProgressDialog.isIndeterminate = true
        mProgressDialog.setMessage("Loading...")
        mProgressDialog.show()
        movieViewModel.movieLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is NetworkResult.Success -> {
                    mProgressDialog.dismiss()
                    binding.rvMovie.layoutManager = GridLayoutManager(requireContext(),2)
                    movieAdapter = it.data?.let { it1 ->
                        MovieAdapter(requireContext(),
                            it1)
                    }!!
                    binding.rvMovie.adapter = movieAdapter
                }
                is NetworkResult.Error -> {
                    mProgressDialog.dismiss()
                    Toast.makeText(requireContext(), "" + it.message.toString(), Toast.LENGTH_LONG)
                        .show()
                }
            }
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}