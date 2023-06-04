package ru.eva.oriokslive.network.entity.schedule

import com.google.gson.annotations.SerializedName

data class Time(
    @SerializedName("Time") val time: String,
    @SerializedName("Code") val code: Int,
    @SerializedName("TimeFrom") val timeFrom: String,
    @SerializedName("TimeTo") val timeTo: String,
)