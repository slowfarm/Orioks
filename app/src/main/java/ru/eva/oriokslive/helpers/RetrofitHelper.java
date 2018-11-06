package ru.eva.oriokslive.helpers;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.eva.oriokslive.App;
import ru.eva.oriokslive.interfaces.OnDisciplinesRecieved;
import ru.eva.oriokslive.interfaces.OnEventsRecieved;
import ru.eva.oriokslive.interfaces.OnSchedulersReceived;
import ru.eva.oriokslive.interfaces.OnStudentRecieved;
import ru.eva.oriokslive.interfaces.OnTokenRecieved;
import ru.eva.oriokslive.models.orioks.AccessToken;
import ru.eva.oriokslive.models.orioks.Disciplines;
import ru.eva.oriokslive.models.orioks.Events;
import ru.eva.oriokslive.models.schedule.Schedulers;
import ru.eva.oriokslive.models.orioks.Student;

public class RetrofitHelper {
    private static RetrofitHelper instance;

    private OnTokenRecieved onTokenReceived;
    private OnDisciplinesRecieved onDisciplinesRecieved;
    private OnStudentRecieved onStudentRecieved;
    private OnEventsRecieved onEventsRecieved;
    private OnSchedulersReceived onSchedulersReceived;

    public static RetrofitHelper getInstance() {
        if (instance == null)
            instance = new RetrofitHelper();
        return instance;
    }

    public void getAccessToken(String encodedString) {
        String userAgent = "User-Agent: orioks_live/2.0 Android "+android.os.Build.VERSION.RELEASE;
        App.getApi().getToken(encodedString, userAgent).enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                onTokenReceived.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                onTokenReceived.onFailure(t);
            }
        });
    }

    public void getDisciplines(String token, String year, String semester) {
        String userAgent = "User-Agent: orioks_live/2.0 Android "+android.os.Build.VERSION.RELEASE;
        App.getApi().getDisciplines("Bearer "+token, userAgent).enqueue(new Callback<List<Disciplines>>() {
            @Override
            public void onResponse(Call<List<Disciplines>> call, Response<List<Disciplines>> response) {
                onDisciplinesRecieved.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<List<Disciplines>> call, Throwable t) {
                onDisciplinesRecieved.onFailure(t);
            }
        });
    }


    public void getStudent(String token) {
        String userAgent = "User-Agent: orioks_live/2.0 Android "+android.os.Build.VERSION.RELEASE;
        App.getApi().getStudent("Bearer "+token, userAgent).enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                onStudentRecieved.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                onStudentRecieved.onFailure(t);
            }
        });
    }

    public void getEvents(String token, int id) {
        String userAgent = "User-Agent: orioks_live/2.0 Android "+android.os.Build.VERSION.RELEASE;
        App.getApi().getEvents("Bearer "+token, userAgent, id).enqueue(new Callback<List<Events>>() {
            @Override
            public void onResponse(Call<List<Events>> call, Response<List<Events>> response) {
                onEventsRecieved.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<List<Events>> call, Throwable t) {
                onEventsRecieved.onFailure(t);
            }
        });
    }

    public void getSchedulers(String group) {
        App.getMietAPI().getScheduler(group).enqueue(new Callback<Schedulers>() {
            @Override
            public void onResponse(Call<Schedulers> call, Response<Schedulers> response) {
                onSchedulersReceived.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<Schedulers> call, Throwable t) {
                onSchedulersReceived.onFailure(t);
            }
        });

    }

    public void deleteAccessToken(String token) {
        String userAgent = "User-Agent: orioks_live/2.0 Android "+android.os.Build.VERSION.RELEASE;
        App.getApi().deleteAccessToken("Bearer "+token,userAgent, token).enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                onTokenReceived.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                onTokenReceived.onFailure(t);
            }
        });
    }

    public void setOnTokenReceived(OnTokenRecieved onTokenReceived) {
        this.onTokenReceived = onTokenReceived;
    }
    public void setOnDisciplinesReceived(OnDisciplinesRecieved onDisciplinesRecieved) {
        this.onDisciplinesRecieved = onDisciplinesRecieved;
    }
    public void setOnStudentReceived(OnStudentRecieved onStudentRecieved) {
        this.onStudentRecieved = onStudentRecieved;
    }

    public void setOnEventsReceived(OnEventsRecieved onEventsRecieved) {
        this.onEventsRecieved = onEventsRecieved;
    }

    public void setOnSchedulersReceived(OnSchedulersReceived onSchedulersReceived) {
        this.onSchedulersReceived = onSchedulersReceived;
    }
}
