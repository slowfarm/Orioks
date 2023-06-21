package ru.eva.oriokslive.domain.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.eva.oriokslive.domain.Common
import ru.eva.oriokslive.network.entity.orioks.Event

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(events: List<Event>)

    @Query("SELECT * FROM ${Common.TABLE_EVENT} WHERE id =:id")
    fun getEventsById(id: Int): List<Event>?

    @Query("DELETE FROM ${Common.TABLE_EVENT}")
    fun clear()
}
