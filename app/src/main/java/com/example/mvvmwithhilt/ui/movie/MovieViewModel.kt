package com.example.mvvmwithhilt.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmwithhilt.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
) : ViewModel() {

    val movieLiveData get() = movieRepository.notesLiveData

    fun getAllMovies() {
        viewModelScope.launch {
            movieRepository.getMovie()
        }
    }

}