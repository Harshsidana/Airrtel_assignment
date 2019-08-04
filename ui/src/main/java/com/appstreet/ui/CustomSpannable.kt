package com.appstreet.ui

import android.content.Context
import android.text.style.ClickableSpan
import android.text.TextPaint
import android.view.View
import androidx.core.content.ContextCompat


open class CustomSpannable(val context: Context, private val isUnderline: Boolean): ClickableSpan() {


    override fun updateDrawState(ds: TextPaint) {
        ds.isUnderlineText = isUnderline
        ds.color = ContextCompat.getColor(context, android.R.color.black)//parseColor("#1b76d3")
    }

    override fun onClick(widget: View) {


    }
}