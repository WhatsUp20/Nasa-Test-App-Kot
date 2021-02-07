package com.example.nasa_test_app_kot.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nasa_test_app_kot.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_nasa_detail.*

class NasaDetailActivity : AppCompatActivity() {

    private val EXTRA_IMAGE1 = "image"
    private val EXTRA_TITLE1 = "title"
    private val EXTRA_DESCRIPTION1 = "desc"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nasa_detail)

        val intent = intent
        if (intent != null && intent.hasExtra(EXTRA_IMAGE1) && intent.hasExtra(EXTRA_TITLE1) && intent.hasExtra(EXTRA_DESCRIPTION1)) {
            val image = intent.getStringExtra(EXTRA_IMAGE1)
            val title = intent.getStringExtra(EXTRA_TITLE1)
            val desc = intent.getStringExtra(EXTRA_DESCRIPTION1)
            Picasso.get().load(image).into(imageViewToDetails)
            textViewTitle.text = title
            textViewDescription.text = desc
        } else {
            finish()
        }
    }
}