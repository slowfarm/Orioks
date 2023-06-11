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
        val response = chain.proceed(builder.build())
        parseCookie(response.peekBody(2048).string())?.let {
            domainRepository.setCookie(it)
            response.close()
            return chain.proceed(builder.header("Cookie", it).build())
        }
        return response
    }

    private fun parseCookie(response: String) =
        """(?<=cookie=").*(?=;path=/)""".toRegex().find(response)?.value
}
