package ru.eva.oriokslive.network.entity

data class Semester(
    val current: Int,
    val list: List<SemesterList>,
    val semester: Int,
    val error: String,
)
