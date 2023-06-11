package ru.eva.oriokslive.ui.entity

data class EventItem(
    val progress: Int,
    val name: String,
    val type: String,
    val grade: String = "-",
    val maxGrade: String,
    val color: Int,
    val week: String,
)
