package com.example.nasa_test_app_kot.screens.nasa_news

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nasa_test_app_kot.R
import com.example.nasa_test_app_kot.adapter.NasaAdapter
import com.example.nasa_test_app_kot.api.NetworkService
import com.example.nasa_test_app_kot.data.Datum
import com.example.nasa_test_app_kot.data.Item
import com.example.nasa_test_app_kot.data.Link
import com.example.nasa_test_app_kot.data.ObjectCollection
import com.example.nasa_test_app_kot.screens.NasaDetailActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.nasa_news_activity.*

class NasaNewsActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()
    private val adapter = NasaAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nasa_news_activity)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        switchNasa.isChecked = true
        switchNasa.setOnCheckedChangeListener { buttonView, isChecked ->
            this@NasaNewsActivity.updateSwitchState(isChecked)
        }
        switchNasa.isChecked = false

        textViewMars.setOnClickListener(View.OnClickListener { v: View? ->
            updateSwitchState(true)
            switchNasa.isChecked = true
        })

        textViewSpace.setOnClickListener(View.OnClickListener { v: View? ->
            updateSwitchState(false)
            switchNasa.isChecked = false
        })

    }

    private fun updateSwitchState(isMarsSelected: Boolean) {
        if (isMarsSelected) {
            loadMarsData()
            setImageClickListener()
            textViewMars.setTextColor(resources.getColor(R.color.colorAccent))
            textViewSpace.setTextColor(resources.getColor(R.color.colorWhite))
        } else {
            loadSpaceData()
            setImageClickListener()
            textViewMars.setTextColor(resources.getColor(R.color.colorWhite))
            textViewSpace.setTextColor(resources.getColor(R.color.colorAccent))
        }
    }

    private fun setImageClickListener() {
        adapter.onImageClickListener = object: NasaAdapter.OnImageClickListener{
            override fun onImageClick(position: Int) {
                val link1: Link = adapter.linkList?.get(position)!!
                val datum1: Datum = adapter.datumList?.get(position)!!
                val intent = Intent(this@NasaNewsActivity, NasaDetailActivity::class.java)
                intent.putExtra("image", link1.href)
                intent.putExtra("title", datum1.title)
                intent.putExtra("desc", datum1.description)
                this@NasaNewsActivity.startActivity(intent)
            }

        }
    }

    private fun loadSpaceData() {
        val disposable = NetworkService.networkApi.getAllSpaceCollections()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callToGetAllDataFromLists(it)
            }, {
                Toast.makeText(
                    this, "Error load data: " + it.message, Toast.LENGTH_SHORT
                ).show()
            })
        compositeDisposable.add(disposable)
    }

    private fun loadMarsData() {
        val disposable = NetworkService.networkApi.getAllMarsCollection()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callToGetAllDataFromLists(it)
            }, {
                Toast.makeText(this, "Error load data: " + it.message, Toast.LENGTH_SHORT).show()
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

                    val linkListOfAllDates = link.flatMap { it }
                    val datumListOfAllDates = datum.flatMap { it }

                    adapter.linkList = linkListOfAllDates
                    adapter.datumList = datumListOfAllDates
                }
            }
        }
    }
        override fun onDestroy() {
            compositeDisposable.dispose()
            super.onDestroy()
        }
    }
