package ru.eva.oriokslive.domain.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.eva.oriokslive.domain.Common.TABLE_DISCIPLINE
import ru.eva.oriokslive.network.entity.orioks.Discipline

@Dao
interface DisciplinesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(disciplines: List<Discipline>)

    @Query("SELECT * FROM $TABLE_DISCIPLINE")
    fun getDisciplines(): List<Discipline>?

    @Query("SELECT * FROM $TABLE_DISCIPLINE WHERE id=:id LIMIT 1")
    fun getDisciplineById(id: Int): Discipline
}
