package ru.eva.oriokslive.network

import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import ru.eva.oriokslive.network.entity.Security
import ru.eva.oriokslive.network.entity.orioks.AccessToken
import ru.eva.oriokslive.network.entity.orioks.Discipline
import ru.eva.oriokslive.network.entity.orioks.Event
import ru.eva.oriokslive.network.entity.orioks.Student


interface OrioksApi {
    @GET("/api/v1/auth")
    suspend fun getToken(@Header("Authorization") loginPass: String): AccessToken?

    @GET("/api/v1/student/disciplines")
    suspend fun getDisciplines(): List<Discipline>?

    @GET("/api/v1/student")
    suspend fun getStudent(): Student?

    @GET("/api/v1/student/disciplines/{id}/events")
    suspend fun getEvents(@Path("id") id: Int): List<Event>?

    @DELETE("/api/v1/student/tokens/{token}")
    fun deleteAccessToken(@Path("token") token: String): AccessToken?

    @GET("/api/v1/student/tokens")
    fun getAllActiveTokens(): List<Security>?
}
