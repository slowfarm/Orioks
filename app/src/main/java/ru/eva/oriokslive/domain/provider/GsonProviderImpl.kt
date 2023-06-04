package ru.eva.oriokslive.domain.provider

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GsonProviderImpl @Inject constructor() : GsonProvider {

    override fun provideGson(): Gson {
        val builder = GsonBuilder()
        builder.setPrettyPrinting()
        return builder.create()
    }
}
