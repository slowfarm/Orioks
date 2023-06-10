package ru.eva.oriokslive.network.utils

import okhttp3.Interceptor
import okhttp3.Response
import ru.eva.oriokslive.domain.repository.DomainRepository
import java.io.IOException

class MietInterceptor(private val domainRepository: DomainRepository) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val builder = original.newBuilder().method(original.method, original.body)
        domainRepository.getCookie()?.let { builder.header("Cookie", it) }
        var response = chain.proceed(builder.build())
        response.body?.string()?.let { parseCookie(it) }?.let {
            domainRepository.setCookie(it)
            builder.header("Cookie", it)
            response.close()
            response = chain.proceed(builder.build())
        }
        return response
    }

    private fun parseCookie(response: String) = """(?<=cookie=").*(?=;path=/)""".toRegex().find(response)?.value
}