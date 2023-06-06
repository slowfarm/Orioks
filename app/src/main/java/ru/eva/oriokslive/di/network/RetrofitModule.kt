package ru.eva.oriokslive.di.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.eva.oriokslive.network.CookieApi
import ru.eva.oriokslive.network.MietApi
import ru.eva.oriokslive.network.NewsApi
import ru.eva.oriokslive.network.OrioksApi
import ru.eva.oriokslive.network.provider.RetrofitProvider

@InstallIn(SingletonComponent::class)
@Module
object RetrofitModule {

    @Provides
    fun provideOrioksApi(retrofitProvider: RetrofitProvider): OrioksApi =
        retrofitProvider.provideOrioksApi()

    @Provides
    fun provideMietApi(retrofitProvider: RetrofitProvider): MietApi =
        retrofitProvider.provideMietApi()

    @Provides
    fun provideNewsApi(retrofitProvider: RetrofitProvider): NewsApi =
        retrofitProvider.provideNewsApi()

    @Provides
    fun provideCookieApi(retrofitProvider: RetrofitProvider): CookieApi =
        retrofitProvider.provideCookieApi()
}
