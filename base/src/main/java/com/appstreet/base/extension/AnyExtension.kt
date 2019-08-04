package com.appstreet.base.extension

infix fun Any?.extract(fieldName: String): Any {
    return if (this != null) this::class.java.getField(fieldName).get(this)
    else throw NullPointerException("Cannot get field $fieldName from a null object")
}

infix fun Any?.extractString(fieldName: String): String {
    return extract(fieldName) as String
}