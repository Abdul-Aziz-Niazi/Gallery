package com.aziz.gallery.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aziz.gallery.R
import com.aziz.gallery.network.models.Photo
import com.aziz.gallery.network.models.PhotoResponse
import com.aziz.gallery.utils.DiffUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.layout_item_photo.view.*

class PhotoAdapter : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {
    val items = ArrayList<Photo>()
    lateinit var onItemClick: (url: String) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_photo, null, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun update(data: PhotoResponse?) {
        data?.hits?.addAll(0, items)
        val diffCallback = DiffUtils(items, data?.hits ?: arrayListOf())
        val diffResult = DiffUtil.calculateDiff(diffCallback, true)
        items.clear()
        items.addAll(data?.hits ?: arrayListOf())
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(photo: Photo) {
            Glide.with(view).load(photo.previewURL)
                .placeholder(R.drawable.img)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(view.photo)
            view.setOnClickListener {
                onItemClick(photo.webformatURL)
            }
        }
    }

}