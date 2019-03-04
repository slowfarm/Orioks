package ru.eva.oriokslive.interfaces.http;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;
import ru.eva.oriokslive.models.miet.schedule.Schedulers;

public interface ScheduleAPI {
    @POST("/schedule/data")
    Call<Schedulers> getScheduler(@Query("group") String group);

    @POST("/schedule/groups")
    Call<String[]> getGroups();
}
