package com.example.nasa_test_app_kot.screens.nasa_news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nasa_test_app_kot.R
import com.example.nasa_test_app_kot.adapter.NasaAdapter
import kotlinx.android.synthetic.main.nasa_news_activity.*

class NasaNewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nasa_news_activity)
        val adapter = NasaAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


    }
}