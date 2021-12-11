package pl.msiwak.recruitmentapp.util.api.list

import io.reactivex.rxjava3.core.Single
import pl.msiwak.recruitmentapp.data.ServerResponseItem
import retrofit2.http.GET

interface ListService {

    @GET("/recruitment-taski")
    fun getData(): Single<ArrayList<ServerResponseItem>>

}