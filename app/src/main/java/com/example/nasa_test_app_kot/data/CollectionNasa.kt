package com.example.nasa_test_app_kot.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CollectionNasa (
    @SerializedName("items") @Expose
    var items: List<Item?>? = null,
    @SerializedName("version")
    @Expose
    val version: String? = null,

    @SerializedName("href")
    @Expose
    val href: String? = null
)