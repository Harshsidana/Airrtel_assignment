package com.appstreet.base.extension

import com.appstreet.base.exception.RequestException

fun Throwable.throwException(): Nothing = throw this

val Throwable.errorCode
    get() = when (this) {
        is RequestException -> statusCode
        else -> null
    }