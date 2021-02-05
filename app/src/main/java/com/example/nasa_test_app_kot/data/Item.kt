package com.example.nasa_test_app_kot.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Item (
    @SerializedName("data")
    @Expose
    val data: List<Datum>? = null,

    @SerializedName("links")
    @Expose
    val links: List<Link>? = null
)