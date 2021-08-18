package com.aziz.gallery.utils

import android.view.View
import android.view.View.*

fun View.visible() {
    visibility = VISIBLE
}

fun View.gone() {
    visibility = GONE
}

fun View.invisible() {
    visibility = INVISIBLE
}

fun View.setVisible(isVisible: Boolean, gone: Boolean = false) {
    visibility = if (isVisible) VISIBLE else if (gone) GONE else INVISIBLE
}

