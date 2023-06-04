package ru.eva.oriokslive.ui.entity

data class SecurityItem(
    val token: String,
    val application: String,
    val device: String,
    val containDevice: Boolean,
    val lastUsed: String,
)