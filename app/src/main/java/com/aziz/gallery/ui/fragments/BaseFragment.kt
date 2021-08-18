package com.aziz.gallery.ui.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.aziz.gallery.ui.viewmodels.MainViewModel

open class BaseFragment : Fragment() {
    val viewModel by activityViewModels<MainViewModel>()

}