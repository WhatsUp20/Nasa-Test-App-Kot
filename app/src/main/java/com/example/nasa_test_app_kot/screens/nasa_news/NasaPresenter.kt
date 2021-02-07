package com.example.nasa_test_app_kot.screens.nasa_news

import android.content.Context
import android.widget.Toast
import com.example.nasa_test_app_kot.api.NetworkService
import com.example.nasa_test_app_kot.data.Datum
import com.example.nasa_test_app_kot.data.Item
import com.example.nasa_test_app_kot.data.Link
import com.example.nasa_test_app_kot.data.ObjectCollection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NasaPresenter(private var contract: NasaContract?) {

    private val compositeDisposable = CompositeDisposable()

    fun loadSpaceData() {
        val disposable = NetworkService.networkApi.getAllSpaceCollections()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callToGetAllDataFromLists(it)
            }, {
                Toast.makeText(contract as Context?, "Error load data: " + it.message, Toast.LENGTH_SHORT).show()
            })
        compositeDisposable.add(disposable)
    }

     fun loadMarsData() {
        val disposable = NetworkService.networkApi.getAllMarsCollection()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callToGetAllDataFromLists(it)
            }, {
                Toast.makeText(contract as Context?, "Error load data: " + it.message, Toast.LENGTH_SHORT).show()
            })
        compositeDisposable.add(disposable)
    }

    private fun callToGetAllDataFromLists(objectCollection: ObjectCollection) {

        val link: MutableList<List<Link>> = mutableListOf()
        val datum: MutableList<List<Datum>> = mutableListOf()
        val items: List<Item>? = objectCollection.collection?.items

        items.let {
            if (it != null) {
                for (i in it.indices) {
                    it[i].links?.let { link.add(it) }
                    it[i].data?.let { datum.add(it) }

                    val linkListOfAllDates = link.flatten()
                    val datumListOfAllDates = datum.flatten()

                    contract?.showListDataFromPresenter(linkListOfAllDates)
                    contract?.showDatumDataFromPresenter(datumListOfAllDates)
                }
            }
        }
    }

    fun disposeDisposable() {
        compositeDisposable.dispose()
    }
}