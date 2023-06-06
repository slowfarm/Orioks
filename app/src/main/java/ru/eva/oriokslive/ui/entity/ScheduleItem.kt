package ru.eva.oriokslive.ui.entity

import ru.eva.oriokslive.network.entity.schedule.Clazz
import ru.eva.oriokslive.network.entity.schedule.Group
import ru.eva.oriokslive.network.entity.schedule.Room
import ru.eva.oriokslive.network.entity.schedule.Time

data class ScheduleItem(
    val day: Int? = null,
    val dayNumber: Int? = null,
    val name: String? = null,
    val room: String? = null,
    val teacher: String? = null,
    val time: String? = null,
    val dayOfWeek: Int,
)