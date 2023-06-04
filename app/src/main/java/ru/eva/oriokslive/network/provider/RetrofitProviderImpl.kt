package ru.eva.oriokslive.network.provider

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.eva.oriokslive.BuildConfig
import ru.eva.oriokslive.domain.repository.DomainRepository
import ru.eva.oriokslive.network.MietApi
import ru.eva.oriokslive.network.OrioksApi
import ru.eva.oriokslive.network.utils.BasicAuthInterceptor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitProviderImpl @Inject constructor(domainRepository: DomainRepository) :
    RetrofitProvider {

    private val client = OkHttpClient.Builder()
        .addNetworkInterceptor(
            HttpLoggingInterceptor().apply { level = if (BuildConfig.DEBUG) BODY else NONE }
        )
        .addInterceptor(BasicAuthInterceptor(domainRepository.getAccessToken()))
        .build()

    override fun provideOrioksApi(): OrioksApi {
        return Retrofit.Builder()
            .baseUrl("https://orioks.miet.ru/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(OrioksApi::class.java)
    }

    override fun provideMietApi(): MietApi {
        return Retrofit.Builder()
            .baseUrl("https:/miet.ru/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(MietApi::class.java)
    }
}