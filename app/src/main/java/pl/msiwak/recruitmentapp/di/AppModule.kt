package pl.msiwak.recruitmentapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.msiwak.recruitmentapp.util.api.list.ListRepo
import pl.msiwak.recruitmentapp.util.api.list.ListRepoImpl
import pl.msiwak.recruitmentapp.util.api.list.ListService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideListRepo(listService: ListService): ListRepo {
        return ListRepoImpl(listService)
    }


}