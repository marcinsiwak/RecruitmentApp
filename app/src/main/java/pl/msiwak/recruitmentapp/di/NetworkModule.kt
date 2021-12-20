package pl.msiwak.recruitmentapp.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pl.msiwak.recruitmentapp.BuildConfig
import pl.msiwak.recruitmentapp.util.api.ApiServiceGenerator
import pl.msiwak.recruitmentapp.util.api.ApiServiceGeneratorImpl
import pl.msiwak.recruitmentapp.util.api.list.ListService
import pl.msiwak.recruitmentapp.util.config.REQUEST_TIMEOUT
import pl.msiwak.recruitmentapp.util.db.AppDB
import pl.msiwak.recruitmentapp.util.db.DataDao
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideGson(): Gson =
        GsonBuilder().create()


    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.ENDPOINT)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    @Provides
    fun provideApiServiceGenerator(retrofit: Retrofit): ApiServiceGenerator {
        return ApiServiceGeneratorImpl(retrofit)
    }

    @Provides
    fun provideListApi(apiServiceGenerator: ApiServiceGenerator): ListService {
        return apiServiceGenerator.createService(ListService::class.java)
    }

}