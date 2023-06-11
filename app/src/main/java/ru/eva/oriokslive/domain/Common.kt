package ru.eva.oriokslive.domain

import androidx.room.migration.Migration

object Common {
    const val DATABASE_NAME = "transfer_database"
    const val VERSION_DATABASE = 1
    const val TABLE_STUDENT = "student_table"
    const val TABLE_DISCIPLINE = "discipline_table"
    const val TABLE_SCHEDULE = "schedule_table"
    const val TABLE_EVENT = "event_table"
    const val TABLE_DEBT = "debt_table"
    const val TABLE_RESIT = "resit_table"

    fun getMigrations(): Array<Migration> = arrayOf()
}
