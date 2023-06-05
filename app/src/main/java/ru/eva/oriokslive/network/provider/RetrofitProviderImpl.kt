package ru.eva.oriokslive.network.provider

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import org.simpleframework.xml.convert.AnnotationStrategy
import org.simpleframework.xml.core.Persister
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import ru.eva.oriokslive.BuildConfig.DEBUG
import ru.eva.oriokslive.domain.repository.DomainRepository
import ru.eva.oriokslive.network.MietApi
import ru.eva.oriokslive.network.NewsApi
import ru.eva.oriokslive.network.OrioksApi
import ru.eva.oriokslive.network.utils.AuthInterceptor
import ru.eva.oriokslive.network.utils.MietInterceptor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitProviderImpl @Inject constructor(
    private val domainRepository: DomainRepository
) : RetrofitProvider {

    private val client = OkHttpClient.Builder()
        .addNetworkInterceptor(HttpLoggingInterceptor().apply { level = if (DEBUG) BODY else NONE })

    override fun provideOrioksApi(): OrioksApi = Retrofit.Builder()
        .baseUrl("https://orioks.miet.ru/")
        .client(client.addInterceptor(AuthInterceptor(domainRepository)).build())
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()
        .create(OrioksApi::class.java)

    override fun provideMietApi(): MietApi = Retrofit.Builder()
        .baseUrl("https:/miet.ru/")
        .client(client.addInterceptor(MietInterceptor()).build())
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .build()
        .create(MietApi::class.java)

    override fun provideNewsApi(): NewsApi = Retrofit.Builder()
        .baseUrl("https:/miet.ru/")
        .client(client.addInterceptor(MietInterceptor()).build())
        .addConverterFactory(SimpleXmlConverterFactory.createNonStrict(Persister(AnnotationStrategy())))
        .build()
        .create(NewsApi::class.java)
}