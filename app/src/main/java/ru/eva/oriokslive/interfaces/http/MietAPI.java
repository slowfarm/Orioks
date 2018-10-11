package ru.eva.oriokslive.interfaces.http;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;
import ru.eva.oriokslive.models.schedule.Schedulers;

public interface MietAPI {
    @POST("/schedule/data")
    Call<Schedulers> getScheduler(@Query("group") String group);
}
