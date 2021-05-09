package com.example.albertsonscodingchallenge.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.albertsonscodingchallenge.R
import com.example.albertsonscodingchallenge.model.AcronymMeaning
import com.example.albertsonscodingchallenge.repo.AcronymRepo
import com.example.albertsonscodingchallenge.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val app: Application) : AndroidViewModel(app) {
    private val _dataState = MutableLiveData<Resource<List<AcronymMeaning>>>()

    val dataState: LiveData<Resource<List<AcronymMeaning>>>
        get() = _dataState

    private var currentQuery: String? = null

    private val emptyMsg
        get() = currentQuery?.let {
            String.format(app.getString(R.string.extra_empty_results), currentQuery)
        } ?: app.getString(R.string.default_empty_results)

    private val <T>Response<T>.fancyErrorMsg
        get() = String.format(app.getString(R.string.rest_api_error), code(), message())

    fun fetchAcronym(shortForm: String) {
        currentQuery = shortForm
        viewModelScope.launch(Dispatchers.IO) {
            _dataState.postValue(Resource.Loading)
            AcronymRepo.getLongForm(shortForm).run {
                when (code()) {
                    200 -> {
                        body().run {
                            if (isNullOrEmpty()) Resource.Error(emptyMsg)
                            else Resource.Success(first().acronymMeanings)
                        }
                    }
                    else -> Resource.Error(fancyErrorMsg)
                }.let { _dataState.postValue(it) }
            }
        }
    }
}