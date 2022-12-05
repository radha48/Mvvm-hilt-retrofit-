package com.example.mvvmwithhilt.repository

import androidx.lifecycle.MutableLiveData
import com.example.mvvmwithhilt.api.APiService
import com.example.mvvmwithhilt.models.Movie
import com.example.mvvmwithhilt.utils.Constants
import com.example.mvvmwithhilt.utils.NetworkResult
import org.json.JSONObject
import javax.inject.Inject

class MovieRepository  @Inject constructor(private val apiService: APiService) {

    private val _notesLiveData = MutableLiveData<NetworkResult<Movie>>()
    val notesLiveData get() = _notesLiveData


    suspend fun getMovie(){
        _notesLiveData.postValue(NetworkResult.Loading())
        val response = apiService.getMovie(Constants.API_KEY)
        if (response.isSuccessful && response.body() != null) {
            _notesLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _notesLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
        } else {
            _notesLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
        }
    }

}