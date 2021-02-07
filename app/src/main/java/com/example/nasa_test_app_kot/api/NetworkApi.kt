package com.example.nasa_test_app_kot.api

import com.example.nasa_test_app_kot.data.ObjectCollection
import io.reactivex.Single
import retrofit2.http.GET

interface NetworkApi {
    @GET("search?q=space&media_type=image&year_start=2021&year_end=2021")
    fun getAllSpaceCollections(): Single<ObjectCollection>

    @GET("search?q=mars&media_type=image&year_start=2021&year_end=2021")
    fun getAllMarsCollection(): Single<ObjectCollection>
}