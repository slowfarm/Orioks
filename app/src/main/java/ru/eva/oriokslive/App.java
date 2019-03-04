package ru.eva.oriokslive;

import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import ru.eva.oriokslive.interfaces.http.NewsAPI;
import ru.eva.oriokslive.interfaces.http.OrioksAPI;
import ru.eva.oriokslive.interfaces.http.ScheduleAPI;

public class App extends android.app.Application {
    private static OrioksAPI orioksApi;
    private static ScheduleAPI scheduleAPI;
    private static NewsAPI newsAPI;

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);


        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));

        OkHttpClient httpClient = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://orioks.miet.ru/")
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        orioksApi = retrofit.create(OrioksAPI.class);

        retrofit = new Retrofit.Builder()
                .baseUrl("https:/miet.ru/")
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        scheduleAPI = retrofit.create(ScheduleAPI.class);

        retrofit = new Retrofit.Builder()
                .baseUrl("https:/miet.ru/")
                .client(httpClient)
                .addConverterFactory(SimpleXmlConverterFactory.createNonStrict(
                        new Persister(new AnnotationStrategy())))
                .build();

        newsAPI = retrofit.create(NewsAPI.class);
    }

    public static OrioksAPI getApi() {
        return orioksApi;
    }

    public static ScheduleAPI getScheduleAPI() {return scheduleAPI;}

    public static NewsAPI getNewsAPI() {return newsAPI;}
}