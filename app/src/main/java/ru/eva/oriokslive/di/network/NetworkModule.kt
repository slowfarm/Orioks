package ru.eva.oriokslive.di.network

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import ru.eva.oriokslive.network.provider.RetrofitProvider
import ru.eva.oriokslive.network.provider.RetrofitProviderImpl
import ru.eva.oriokslive.network.repository.RemoteRepository
import ru.eva.oriokslive.network.repository.RemoteRepositoryImpl

@InstallIn(
    ViewModelComponent::class,
    ServiceComponent::class,
    SingletonComponent::class,
)
@Module
abstract class NetworkModule {

    @Binds
    abstract fun bindRemoteRepository(repository: RemoteRepositoryImpl): RemoteRepository

    @Binds
    abstract fun bindRetrofitProvider(provider: RetrofitProviderImpl): RetrofitProvider
}
