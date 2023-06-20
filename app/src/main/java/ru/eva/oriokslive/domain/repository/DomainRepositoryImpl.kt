package ru.eva.oriokslive.domain.repository

import android.content.SharedPreferences
import androidx.annotation.WorkerThread
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
import ru.eva.oriokslive.domain.GeneralDatabase
import ru.eva.oriokslive.domain.dao.DebtsDao
import ru.eva.oriokslive.domain.dao.DisciplinesDao
import ru.eva.oriokslive.domain.dao.EventDao
import ru.eva.oriokslive.domain.dao.ResitDao
import ru.eva.oriokslive.domain.dao.ScheduleDao
import ru.eva.oriokslive.domain.dao.StudentDao
import ru.eva.oriokslive.network.entity.orioks.Debt
import ru.eva.oriokslive.network.entity.orioks.Discipline
import ru.eva.oriokslive.network.entity.orioks.Event
import ru.eva.oriokslive.network.entity.orioks.Resit
import ru.eva.oriokslive.network.entity.orioks.Student
import ru.eva.oriokslive.network.entity.schedule.Data
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DomainRepositoryImpl @Inject constructor(
    private val preferences: SharedPreferences,
    private val generalDatabase: GeneralDatabase,
    private val studentDao: StudentDao,
    private val disciplinesDao: DisciplinesDao,
    private val scheduleDao: ScheduleDao,
    private val eventDao: EventDao,
    private val debtsDao: DebtsDao,
    private val resitDao: ResitDao,
) : DomainRepository {

    override fun setAccessToken(value: String) {
        preferences.edit().putString(ACCESS_TOKEN, value).commit()
    }

    override fun getAccessToken(): String? = preferences.getString(ACCESS_TOKEN, null)
    override fun setCookie(value: String) {
        preferences.edit().putString(COOKIE, value).commit()
    }

    override fun getCookie(): String? = preferences.getString(COOKIE, null)

    override fun setDefaultTheme(value: Int) {
        preferences.edit().putInt(THEME, value).apply()
    }

    override fun getDefaultTheme(): Int = preferences.getInt(THEME, MODE_NIGHT_FOLLOW_SYSTEM)

    @WorkerThread
    override suspend fun setStudent(student: Student) {
        studentDao.insert(student)
    }

    @WorkerThread
    override suspend fun getStudent(): Student? = studentDao.getStudent()

    @WorkerThread
    override suspend fun setEvents(events: List<Event>) {
        eventDao.insert(events)
    }

    @WorkerThread
    override suspend fun getEventsById(id: Int): List<Event>? = eventDao.getEventsById(id)

    @WorkerThread
    override suspend fun setDisciplines(disciplines: List<Discipline>) {
        disciplinesDao.insert(disciplines)
    }

    @WorkerThread
    override suspend fun getDisciplineById(id: Int): Discipline =
        disciplinesDao.getDisciplineById(id)

    @WorkerThread
    override suspend fun getDisciplines(): List<Discipline>? = disciplinesDao.getDisciplines()

    @WorkerThread
    override suspend fun setDebts(debts: List<Debt>) {
        debtsDao.insert(debts)
    }

    @WorkerThread
    override suspend fun getDebts(): List<Debt>? = debtsDao.getDebts()

    @WorkerThread
    override suspend fun getDebtsById(id: Int): Debt = debtsDao.getDebtsById(id)

    @WorkerThread
    override fun setResits(resits: List<Resit>) {
        resitDao.insert(resits)
    }

    @WorkerThread
    override fun getResitsById(id: Int): List<Resit>? = resitDao.getResitById(id)

    @WorkerThread
    override suspend fun clearAll() {
        preferences.edit().clear().apply()
        generalDatabase.clearAllTables()
    }

    @WorkerThread
    override suspend fun getGroups(): List<String>? = scheduleDao.getGroups()

    @WorkerThread
    override suspend fun removeGroup(group: String) = scheduleDao.removeGroup(group)

    @WorkerThread
    override suspend fun setSchedule(schedule: List<Data>) {
        scheduleDao.insert(schedule)
    }

    @WorkerThread
    override suspend fun getSchedule(group: String): List<Data>? = scheduleDao.getSchedule(group)

    @WorkerThread
    override suspend fun getSchedule(dayNumber: Int, day: Int, group: String): List<Data>? =
        scheduleDao.getSchedule(dayNumber, day, group)

    @WorkerThread
    override suspend fun getSchedule(dayNumber: Int, group: String): List<Data>? =
        scheduleDao.getSchedule(dayNumber, group)

    companion object {
        private const val ACCESS_TOKEN = "ACCESS_TOKEN"
        private const val THEME = "THEME"
        private const val COOKIE = "COOKIE"
    }
}
