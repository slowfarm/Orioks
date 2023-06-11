package ru.eva.oriokslive.network.entity.schedule

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Room(
    @SerializedName("Code")
    @ColumnInfo(name = "code")
    val code: String,
    @SerializedName("Name")
    @ColumnInfo(name = "name")
    val name: String,
)
