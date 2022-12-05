package com.example.mvvmwithhilt.models

data class Movie(
    val results: List<Results>?
)
data class Results(
    val title: String,
    val poster_path: String,
)

