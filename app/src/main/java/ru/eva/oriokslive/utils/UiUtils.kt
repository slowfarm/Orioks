package ru.eva.oriokslive.utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.widget.SearchView

fun Context.showToast(text: String?, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, length).show()
}

fun Context.showToast(@StringRes id: Int, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, id, length).show()
}

fun Context.showToast(throwable: Throwable, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, throwable.message, length).show()
}

fun SearchView.setOnQueryChangeListener(listener: (String) -> Unit) {
    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            query?.let(listener)
            return false
        }

        override fun onQueryTextChange(query: String?): Boolean {
            query?.let(listener)
            return false
        }
    })
}
