package ru.eva.oriokslive.interfaces.http;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import ru.eva.oriokslive.models.orioks.AccessToken;
import ru.eva.oriokslive.models.orioks.Discipline;
import ru.eva.oriokslive.models.orioks.Event;
import ru.eva.oriokslive.models.orioks.Security;
import ru.eva.oriokslive.models.orioks.Student;

public interface OrioksAPI {

    @Headers({"Accept: application/json"})
    @GET("/api/v1/auth")
    Call<AccessToken> getToken(@Header("Authorization") String loginPass, @Header("User-Agent") String userAgent);

    @Headers({"Accept: application/json"})
    @GET("/api/v1/student/disciplines")
    Call<List<Discipline>> getDisciplines(@Header("Authorization") String token, @Header("User-Agent") String userAgent);

    @Headers({"Accept: application/json"})
    @GET("/api/v1/student")
    Call<Student> getStudent(@Header("Authorization") String token, @Header("User-Agent") String userAgent);

    @Headers({"Accept: application/json"})
    @GET("/api/v1/student/disciplines/{id}/events")
    Call<List<Event>> getEvents(@Header("Authorization") String token, @Header("User-Agent") String userAgent, @Path("id") int id);

    @Headers({"Accept: application/json"})
    @DELETE("/api/v1/student/tokens/{token}")
    Call<AccessToken> deleteAccessToken(@Header("Authorization") String token, @Header("User-Agent") String userAgent, @Path("token") String token1);

    @Headers({"Accept: application/json"})
    @GET("/api/v1/student/tokens")
    Call<List<Security>> getAllActiveTokens(@Header("Authorization") String token, @Header("User-Agent") String userAgent);

    @Headers({"Accept: application/json"})
    @GET("/api/v1/schedule/groups")
    Call<String[]> getGroup(@Header("Authorization") String token, @Header("User-Agent") String userAgent);
}