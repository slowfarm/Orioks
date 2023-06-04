package ru.eva.oriokslive.ui.entity

import androidx.annotation.StringRes

data class Header(
    val week: String,
    val progress: Int,
    @StringRes val value: Int,
    val username: String,
    val group: String,
)
