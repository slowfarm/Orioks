package ru.eva.oriokslive.network.entity.orioks

import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("alias") val alias: String,
    @SerializedName("current_grade") val currentGrade: Double,
    @SerializedName("max_grade") val maxGrade: Double,
    @SerializedName("name") val name: String,
    @SerializedName("type") val type: String,
    @SerializedName("week") val week: String,
    @SerializedName("id") val id: Int,
)