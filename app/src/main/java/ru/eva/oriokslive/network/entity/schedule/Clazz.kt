package ru.eva.oriokslive.network.entity.schedule

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Clazz(
    @SerializedName("Code")
    @ColumnInfo(name = "code")
    val code: String,
    @SerializedName("Name")
    @ColumnInfo(name = "name")
    val name: String,
    @SerializedName("TeacherFull")
    @ColumnInfo(name = "teacherFull")
    val teacherFull: String,
    @SerializedName("Teacher")
    @ColumnInfo(name = "teacher")
    val teacher: String,
)
