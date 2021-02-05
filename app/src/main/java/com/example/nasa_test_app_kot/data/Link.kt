package com.example.nasa_test_app_kot.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Link(
    @SerializedName("href") @Expose
    var href: String? = null
)