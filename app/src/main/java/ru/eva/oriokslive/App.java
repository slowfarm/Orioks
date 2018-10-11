package ru.eva.oriokslive;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.eva.oriokslive.interfaces.http.MietAPI;
import ru.eva.oriokslive.interfaces.http.OrioksAPI;

public class App extends android.app.Application {
    private static OrioksAPI orioksApi;
    private static MietAPI mietAPI;

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

        mietAPI = retrofit.create(MietAPI.class);
    }

    public static OrioksAPI getApi() {
        return orioksApi;
    }

    public static MietAPI getMietAPI() {return  mietAPI;}
}