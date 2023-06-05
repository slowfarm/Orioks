package ru.eva.oriokslive.utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.showToast(text: String?, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, length).show()
}

fun Context.showToast(@StringRes id: Int, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, id, length).show()
}

fun Context.showToast(throwable: Throwable, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, throwable.message, length).show()
}