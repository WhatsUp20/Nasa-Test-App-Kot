package com.example.nasa_test_app_kot.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nasa_test_app_kot.R
import com.example.nasa_test_app_kot.data.Datum
import com.example.nasa_test_app_kot.data.Link
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.nasa_item.view.*

class NasaAdapter: RecyclerView.Adapter<NasaAdapter.NasaViewHolder>() {

    var linkList: List<Link>? = ArrayList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }
    var datumList:List<Datum>? = ArrayList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    val onImageClickListener: OnImageClickListener? = null

    interface OnImageClickListener {
        fun onImageClick(position: Int)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NasaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.nasa_item, parent, false)
        return NasaViewHolder(view)
    }

    override fun getItemCount():Int = linkList!!.size

    override fun onBindViewHolder(holder: NasaViewHolder, position: Int) {
        val link = linkList?.get(position)
        val datum = datumList?.get(position)
        with(holder) {
            titleToDetail.text = datum?.title
            descriptionToDetail.text = datum?.description
            Picasso.get().load(link?.href).into(holder.imageView)
        }

    }

    inner class NasaViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.imageView
        val descriptionToDetail = itemView.descriptionToDetail
        val titleToDetail = itemView.titleToDetail
        val listener = itemView.setOnClickListener {
            onImageClickListener?.onImageClick(adapterPosition)
        }
    }
}