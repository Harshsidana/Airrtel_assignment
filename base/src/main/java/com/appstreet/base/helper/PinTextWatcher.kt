package com.appstreet.base.helper

import android.app.Activity
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.AppCompatEditText

class PinTextWatcher constructor(
    private val currentIndex: Int,
    private val editTexts: Array<AppCompatEditText>
) :
    TextWatcher {
    private var isFirst = false
    private var isLast = false
    private var newTypedString = ""

    private val isAllEditTextsFilled: Boolean
        get() {
            for (editText in editTexts)
                if (editText.text.toString().trim().isEmpty())
                    return false
            return true
        }

    init {
        if (currentIndex == 0)
            this.isFirst = true
        else if (currentIndex == editTexts.size - 1)
            this.isLast = true
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        newTypedString = s.subSequence(start, start + count).toString().trim()
    }

    override fun afterTextChanged(s: Editable) {

        var text = newTypedString

        if (text.length > 1)
            text = text[0].toString()

        editTexts[currentIndex].removeTextChangedListener(this)
        editTexts[currentIndex].setText(text)
        editTexts[currentIndex].setSelection(text.length)
        editTexts[currentIndex].addTextChangedListener(this)

        if (text.length == 1)
            moveToNext()
    }

    private fun moveToNext() {
        if (!isLast)
            editTexts[currentIndex + 1].requestFocus()

        if (isAllEditTextsFilled && isLast) { // isLast is optional
            val inputMethodManager =
                editTexts[currentIndex].context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(editTexts[currentIndex].windowToken, 0)
        }
    }

    private fun moveToPrevious() {
        if (!isFirst) {
            editTexts[currentIndex - 1].requestFocus()
        }

    }

}
