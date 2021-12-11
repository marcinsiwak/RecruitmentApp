package pl.msiwak.recruitmentapp.util.api

import retrofit2.Retrofit

class ApiServiceGeneratorImpl(private val retrofit: Retrofit) : ApiServiceGenerator {

    override fun <T> createService(serviceClass: Class<T>): T {
        return retrofit.create(serviceClass)
    }
}