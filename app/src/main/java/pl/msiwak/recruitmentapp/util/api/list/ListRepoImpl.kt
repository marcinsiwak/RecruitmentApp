package pl.msiwak.recruitmentapp.util.api.list

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import pl.msiwak.recruitmentapp.common.toListItem
import pl.msiwak.recruitmentapp.data.ListItem
import pl.msiwak.recruitmentapp.data.ServerResponseItem
import javax.inject.Inject

class ListRepoImpl @Inject constructor(private val listService: ListService) : ListRepo {

    override fun getData(): Single<List<ListItem>> {
        return listService.getData().subscribeOn(Schedulers.io())
            .map {
                it.sortedBy { serverResponseItem -> serverResponseItem.orderId }
                    .map { serverResponseItem -> serverResponseItem.toListItem() }
            }
    }
}