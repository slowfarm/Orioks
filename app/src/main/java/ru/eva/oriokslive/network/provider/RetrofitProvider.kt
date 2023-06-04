package ru.eva.oriokslive.network.provider

import ru.eva.oriokslive.network.MietApi
import ru.eva.oriokslive.network.OrioksApi

interface RetrofitProvider {

    fun provideOrioksApi(): OrioksApi

    fun provideMietApi(): MietApi
}