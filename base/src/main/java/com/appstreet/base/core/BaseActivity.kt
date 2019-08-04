package com.appstreet.base.core

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.MenuItem
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import android.view.WindowManager
import com.appstreet.base.R
import com.appstreet.base.extension.getColorFromRes
import com.appstreet.base.extension.hideKeyboard
import com.appstreet.base.helper.KeyboardUtil
import com.appstreet.ui.PageLoadingProgress

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {

    @LayoutRes
    abstract fun getLayoutRes(): Int

    @ColorRes
    open fun statusBarColor(): Int = R.color.default_status_bar_color

    open fun lightColorIcon(): Boolean = false

    protected val loadingDialog by lazy { PageLoadingProgress(this) }

    abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutRes())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) changeStatusBarColor()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) changeStatusBarIconColor()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return true
    }

    override fun onBackPressed() {
        if (!loadingDialog.isVisible(this)) super.onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        hideKeyboard()
    }

    fun showLoading(isShow: Boolean) {
        if (isShow) {
            with(loadingDialog) {
                showFromActivity(this@BaseActivity)
                requestFocus()
            }
            hideKeyboard()
        } else loadingDialog.hideFromActivity(this)
    }

    public fun changeStatusBarColor(@ColorRes barColor: Int = statusBarColor()) {
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) return
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = getColorFromRes(barColor)
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

    fun showBackButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun setToolBarTitle(title:String){
        supportActionBar?.setTitle(title)
    }
}