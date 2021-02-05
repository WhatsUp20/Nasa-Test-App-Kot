package com.example.nasa_test_app_kot.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ObjectCollection(
    @SerializedName("collection") @Expose
    var collection: CollectionNasa? = null
)