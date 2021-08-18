package com.aziz.gallery.utils

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager


object Utils {
    fun hideKeyboardFrom(view: View) {
        val imm = view.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}