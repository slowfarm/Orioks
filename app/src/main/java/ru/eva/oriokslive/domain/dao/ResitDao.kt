package ru.eva.oriokslive.domain.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.eva.oriokslive.domain.Common
import ru.eva.oriokslive.network.entity.orioks.Resit

@Dao
interface ResitDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(resits: List<Resit>)

    @Query("SELECT * FROM ${Common.TABLE_RESIT} WHERE id =:id")
    fun getResitById(id: Int): List<Resit>?

    @Query("DELETE FROM ${Common.TABLE_RESIT}")
    fun clear()
}
