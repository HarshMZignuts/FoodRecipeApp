package com.example.foodrecipeapp.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber

object Utils {
    fun snackBar(view: View?, context: Context?, message: String?) {
        if (message != null && view != null) {
            val imm =
                context!!.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)

            val snackbar =
                Snackbar.make(view, message, Snackbar.LENGTH_LONG)
            val snackbarView = snackbar.view
            val snackTextView =
                snackbarView.findViewById<View>(com.google.android.material.R.id.snackbar_text) as TextView

            snackTextView.maxLines = 4
            snackbar.show()
        }
    }

    fun logout() {
        try {
            PrefManager.clearAll()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            Timber.e(e.localizedMessage)
        }
    }
}