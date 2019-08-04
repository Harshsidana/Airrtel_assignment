package com.appstreet.base.common

/**
 * Created by kenny on 22/10/18.
 */
interface ConverterAdapter<F, T> {

    fun doConvert(fromObject: F): T
}