package ru.eva.oriokslive.domain;


import android.support.annotation.NonNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.eva.oriokslive.App;
import ru.eva.oriokslive.interfaces.OnAllAccessTokensReceived;
import ru.eva.oriokslive.interfaces.OnDisciplinesRecieved;
import ru.eva.oriokslive.interfaces.OnEventsRecieved;
import ru.eva.oriokslive.interfaces.OnGroupsReceived;
import ru.eva.oriokslive.interfaces.OnNewsReceived;
import ru.eva.oriokslive.interfaces.OnSchedulersReceived;
import ru.eva.oriokslive.interfaces.OnStudentRecieved;
import ru.eva.oriokslive.interfaces.OnTokenRecieved;
import ru.eva.oriokslive.models.miet.news.NewsResponse;
import ru.eva.oriokslive.models.orioks.AccessToken;
import ru.eva.oriokslive.models.orioks.Discipline;
import ru.eva.oriokslive.models.orioks.Event;
import ru.eva.oriokslive.models.orioks.Security;
import ru.eva.oriokslive.models.miet.schedule.Schedulers;
import ru.eva.oriokslive.models.orioks.Student;

public class NetworkStorage {
    private static NetworkStorage instance;

    private OnTokenRecieved onTokenReceived;
    private OnDisciplinesRecieved onDisciplinesRecieved;
    private OnStudentRecieved onStudentRecieved;
    private OnEventsRecieved onEventsRecieved;
    private OnSchedulersReceived onSchedulersReceived;
    private OnAllAccessTokensReceived onAllAccessTokensReceived;
    private OnGroupsReceived onGroupsReceived;
    private OnNewsReceived onNewsReceived;

    public static NetworkStorage getInstance() {
        if (instance == null)
            instance = new NetworkStorage();
        return instance;
    }

    public void getAccessToken(String encodedString) {
        String userAgent = "Orioks/2.0 Android "+android.os.Build.VERSION.RELEASE;
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
        String userAgent = "Orioks/2.0 Android "+android.os.Build.VERSION.RELEASE;
        App.getApi().getDisciplines("Bearer "+token, userAgent).enqueue(new Callback<List<Discipline>>() {
            @Override
            public void onResponse(Call<List<Discipline>> call, Response<List<Discipline>> response) {
                onDisciplinesRecieved.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<List<Discipline>> call, Throwable t) {
                onDisciplinesRecieved.onFailure(t);
            }
        });
    }

    public void getStudent(String token) {
        String userAgent = "Orioks/2.0 Android "+android.os.Build.VERSION.RELEASE;
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
        String userAgent = "Orioks/2.0 Android "+android.os.Build.VERSION.RELEASE;
        App.getApi().getEvents("Bearer "+token, userAgent, id).enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                onEventsRecieved.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                onEventsRecieved.onFailure(t);
            }
        });
    }

    public void getSchedulers(String group) {
        App.getScheduleAPI().getScheduler(group).enqueue(new Callback<Schedulers>() {
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


    public void getNews() {
        App.getNewsAPI().getNews().enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(@NonNull Call<NewsResponse> call, @NonNull Response<NewsResponse> response) {
                onNewsReceived.onResponse(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<NewsResponse> call, @NonNull Throwable t) {
                onNewsReceived.onFailure(t);
            }
        });
    }

    public void deleteAccessToken(String token) {
        String userAgent = "Orioks/2.0 Android "+android.os.Build.VERSION.RELEASE;
        App.getApi().deleteAccessToken("Bearer "+token, userAgent, token).enqueue(new Callback<AccessToken>() {
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

    public void getAllActiveTokens(String token) {
        String userAgent = "Orioks/2.0 Android "+android.os.Build.VERSION.RELEASE;
        App.getApi().getAllActiveTokens("Bearer "+token, userAgent).enqueue(new Callback<List<Security>>() {
            @Override
            public void onResponse(Call<List<Security>> call, Response<List<Security>> response) {
                onAllAccessTokensReceived.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<List<Security>> call, Throwable t) {
                onAllAccessTokensReceived.onFailure(t);
            }
        });
    }

    public void getGroups() {
        App.getScheduleAPI().getGroups().enqueue(new Callback<String[]>() {
            @Override
            public void onResponse(@NonNull Call<String[]> call, @NonNull Response<String[]> response) {
                onGroupsReceived.onResponse(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<String[]> call, @NonNull Throwable t) {
                onGroupsReceived.onFailure(t);
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

    public void setOnAllActiveTokensReceived(OnAllAccessTokensReceived onAllAccessTokensReceived) {
        this.onAllAccessTokensReceived = onAllAccessTokensReceived;
    }

    public void setOnGroupsReceived(OnGroupsReceived onGroupsReceived) {
        this.onGroupsReceived = onGroupsReceived;
    }

    public void setOnNewsReceived(OnNewsReceived onNewsReceived) {
        this.onNewsReceived = onNewsReceived;
    }
}
