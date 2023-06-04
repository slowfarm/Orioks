package ru.eva.oriokslive.domain.repository

import ru.eva.oriokslive.network.entity.orioks.Discipline
import ru.eva.oriokslive.network.entity.orioks.Event
import ru.eva.oriokslive.network.entity.orioks.Student
import ru.eva.oriokslive.network.entity.schedule.Data
import ru.eva.oriokslive.network.entity.schedule.Schedule
import java.time.DayOfWeek

interface DomainRepository {
    fun setAccessToken(value: String)
    fun getAccessToken(): String?
    suspend fun setStudent(student: Student)
    suspend fun getStudent(): Student?
    suspend fun getEvents(id: Int): List<Event>?
    suspend fun getDisciplineById(id: Int): Discipline
    suspend fun setDisciplines(disciplines: List<Discipline>)
    suspend fun getDisciplines(): List<Discipline>?
    suspend fun clearAll()
    suspend fun getGroups(): List<String>?
    suspend fun removeGroup(group: String)
    suspend fun addGroup(group: String)
    suspend fun setSchedule(schedule: Schedule)
    suspend fun getSchedule(group: String): List<Data>?
    suspend fun getSchedule(dayNumber: Int, day: Int, group: String): List<Data>?
    suspend fun getSchedule(dayNumber: Int, group: String): List<Data>?

}
