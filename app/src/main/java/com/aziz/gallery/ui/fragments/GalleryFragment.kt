package com.aziz.gallery.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.aziz.gallery.R
import com.aziz.gallery.adapters.PhotoAdapter
import com.aziz.gallery.config.Constants
import com.aziz.gallery.network.models.PhotoResponse
import com.aziz.gallery.network.models.Status
import com.aziz.gallery.utils.Utils
import com.aziz.gallery.utils.invisible
import com.aziz.gallery.utils.visible
import kotlinx.android.synthetic.main.fragment_gallery.*

class GalleryFragment : BaseFragment() {
    var photoAdapter = PhotoAdapter()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun search(text: String) {
        viewModel.searchPhotos(text).observe(viewLifecycleOwner, {
            when (it?.status) {
                Status.LOADING -> showProgress()
                Status.SUCCESS -> onSuccess(it.data)
                Status.ERROR -> onError(it.message)
            }
        })
    }

    private fun setupUI() {
        setupSearch()
        setupAdapter()
    }

    private fun setupAdapter() {
        galleryRV.layoutManager = GridLayoutManager(requireContext(), 3)
        galleryRV.setOnScrollChangeListener { _, _, _, _, _ ->
            val layoutManager = galleryRV.layoutManager as GridLayoutManager
            if (layoutManager.findLastCompletelyVisibleItemPosition() == photoAdapter.items.size - 1) {
                search(viewModel.lastQuery)
            }
        }
    }

    private fun setupSearch() {
        searchET.requestFocus()
        searchET.setOnEditorActionListener { _, id, _ ->
            if (id == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.currentPage = 1
                search(searchET.text.toString())
                Utils.hideKeyboardFrom(searchET)
                return@setOnEditorActionListener true
            }

            false
        }
    }

    private fun onError(message: String?) {
        progressBar.invisible()
        galleryRV.visible()
        Toast.makeText(requireContext(), message ?: "Error", Toast.LENGTH_SHORT).show()
    }

    private fun onSuccess(data: PhotoResponse?) {
        progressBar.invisible()
        galleryRV.visible()
        if (viewModel.currentPage == 1) {
            photoAdapter = PhotoAdapter()
            galleryRV.adapter = photoAdapter
            photoAdapter.onItemClick = {
                openPreviewFragment(it)
            }
        }

        photoAdapter.update(data)
        viewModel.currentPage += 1
    }

    private fun openPreviewFragment(it: String) {
        val bundle = Bundle()
        bundle.putString(Constants.IMAGE_URL, it)
        viewModel.previewLiveData.postValue(bundle)
    }

    private fun showProgress() {
        progressBar.visible()
    }

}