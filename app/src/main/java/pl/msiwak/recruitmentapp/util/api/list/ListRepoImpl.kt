package pl.msiwak.recruitmentapp.util.api.list

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import pl.msiwak.recruitmentapp.common.toListItem
import pl.msiwak.recruitmentapp.data.ListItem
import pl.msiwak.recruitmentapp.util.db.DataDao
import javax.inject.Inject

class ListRepoImpl @Inject constructor(
    private val listService: ListService,
    private val dataDao: DataDao
) : ListRepo {

    override fun getData(): Single<List<ListItem>> {
        return getDataFromServer()
    }

    override fun getDataFromLocalDb(): Single<List<ListItem>> {
        return dataDao.getData()
            .subscribeOn(Schedulers.io())
            .flatMap {
                if (it.isEmpty()) {
                    getDataFromServer()
                } else {
                    Single.just(it)
                }
            }
    }

    private fun getDataFromServer(): Single<List<ListItem>> {
        return listService.getData()
            .subscribeOn(Schedulers.io())
            .map {
                val list = it.sortedBy { serverResponseItem -> serverResponseItem.orderId }
                    .map { serverResponseItem -> serverResponseItem.toListItem() }
                dataDao.insertAll(list)
                list
            }
    }
}