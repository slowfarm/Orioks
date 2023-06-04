package ru.eva.oriokslive.domain

import androidx.room.migration.Migration

object Common {
    const val DATABASE_NAME = "transfer_database"
    const val VERSION_DATABASE = 1
    const val TABLE_STUDENT = "student_table"
    const val TABLE_DISCIPLINE = "discipline_table"

    fun getMigrations(): Array<Migration> = arrayOf()
}