package ru.eva.oriokslive.ui.entity

import androidx.room.ColumnInfo
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import ru.eva.oriokslive.network.entity.schedule.Clazz
import ru.eva.oriokslive.network.entity.schedule.Group
import ru.eva.oriokslive.network.entity.schedule.Room
import ru.eva.oriokslive.network.entity.schedule.Time

data class ScheduleItem(
    val day: Int? = null,
    val dayNumber: Int? = null,
    val time: Time? = null,
    val clazz: Clazz? = null,
    val group: Group? = null,
    val room: Room? = null,
    val dayOfWeek: String? = null,
)