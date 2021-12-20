package pl.msiwak.recruitmentapp.util.api.list

import io.reactivex.rxjava3.core.Single
import pl.msiwak.recruitmentapp.data.ListItem
import pl.msiwak.recruitmentapp.data.ServerResponseItem

interface ListRepo {

    fun getData(): Single<List<ListItem>>
    fun getDataFromLocalDb(): Single<List<ListItem>>
}