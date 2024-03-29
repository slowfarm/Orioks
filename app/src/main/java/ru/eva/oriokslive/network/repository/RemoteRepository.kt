package ru.eva.oriokslive.network.repository

import ru.eva.oriokslive.network.entity.Semester
import ru.eva.oriokslive.network.entity.news.NewsResponse
import ru.eva.oriokslive.network.entity.orioks.AccessToken
import ru.eva.oriokslive.network.entity.orioks.Debt
import ru.eva.oriokslive.network.entity.orioks.Discipline
import ru.eva.oriokslive.network.entity.orioks.Event
import ru.eva.oriokslive.network.entity.orioks.Resit
import ru.eva.oriokslive.network.entity.orioks.Security
import ru.eva.oriokslive.network.entity.orioks.Student
import ru.eva.oriokslive.network.entity.schedule.Schedule

interface RemoteRepository {
    suspend fun getAccessToken(login: String, password: String): AccessToken
    suspend fun getStudent(): Student
    suspend fun getEvents(id: Int): List<Event>
    suspend fun getDisciplines(): List<Discipline>
    suspend fun deleteAccessToken(token: String): AccessToken
    suspend fun getAllActiveTokens(): List<Security>
    suspend fun getDebts(): List<Debt>
    suspend fun getResits(id: Int): List<Resit>
    suspend fun getSemester(): Semester
    suspend fun changeSemester(id: Int): Semester
    suspend fun getGroups(): List<String>
    suspend fun getSchedule(group: String): Schedule
    suspend fun getNews(): NewsResponse
}
