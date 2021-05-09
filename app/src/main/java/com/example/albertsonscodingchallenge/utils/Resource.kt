package com.example.albertsonscodingchallenge.utils

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val errorMsg: String) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}