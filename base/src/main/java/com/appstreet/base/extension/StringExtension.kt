package com.appstreet.base.extension

import java.text.SimpleDateFormat
import java.util.*

fun String.milliSecToDate(): String {
    return SimpleDateFormat("dd MMM", Locale.getDefault()).format(this.toLong()).toString()
}

fun String.milliSecToWeek(): String {
    return SimpleDateFormat("EE", Locale.getDefault()).format(this.toLong()).toString()
}

fun String.milliSecTotime(): String {
    return SimpleDateFormat("hh:mm", Locale.getDefault()).format(this.toLong()).toString()
}

fun String.milliSecToMeridian(): String {
    return SimpleDateFormat("aaa", Locale.getDefault()).format(this.toLong()).toString()
}