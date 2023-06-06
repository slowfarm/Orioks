package ru.eva.oriokslive.network.entity.orioks

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import ru.eva.oriokslive.domain.Common

@Entity(tableName = Common.TABLE_RESIT)
data class Resit(
    @ColumnInfo(name = "classroom")
    var classroom: String,
    @ColumnInfo(name = "datetime")
    var datetime: String,
    @SerializedName("resit_number")
    @ColumnInfo(name = "resitNumber")
    var resitNumber: String,
    @ColumnInfo(name = "id")
    @PrimaryKey
    var id: Int,
)
