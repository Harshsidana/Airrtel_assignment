package com.appstreet.base.extension

fun Boolean?.fallBack(fallBackValue: Boolean = false) = this ?: fallBackValue

fun Boolean.toInt() = if (this) 1 else 0

fun Boolean.toIntString() = toInt().toString()