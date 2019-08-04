package com.appstreet.base.core

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.WindowManager
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.appstreet.base.R

abstract class BaseFragmentActivity<VM : BaseViewModel> : AppCompatActivity() {

    @LayoutRes
    abstract fun getLayoutRes(): Int

    @ColorRes
    open fun statusBarColor(): Int = R.color.default_status_bar_color

    open fun lightColorIcon(): Boolean = false

    abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutRes())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) changeStatusBarColor()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) changeStatusBarIconColor()
    }

    protected fun Context.getColorFromRes(@ColorRes colorRes: Int) = ContextCompat.getColor(this, colorRes)

    protected fun Context.getDimenFromRes(@DimenRes res: Int) = getTypedValue(res).float

    @SuppressWarnings("InlinedApi", "newApi")
    private fun changeStatusBarColor() {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = getColorFromRes(statusBarColor())
        }
    }

    private fun changeStatusBarIconColor() {
        window.decorView.rootView.systemUiVisibility = if (lightColorIcon()) 0 else 8192
    }

    private fun Context.getTypedValue(res: Int): TypedValue {
        return TypedValue().apply {
            resources.getValue(res, this, true)
        }
    }
}