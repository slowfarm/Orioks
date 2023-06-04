package ru.eva.oriokslive.network.entity.schedule

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import ru.eva.oriokslive.domain.Common

@Entity(tableName = Common.TABLE_SCHEDULE)
data class Data(
    @SerializedName("Day")
    @ColumnInfo(name = "day")
    @PrimaryKey
    val day: Int,
    @SerializedName("DayNumber")
    @ColumnInfo(name = "dayNumber")
    val dayNumber: Int,
    @SerializedName("Time")
    @Embedded(prefix = "time_")
    val time: Time,
    @SerializedName("Class")
    @Embedded(prefix = "clazz_")
    val clazz: Clazz,
    @SerializedName("Group")
    @Embedded(prefix = "group_")
    val group: Group,
    @SerializedName("Room")
    @Embedded(prefix = "room_")
    val room: Room,
)