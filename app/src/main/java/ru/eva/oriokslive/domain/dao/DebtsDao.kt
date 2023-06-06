package ru.eva.oriokslive.domain.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.eva.oriokslive.domain.Common
import ru.eva.oriokslive.network.entity.orioks.Debt

@Dao
interface DebtsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(debts: List<Debt>)

    @Query("SELECT * FROM ${Common.TABLE_DEBT}")
    fun getDebts(): List<Debt>?

    @Query("SELECT * FROM ${Common.TABLE_DEBT} WHERE id=:id LIMIT 1")
    fun getDebtsById(id: Int): Debt
}