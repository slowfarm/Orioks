package ru.eva.oriokslive.network.entity.schedule

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Time(
    @SerializedName("Time")
    @ColumnInfo(name = "time")
    val time: String,
    @SerializedName("Code")
    @ColumnInfo(name = "code")
    val code: Int,
    @SerializedName("TimeFrom")
    @ColumnInfo(name = "timeFrom")
    val timeFrom: String,
    @SerializedName("TimeTo")
    @ColumnInfo(name = "timeTo")
    val timeTo: String,
)
