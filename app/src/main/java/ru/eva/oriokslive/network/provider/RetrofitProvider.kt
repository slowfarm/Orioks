package ru.eva.oriokslive.network.provider

import ru.eva.oriokslive.network.CookieApi
import ru.eva.oriokslive.network.MietApi
import ru.eva.oriokslive.network.NewsApi
import ru.eva.oriokslive.network.OrioksApi

interface RetrofitProvider {

    fun provideOrioksApi(): OrioksApi

    fun provideMietApi(): MietApi

    fun provideNewsApi(): NewsApi

    fun provideCookieApi(): CookieApi
}