package ru.eva.oriokslive.ui.entity

data class ScheduleItem(
    val day: Int? = null,
    val dayNumber: Int? = null,
    val name: String? = null,
    val room: String? = null,
    val teacher: String? = null,
    val time: String? = null,
    val dayOfWeek: Int,
)
