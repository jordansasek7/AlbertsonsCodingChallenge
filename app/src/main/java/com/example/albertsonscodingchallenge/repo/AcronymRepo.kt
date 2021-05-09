package com.example.albertsonscodingchallenge.repo

import com.example.albertsonscodingchallenge.model.AcronymItem
import com.example.albertsonscodingchallenge.model.AcronymMeaning
import com.example.albertsonscodingchallenge.repo.remote.AcronymRetrofitInstance
import retrofit2.Response

object AcronymRepo {

    suspend fun getLongForm(shortForm:String): Response<List<AcronymItem>> = AcronymRetrofitInstance.acronymService.getLongForm(shortForm)
}