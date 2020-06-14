package com.github.mrindeciso.delegatedpreferences_demo.utils

import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import com.google.android.material.textfield.TextInputEditText

fun TextInputEditText.onKeyboardEnter(callback: (text: String) -> Unit) {
    this.setOnEditorActionListener { _, actionId, event ->
        if (actionId == EditorInfo.IME_ACTION_SEARCH
            || actionId == EditorInfo.IME_ACTION_DONE
            || event?.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_ENTER
        ) {
            callback(this.text.toString())
            true
        } else {
            false
        }
    }
}