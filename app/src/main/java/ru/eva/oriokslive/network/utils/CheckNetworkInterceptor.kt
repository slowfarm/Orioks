package ru.eva.oriokslive.network.utils

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response
import ru.eva.oriokslive.R
import ru.eva.oriokslive.network.exceptions.NetworkException
import ru.eva.oriokslive.utils.networkAvailable
import java.io.IOException

class CheckNetworkInterceptor(private val context: Context) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!networkAvailable(context)) throw NetworkException(context.getString(R.string.no_connection))
        return chain.proceed(chain.request())
    }
}
