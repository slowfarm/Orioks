package ru.eva.oriokslive.network.entity.orioks

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import ru.eva.oriokslive.domain.Common

@Entity(tableName = Common.TABLE_EVENT)
data class Event(
    @SerializedName("alias")
    @ColumnInfo(name = "alias")
    val alias: String,
    @SerializedName("current_grade")
    @ColumnInfo(name = "currentGrade")
    val currentGrade: Double?,
    @SerializedName("max_grade")
    @ColumnInfo(name = "maxGrade")
    val maxGrade: Double,
    @SerializedName("name")
    @ColumnInfo(name = "name")
    val name: String?,
    @SerializedName("type")
    @ColumnInfo(name = "type")
    val type: String,
    @SerializedName("week")
    @ColumnInfo(name = "week")
    val week: String,
    @SerializedName("id")
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: Int,
)
