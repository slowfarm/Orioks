package ru.eva.oriokslive.network.utils

import okhttp3.Interceptor
import okhttp3.Response
import ru.eva.oriokslive.domain.repository.DomainRepository
import java.io.IOException

class AuthInterceptor(private val domainRepository: DomainRepository) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val builder = original.newBuilder()
            .header("Accept", "application/json")
            .header("User-Agent", "orioks/2.0 Android ${android.os.Build.VERSION.RELEASE}")
            .method(original.method, original.body)
        domainRepository.getAccessToken()?.let { builder.header("Authorization", "Bearer $it") }
        return chain.proceed(builder.build())
    }
}
