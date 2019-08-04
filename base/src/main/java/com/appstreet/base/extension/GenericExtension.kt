package com.appstreet.base.extension

import com.appstreet.base.common.ConverterAdapter

/**
 * Created by kenny on 26/10/18.
 */
infix fun <F, T> F.convertWith(converterAdapter: ConverterAdapter<F, T>): T {
    return this.let(converterAdapter::doConvert)
}

infix fun <T>T?.ifNull(defVal: () -> T): T = this ?: defVal()