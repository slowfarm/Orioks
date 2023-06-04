package ru.eva.oriokslive.network.entity.schedule

import com.google.gson.annotations.SerializedName

data class Room(
    @SerializedName("Code") val code: String,
    @SerializedName("Name") val name: String,
)