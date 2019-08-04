package com.appstreet.base.timber

import android.util.Log
import timber.log.Timber

class CrashReportTree : Timber.Tree() {

    @Suppress("LogNotTimber")
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO) {
            Log.d(tag, message, t)
            return
        }

        Log.e(tag, message, t)
    }
}