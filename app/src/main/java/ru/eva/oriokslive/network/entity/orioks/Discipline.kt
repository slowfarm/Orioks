package ru.eva.oriokslive.network.entity.orioks

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import ru.eva.oriokslive.domain.Common

@Entity(tableName = Common.TABLE_DISCIPLINE)
data class Discipline(
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: Int,
    @SerializedName("control_form")
    @ColumnInfo(name = "controlForm")
    val controlForm: String,
    @SerializedName("current_grade")
    @ColumnInfo(name = "currentGrade")
    val currentGrade: Double,
    @ColumnInfo(name = "department")
    val department: String,
    @SerializedName("exam_date")
    @ColumnInfo(name = "examDate")
    val examDate: String,
    @SerializedName("is_active")
    @ColumnInfo(name = "isActive")
    val isActive: Boolean,
    @SerializedName("max_grade")
    @ColumnInfo(name = "maxGrade")
    val maxGrade: Double,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("teachers")
    val teachers: List<String>,
)
