package ru.eva.oriokslive.network.utils

import okhttp3.Interceptor
import okhttp3.Response
import ru.eva.oriokslive.domain.repository.DomainRepository
import java.io.IOException

class MietInterceptor(private val domainRepository: DomainRepository) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val builder = original.newBuilder()
            .method(original.method, original.body)
        domainRepository.getCookie()?.let { builder.header("Cookie", it) }
        return chain.proceed(builder.build())
    }
}