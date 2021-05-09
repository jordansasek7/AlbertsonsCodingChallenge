package com.example.albertsonscodingchallenge.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Lf(
    val freq: Int,
    @field:Json(name = "lf")
    val meaning: String,
    val since: Int,
    val vars: List<Var>
)