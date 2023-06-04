package ru.eva.oriokslive.network.repository

import okhttp3.Credentials
import ru.eva.oriokslive.network.MietApi
import ru.eva.oriokslive.network.OrioksApi
import ru.eva.oriokslive.network.entity.Security
import ru.eva.oriokslive.network.entity.orioks.AccessToken
import ru.eva.oriokslive.network.entity.orioks.Discipline
import ru.eva.oriokslive.network.entity.orioks.Event
import ru.eva.oriokslive.network.entity.orioks.Student
import ru.eva.oriokslive.network.entity.schedule.Group
import ru.eva.oriokslive.network.entity.schedule.Schedule
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteRepositoryImpl @Inject constructor(
    private val orioksApi: OrioksApi,
    private val mietApi: MietApi,
) : RemoteRepository {
    override suspend fun getAccessToken(login: String, password: String): AccessToken? =
        orioksApi.getToken(Credentials.basic(login, password))

    override suspend fun getStudent(): Student? = orioksApi.getStudent()

    override suspend fun getEvents(id: Int): List<Event>? = orioksApi.getEvents(id)

    override suspend fun getDisciplines(): List<Discipline>? = orioksApi.getDisciplines()

    override suspend fun deleteAccessToken(token: String): AccessToken? =
        orioksApi.deleteAccessToken(token)

    override suspend fun getAllActiveTokens(): List<Security>? = orioksApi.getAllActiveTokens()

    override suspend fun getGroups(): List<String>? = mietApi.getGroups()

    override suspend fun getSchedule(group: String): Schedule? = mietApi.getScheduler(group)
}

