package ru.eva.oriokslive.network.provider

import android.content.Context
import com.google.gson.GsonBuilder
import dagger.hilt.android.qualifiers.ApplicationContext
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
import ru.eva.oriokslive.network.utils.CheckNetworkInterceptor
import java.net.CookieHandler
import java.net.CookieManager
import java.net.CookiePolicy
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitProviderImpl @Inject constructor(
    @ApplicationContext context: Context,
    domainRepository: DomainRepository
) : RetrofitProvider {

    private val logging = HttpLoggingInterceptor().apply { level = if (DEBUG) BODY else NONE }
    private val checkNetworkInterceptor = CheckNetworkInterceptor(context)

    private val orioksClient = OkHttpClient.Builder()
        .addNetworkInterceptor(logging)
        .addInterceptor(checkNetworkInterceptor)
        .addInterceptor(AuthInterceptor(domainRepository))
        .build()

    private val cookieManager: CookieManager = CookieManager().apply {
        setCookiePolicy(CookiePolicy.ACCEPT_ALL)
        CookieHandler.setDefault(this)
    }

    private val mietClient = OkHttpClient.Builder()
        .addNetworkInterceptor(logging)
        .addInterceptor(checkNetworkInterceptor)
        //.addInterceptor(MietInterceptor(cookieManager))
        .build()

    override fun provideOrioksApi(): OrioksApi = Retrofit.Builder()
        .baseUrl("https://orioks.miet.ru/")
        .client(orioksClient)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()
        .create(OrioksApi::class.java)

    override fun provideMietApi(): MietApi = Retrofit.Builder()
        .baseUrl("https:/miet.ru/")
        .client(mietClient)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .build()
        .create(MietApi::class.java)

    override fun provideNewsApi(): NewsApi = Retrofit.Builder()
        .baseUrl("https:/miet.ru/")
        .client(mietClient)
        .addConverterFactory(SimpleXmlConverterFactory.createNonStrict(Persister(AnnotationStrategy())))
        .build()
        .create(NewsApi::class.java)
}