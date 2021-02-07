package com.example.nasa_test_app_kot.screens.nasa_news

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nasa_test_app_kot.R
import com.example.nasa_test_app_kot.adapter.NasaAdapter
import com.example.nasa_test_app_kot.data.Datum
import com.example.nasa_test_app_kot.data.Link
import com.example.nasa_test_app_kot.screens.NasaDetailActivity
import kotlinx.android.synthetic.main.nasa_news_activity.*

class NasaNewsActivity : AppCompatActivity(), NasaContract {

    private val adapter = NasaAdapter()
    private val presenter = NasaPresenter(this)

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
            presenter.loadMarsData()
            setImageClickListener()
            textViewMars.setTextColor(resources.getColor(R.color.colorAccent))
            textViewSpace.setTextColor(resources.getColor(R.color.colorWhite))
        } else {
            presenter.loadSpaceData()
            setImageClickListener()
            textViewMars.setTextColor(resources.getColor(R.color.colorWhite))
            textViewSpace.setTextColor(resources.getColor(R.color.colorAccent))
        }
    }

    private fun setImageClickListener() {
        adapter.onImageClickListener = object : NasaAdapter.OnImageClickListener {
            override fun onImageClick(position: Int) {
                val link1: Link? = adapter.linkList?.get(position)
                val datum1: Datum? = adapter.datumList?.get(position)
                val intent = Intent(this@NasaNewsActivity, NasaDetailActivity::class.java)
                intent.putExtra("image", link1?.href)
                intent.putExtra("title", datum1?.title)
                intent.putExtra("desc", datum1?.description)
                this@NasaNewsActivity.startActivity(intent)
            }
        }
    }

    override fun onDestroy() {
        presenter.disposeDisposable()
        super.onDestroy()
    }

    override fun showDatumDataFromPresenter(datumList: List<Datum>?) {
        adapter.datumList = datumList
    }

    override fun showListDataFromPresenter(linkList: List<Link>?) {
        adapter.linkList = linkList
    }

    override fun showProgressBar() {
        progressBarLoading.visibility = View.VISIBLE
    }

    override fun noShowProgressBar() {
        progressBarLoading.visibility = View.INVISIBLE
    }
}
