package com.appstreet.base.extension

import android.app.Activity
import android.content.res.Resources
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import com.appstreet.base.helper.KeyboardUtil

fun AppCompatActivity.hideKeyboard() = KeyboardUtil.hideKeyboard(this)

fun getScreenHeight() = Resources.getSystem().displayMetrics.heightPixels

fun Activity.getStatusBarHeight(): Int {
    var result = 0
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = resources.getDimensionPixelSize(resourceId)
    }
    return result
}