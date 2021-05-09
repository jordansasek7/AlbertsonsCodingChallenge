package com.example.albertsonscodingchallenge.model

import androidx.lifecycle.GeneratedAdapter
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Var(
    val freq: Int,
    @field:Json(name = "lf")
    val meaning: String,
    val since: Int
)