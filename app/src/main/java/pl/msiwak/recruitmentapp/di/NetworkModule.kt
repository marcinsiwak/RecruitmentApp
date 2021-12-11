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
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideGson(): Gson =
        GsonBuilder()
//            .registerTypeAdapter()
            .create()

    //todo

    @Provides
    @Singleton
    fun provideRetrofit(
//        configuration: Configuration,
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