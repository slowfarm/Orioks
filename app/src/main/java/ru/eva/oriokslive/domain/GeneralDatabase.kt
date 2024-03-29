package ru.eva.oriokslive.domain

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.eva.oriokslive.domain.dao.DebtsDao
import ru.eva.oriokslive.domain.dao.DisciplinesDao
import ru.eva.oriokslive.domain.dao.EventDao
import ru.eva.oriokslive.domain.dao.ResitDao
import ru.eva.oriokslive.domain.dao.ScheduleDao
import ru.eva.oriokslive.domain.dao.StudentDao
import ru.eva.oriokslive.domain.utils.Converters
import ru.eva.oriokslive.network.entity.orioks.Debt
import ru.eva.oriokslive.network.entity.orioks.Discipline
import ru.eva.oriokslive.network.entity.orioks.Event
import ru.eva.oriokslive.network.entity.orioks.Resit
import ru.eva.oriokslive.network.entity.orioks.Student
import ru.eva.oriokslive.network.entity.schedule.Data

@Database(
    entities = [Student::class, Discipline::class, Data::class, Event::class, Debt::class, Resit::class],
    version = Common.VERSION_DATABASE,
)
@TypeConverters(Converters::class)
abstract class GeneralDatabase : RoomDatabase() {
    abstract fun getStudentDao(): StudentDao
    abstract fun getDisciplineDao(): DisciplinesDao
    abstract fun getScheduleDao(): ScheduleDao
    abstract fun getEventDao(): EventDao
    abstract fun getDebtDao(): DebtsDao
    abstract fun getResitDao(): ResitDao
}
