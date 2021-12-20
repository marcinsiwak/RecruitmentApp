package pl.msiwak.recruitmentapp.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pl.msiwak.recruitmentapp.util.api.list.ListRepo
import pl.msiwak.recruitmentapp.util.api.list.ListRepoImpl
import pl.msiwak.recruitmentapp.util.api.list.ListService
import pl.msiwak.recruitmentapp.util.config.ResourceProvider
import pl.msiwak.recruitmentapp.util.config.ResourceProviderImpl
import pl.msiwak.recruitmentapp.util.db.AppDB
import pl.msiwak.recruitmentapp.util.db.DataDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideListRepo(listService: ListService, dataDao: DataDao): ListRepo {
        return ListRepoImpl(listService, dataDao)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDB.getInstance(appContext)

    @Provides
    fun provideDataDao(appDatabase: AppDB): DataDao {
        return appDatabase.dataDao()
    }

}