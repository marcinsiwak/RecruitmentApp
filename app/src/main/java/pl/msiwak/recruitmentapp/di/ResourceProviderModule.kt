package pl.msiwak.recruitmentapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.msiwak.recruitmentapp.util.config.ResourceProvider
import pl.msiwak.recruitmentapp.util.config.ResourceProviderImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ResourceProviderModule {

    @Binds
    @Singleton
    abstract fun provideResourceProvider(resourceProviderImpl: ResourceProviderImpl): ResourceProvider
}