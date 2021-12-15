package pl.msiwak.recruitmentapp.util.api.list

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import pl.msiwak.recruitmentapp.data.ServerResponseItem
import javax.inject.Inject

class ListRepoImpl @Inject constructor(private val listService: ListService) : ListRepo {

    override fun getData(): Single<List<ServerResponseItem>> {
        return listService.getData().subscribeOn(Schedulers.io())
    }
}