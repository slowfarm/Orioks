package ru.eva.oriokslive.domain.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.eva.oriokslive.domain.Common
import ru.eva.oriokslive.network.entity.schedule.Data

@Dao
interface ScheduleDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(schedule: List<Data>)

    @Query("SELECT * FROM ${Common.TABLE_SCHEDULE} WHERE group_name =:group")
    fun getSchedule(group: String): List<Data>?

    @Query("SELECT * FROM ${Common.TABLE_SCHEDULE} WHERE dayNumber=:dayNumber AND day=:day AND group_name=:group")
    fun getSchedule(dayNumber: Int, day: Int, group: String): List<Data>?

    @Query("SELECT * FROM ${Common.TABLE_SCHEDULE} WHERE dayNumber=:dayNumber AND group_name=:group")
    fun getSchedule(dayNumber: Int, group: String): List<Data>?

    @Query("SELECT group_name FROM ${Common.TABLE_SCHEDULE} GROUP BY group_name")
    fun getGroups(): List<String>?

    @Query("DELETE FROM ${Common.TABLE_SCHEDULE} WHERE group_name = :group")
    fun removeGroup(group: String)
}