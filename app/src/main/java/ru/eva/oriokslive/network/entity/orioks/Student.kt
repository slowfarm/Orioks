package ru.eva.oriokslive.network.entity.orioks

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import ru.eva.oriokslive.domain.Common

@Entity(tableName = Common.TABLE_STUDENT)
data class Student(
    @SerializedName("course")
    @ColumnInfo(name = "course")
    @PrimaryKey
    val course: Int,
    @SerializedName("department")
    @ColumnInfo(name = "department")
    val department: String,
    @SerializedName("full_name")
    @ColumnInfo(name = "fullName")
    val fullName: String,
    @SerializedName("group")
    @ColumnInfo(name = "group")
    val group: String,
    @SerializedName("record_book_id")
    @ColumnInfo(name = "recordBookId")
    val recordBookId: String,
    @SerializedName("semester")
    @ColumnInfo(name = "semester")
    val semester: Int,
    @SerializedName("study_direction")
    @ColumnInfo(name = "studyDirection")
    val studyDirection: String,
    @SerializedName("study_profile")
    @ColumnInfo(name = "studyProfile")
    val studyProfile: String,
    @SerializedName("year")
    @ColumnInfo(name = "year")
    val year: String,
)
