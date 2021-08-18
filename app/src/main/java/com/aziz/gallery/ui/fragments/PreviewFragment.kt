package com.aziz.gallery.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aziz.gallery.R
import com.aziz.gallery.config.Constants
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_preview.*

class PreviewFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_preview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val url = arguments?.getString(Constants.IMAGE_URL)
        Glide.with(this).load(url).into(imageView)
    }
}