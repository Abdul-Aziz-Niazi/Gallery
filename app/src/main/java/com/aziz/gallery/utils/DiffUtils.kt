package com.aziz.gallery.utils

import androidx.recyclerview.widget.DiffUtil
import com.aziz.gallery.network.models.Photo

class DiffUtils(private val oldList: ArrayList<Photo>, private val newList: ArrayList<Photo>) :
    DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].imageURL == newList[newItemPosition].imageURL
    }
}