package ru.eva.oriokslive.di.domain

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import ru.eva.oriokslive.domain.provider.DatabaseProvider
import ru.eva.oriokslive.domain.provider.DatabaseProviderImpl
import ru.eva.oriokslive.domain.provider.GsonProvider
import ru.eva.oriokslive.domain.provider.GsonProviderImpl
import ru.eva.oriokslive.domain.repository.DomainRepository
import ru.eva.oriokslive.domain.repository.DomainRepositoryImpl

@InstallIn(
    ViewModelComponent::class,
    ServiceComponent::class,
    SingletonComponent::class,
)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindDomainRepository(repository: DomainRepositoryImpl): DomainRepository

    @Binds
    abstract fun bindDatabaseProvider(provider: DatabaseProviderImpl): DatabaseProvider

    @Binds
    abstract fun bindGsonProvider(provider: GsonProviderImpl): GsonProvider
}
