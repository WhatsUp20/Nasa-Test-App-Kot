package com.example.nasa_test_app_kot.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nasa_test_app_kot.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_nasa_detail.*

class NasaDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_IMAGE = "image"
        const val EXTRA_TITLE = "title"
        const val EXTRA_DESCRIPTION = "desc"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nasa_detail)

        val intent = intent
        if (intent != null && intent.hasExtra(EXTRA_IMAGE) && intent.hasExtra(EXTRA_TITLE) && intent.hasExtra(EXTRA_DESCRIPTION)) {
            val image = intent.getStringExtra(EXTRA_IMAGE)
            val title = intent.getStringExtra(EXTRA_TITLE)
            val desc = intent.getStringExtra(EXTRA_DESCRIPTION)
            Picasso.get().load(image).into(imageViewToDetails)
            textViewTitle.text = title
            textViewDescription.text = desc
        } else {
            finish()
        }
    }
}