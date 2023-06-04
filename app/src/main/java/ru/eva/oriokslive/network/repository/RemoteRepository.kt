package ru.eva.oriokslive.network.repository

import ru.eva.oriokslive.network.entity.Security
import ru.eva.oriokslive.network.entity.orioks.AccessToken
import ru.eva.oriokslive.network.entity.orioks.Discipline
import ru.eva.oriokslive.network.entity.orioks.Event
import ru.eva.oriokslive.network.entity.orioks.Student
import ru.eva.oriokslive.network.entity.schedule.Group
import ru.eva.oriokslive.network.entity.schedule.Schedule

interface RemoteRepository {
    suspend fun getAccessToken(login: String, password: String): AccessToken?
    suspend fun getStudent(): Student?
    suspend fun getEvents(id: Int): List<Event>?
    suspend fun getDisciplines(): List<Discipline>?
    suspend fun deleteAccessToken(token: String): AccessToken?
    suspend fun getAllActiveTokens(): List<Security>?
    suspend fun getGroups(): List<String>?
    suspend fun getSchedule(group: String): Schedule?
}