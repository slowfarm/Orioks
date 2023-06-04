package ru.eva.oriokslive.domain.repository

import android.content.SharedPreferences
import androidx.annotation.WorkerThread
import ru.eva.oriokslive.domain.dao.DisciplinesDao
import ru.eva.oriokslive.domain.dao.StudentDao
import ru.eva.oriokslive.network.entity.orioks.Discipline
import ru.eva.oriokslive.network.entity.orioks.Event
import ru.eva.oriokslive.network.entity.orioks.Student
import ru.eva.oriokslive.network.entity.schedule.Data
import ru.eva.oriokslive.network.entity.schedule.Schedule
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DomainRepositoryImpl @Inject constructor(
    private val preferences: SharedPreferences,
    private val studentDao: StudentDao,
    private val disciplinesDao: DisciplinesDao,
) : DomainRepository {
    override fun setAccessToken(value: String) {
        preferences.edit().putString(ACCESS_TOKEN, value).commit()
    }

    @WorkerThread
    override fun getAccessToken(): String? = preferences.getString(ACCESS_TOKEN, null)

    @WorkerThread
    override suspend fun setStudent(student: Student) {
        studentDao.insert(student)
    }

    @WorkerThread
    override suspend fun getStudent(): Student? = studentDao.getStudent()

    override suspend fun getEvents(id: Int): List<Event>? {
        TODO("Not yet implemented")
    }

    override suspend fun getDisciplineById(id: Int): Discipline = disciplinesDao.getDisciplineById(id)

    @WorkerThread
    override suspend fun setDisciplines(disciplines: List<Discipline>) {
        disciplinesDao.insert(disciplines)
    }

    @WorkerThread
    override suspend fun getDisciplines(): List<Discipline>? = disciplinesDao.getDisciplines()

    @WorkerThread
    override suspend fun clearAll() {
        preferences.edit().clear().apply()
    }

    override suspend fun getGroups(): List<String>? {
        TODO("Not yet implemented")
    }

    override suspend fun removeGroup(group: String) {
        TODO("Not yet implemented")
    }

    override suspend fun addGroup(group: String) {
        TODO("Not yet implemented")
    }

    override suspend fun setSchedule(schedule: Schedule) {
        TODO("Not yet implemented")
    }

    override suspend fun getSchedule(group: String): List<Data>? {
        TODO("Not yet implemented")
    }

    override suspend fun getSchedule(dayNumber: Int, day: Int, group: String): List<Data>? {
        TODO("Not yet implemented")
    }

    override suspend fun getSchedule(dayNumber: Int, group: String): List<Data>? {
        TODO("Not yet implemented")
    }

    companion object {
        private const val ACCESS_TOKEN = "ACCESS_TOKEN"
    }
}
