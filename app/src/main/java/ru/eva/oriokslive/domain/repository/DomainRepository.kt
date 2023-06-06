package ru.eva.oriokslive.domain.repository

import ru.eva.oriokslive.network.entity.orioks.Debt
import ru.eva.oriokslive.network.entity.orioks.Discipline
import ru.eva.oriokslive.network.entity.orioks.Event
import ru.eva.oriokslive.network.entity.orioks.Resit
import ru.eva.oriokslive.network.entity.orioks.Student
import ru.eva.oriokslive.network.entity.schedule.Data

interface DomainRepository {
    fun setAccessToken(value: String)
    fun getAccessToken(): String?
    fun setCookie(value: String)
    fun getCookie(): String?
    fun setDefaultTheme(value: Int)
    fun getDefaultTheme(): Int
    suspend fun setStudent(student: Student)
    suspend fun getStudent(): Student?
    suspend fun setEvents(events: List<Event>)
    suspend fun getEventsById(id: Int): List<Event>?
    suspend fun getDisciplineById(id: Int): Discipline
    suspend fun setDisciplines(disciplines: List<Discipline>)
    suspend fun getDisciplines(): List<Discipline>?
    suspend fun setDebts(debts: List<Debt>)
    suspend fun getDebts(): List<Debt>?
    suspend fun getDebtsById(id: Int): Debt
    fun setResits(resits: List<Resit>)
    fun getResitsById(id: Int): List<Resit>?
    suspend fun clearAll()
    suspend fun getGroups(): List<String>?
    suspend fun removeGroup(group: String)
    suspend fun setSchedule(schedule: List<Data>)
    suspend fun getSchedule(group: String): List<Data>?
    suspend fun getSchedule(dayNumber: Int, day: Int, group: String): List<Data>?
    suspend fun getSchedule(dayNumber: Int, group: String): List<Data>?
}
