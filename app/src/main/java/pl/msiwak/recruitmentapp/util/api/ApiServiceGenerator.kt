package pl.msiwak.recruitmentapp.util.api

interface ApiServiceGenerator {
    fun <T> createService(serviceClass: Class<T>): T
}