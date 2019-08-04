package com.appstreet.base.extension


fun Map<String, Any>?.getString(key: String) = this?.get(key) as String?

fun Map<String, Any>?.getLong(key: String) = (this?.get(key) as Double?)?.toLong()

fun Map<String, Any>?.getInt(key: String) = (this?.get(key) as Double?)?.toInt()

fun Map<String, Any>?.getDouble(key: String) = this?.get(key) as Double?

fun Map<String, Any>?.getBoolean(key: String) = this?.get(key) as Boolean?

fun <T>Map<String, Any>?.getList(key: String) = this?.get(key) as List<T>?

