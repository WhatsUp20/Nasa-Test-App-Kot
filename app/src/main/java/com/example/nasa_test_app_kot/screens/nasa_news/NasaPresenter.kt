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
import java.util.*

class NasaPresenter(contract:NasaContract) {

    private val compositeDisposable = CompositeDisposable()
    private val contract: NasaContract? = null

    fun loadSpaceData() {
        val disposable = NetworkService.networkApi.getAllSpaceCollections()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callToGetAllDataFromLists(it)
            }, {
                Toast.makeText(
                    contract as Context?,
                    "Error load data: " + it.message,
                    Toast.LENGTH_SHORT
                ).show()
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
                Toast.makeText(
                    contract as Context?,
                    "Error load data: " + it.message,
                    Toast.LENGTH_SHORT
                ).show()
            })
        compositeDisposable.add(disposable)
    }

    private fun callToGetAllDataFromLists(objectCollection: ObjectCollection) {

        //Get All lists
        val link: MutableList<List<Link>?> = ArrayList()
        val datum: MutableList<List<Datum>?> = ArrayList()
        val linkList: MutableList<Link?> = ArrayList()
        val datumList: MutableList<Datum?> = ArrayList()
        val items: List<Item?>? = objectCollection.collection?.items

        //Fill link and datum lists data from item list
        for (i in items!!.indices) {
            link.add(objectCollection.collection?.items!![i]?.links)
            datum.add(objectCollection.collection?.items!![i]?.data)
        }

        //Get and add all dates to linkList
        for (i in link.indices) {
            val links: List<Link>? = link[i]
            if (links != null) {
                linkList.addAll(links)
            }
        }
        //Get and add all dates to datumList
        for (i in datum.indices) {
            val datumListOfAllDates: List<Datum>? = datum[i]
            if (datumListOfAllDates != null) {
                datumList.addAll(datumListOfAllDates)
            }

            contract!!.showDatumDataFromPresenter(datumList)
            contract!!.showListDataFromPresenter(linkList)
        }
    }

    fun disposeDisposable() {
        compositeDisposable.dispose()
    }
}