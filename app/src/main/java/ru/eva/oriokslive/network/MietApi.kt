package ru.eva.oriokslive.network

import retrofit2.http.POST
import retrofit2.http.Query
import ru.eva.oriokslive.network.entity.schedule.Schedule

interface MietApi {
    @POST("/schedule/data")
    suspend fun getScheduler(@Query("group") group: String): Schedule?

    @POST("/schedule/groups")
    suspend fun getGroups(): Array<String>?
}
