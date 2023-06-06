package ru.eva.oriokslive.network.entity.orioks

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.eva.oriokslive.domain.Common

@Entity(tableName = Common.TABLE_DEBT)
data class Debt(
    @ColumnInfo(name = "consultationSchedule")
    val consultationSchedule: List<String>,
    @ColumnInfo(name = "formControl")
    val formControl: String,
    @ColumnInfo(name = "currentScore")
    val currentScore: Double,
    @ColumnInfo(name = "deadline")
    val deadline: String,
    @ColumnInfo(name = "institute")
    val institute: String,
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "maxScore")
    val maxScore: Double,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "teachers")
    val teachers: List<String>,
)
