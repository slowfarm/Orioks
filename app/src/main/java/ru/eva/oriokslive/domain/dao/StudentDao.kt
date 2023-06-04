package ru.eva.oriokslive.domain.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.eva.oriokslive.domain.Common.TABLE_STUDENT
import ru.eva.oriokslive.network.entity.orioks.Student

@Dao
interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(student: Student)

    @Query("SELECT * FROM $TABLE_STUDENT LIMIT 1")
    fun getStudent(): Student?
}