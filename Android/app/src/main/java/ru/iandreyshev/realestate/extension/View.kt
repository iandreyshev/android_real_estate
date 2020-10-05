package ru.iandreyshev.realestate.extension

import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.core.content.ContextCompat

fun View.pixels(@DimenRes dimenRes: Int) = uiLazy {
    resources.getDimensionPixelSize(dimenRes)
}

fun View.pixelsF(@DimenRes dimenRes: Int) = uiLazy {
    resources.getDimensionPixelSize(dimenRes).toFloat()
}

fun View.color(@ColorRes colorRes: Int) = uiLazy {
    ContextCompat.getColor(context, colorRes)
}
