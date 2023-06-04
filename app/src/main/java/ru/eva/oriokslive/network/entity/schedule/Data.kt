package ru.eva.oriokslive.network.entity.schedule

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("Day") val day: Int? = null,
    @SerializedName("DayNumber") val dayNumber: Int? = null,
    @SerializedName("Time") val time: Time? = null,
    @SerializedName("Class") val clazz: Clazz? = null,
    @SerializedName("Group") val group: Group? = null,
    @SerializedName("Room") val room: Room? = null,
    val dayOfWeek: String? = null,
)