package ru.eva.oriokslive.domain.provider

import com.google.gson.Gson

interface GsonProvider {

    fun provideGson(): Gson
}
