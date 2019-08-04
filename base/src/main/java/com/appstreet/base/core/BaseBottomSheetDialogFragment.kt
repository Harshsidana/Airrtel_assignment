package com.appstreet.base.core

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.appstreet.base.extension.getColorFromRes
import com.appstreet.base.extension.hideKeyboard
import com.appstreet.base.helper.KeyboardUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetDialogFragment<VM: ViewModel> : BottomSheetDialogFragment() {

    @LayoutRes
    abstract fun getLayoutRes(): Int

    @ColorRes
    open fun statusBarColor(): Int? = null

    open fun lightColorIcon(): Boolean = false

    abstract val viewModel: VM

    fun fragmentTag(): String = this::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutRes(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        KeyboardUtil(activity as FragmentActivity, view)
        isCancelable = false
        changeStatusBarColor()
        changeStatusBarIconColor()
        viewInitialization(view)
        onPreparationFinished(view)
    }

    override fun onDestroyView() {
        (activity as AppCompatActivity?)?.hideKeyboard()
        super.onDestroyView()
    }

    private fun changeStatusBarColor() {
        if(android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) return
        activity?.window?.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor()?.let { color -> statusBarColor = context.getColorFromRes(color) }
        }
    }

    private fun changeStatusBarIconColor() {
        activity?.window?.decorView?.rootView?.systemUiVisibility = if (lightColorIcon()) 0 else 8192
    }

    /**
     * This method is called after view has been created.
     * This method should be used to initialize all views that are needed to be created (and recreated after fragment is reattached)
     * @param view The root view of the fragment
     */
    open fun viewInitialization(view: View) {}

    /**
     * This method is called after viewInitialization(view) is finished
     * @param view The root view of the fragment
     */
    open fun onPreparationFinished(view: View) {}
}