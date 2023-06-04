package ru.eva.oriokslive.network.entity.schedule

import com.google.gson.annotations.SerializedName

data class Clazz(
    @SerializedName("Code") val code: String,
    @SerializedName("Name") val name: String,
    @SerializedName("TeacherFull") val teacherFull: String,
    @SerializedName("Teacher") val teacher: String,
)