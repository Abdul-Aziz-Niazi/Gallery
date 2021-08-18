package com.aziz.gallery.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.aziz.gallery.R
import com.aziz.gallery.ui.fragments.GalleryFragment
import com.aziz.gallery.ui.fragments.PreviewFragment
import com.aziz.gallery.ui.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRootFragment()
        setupNavigation()
    }

    private fun setupNavigation() {
        viewModel.previewLiveData.observe(this, Observer {
            setFragment(PreviewFragment(), true, it)
        })
    }

    private fun setupRootFragment() {
        setFragment(GalleryFragment())
    }

    private fun setFragment(fragment: Fragment, add: Boolean = false, bundle: Bundle? = null) {
        val transaction = supportFragmentManager.beginTransaction()
        if (bundle != null)
            fragment.arguments = bundle
        if (add)
            transaction.add(R.id.container, fragment).addToBackStack(fragment.javaClass.simpleName).commit()
        else
            transaction.replace(R.id.container, fragment).commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount != 0)
            supportFragmentManager.popBackStack()
        else
            super.onBackPressed()
    }
}