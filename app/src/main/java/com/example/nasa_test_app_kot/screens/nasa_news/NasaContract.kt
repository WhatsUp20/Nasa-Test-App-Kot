package com.example.nasa_test_app_kot.screens.nasa_news

import com.example.nasa_test_app_kot.data.Datum
import com.example.nasa_test_app_kot.data.Link

interface NasaContract {
    fun showDatumDataFromPresenter(datumList: List<Datum?>?)
    fun showListDataFromPresenter(linkList: List<Link?>?)
    fun showProgressBar()
    fun notShowProgressBar()
}