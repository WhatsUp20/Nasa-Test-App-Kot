package com.example.nasa_test_app_kot.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Datum(
    @SerializedName("nasa_id")
    @Expose
    var nasaId: String? = null,

    @SerializedName("title")
    @Expose
    val title: String? = null,

    @SerializedName("description")
    @Expose
    val description: String? = null,

    @SerializedName("keywords")
    @Expose
    val keywords: List<String>? = null,

    @SerializedName("center")
    @Expose
    val center: String? = null,

    @SerializedName("date_created")
    @Expose
    val dateCreated: String? = null,

    @SerializedName("media_type")
    @Expose
    val mediaType: String? = null,

    @SerializedName("description_508")
    @Expose
    val description508: String? = null
)