package ru.eva.oriokslive.network.entity.schedule

import com.google.gson.annotations.SerializedName

data class Schedule(
    @SerializedName("Data") val data: List<Data>,
    @SerializedName("Semestr") val semester: String,
)