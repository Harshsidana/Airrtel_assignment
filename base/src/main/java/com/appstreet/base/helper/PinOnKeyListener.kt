package com.appstreet.base.helper

import android.view.KeyEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText

class PinOnKeyListener constructor(
    private val currentIndex: Int,
    private val editTexts: Array<AppCompatEditText>
) :
    View.OnKeyListener {

    override fun onKey(v: View, keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN) {
            if (editTexts[currentIndex].text.toString().isEmpty() && currentIndex != 0) {
                editTexts[currentIndex - 1].setText("")
                editTexts[currentIndex - 1].requestFocus()
            }
        }
        return false
    }

}