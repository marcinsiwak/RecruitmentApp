package pl.msiwak.recruitmentapp.domain

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import pl.msiwak.recruitmentapp.data.ListItem
import pl.msiwak.recruitmentapp.util.api.list.ListRepo
import javax.inject.Inject

class GetDataFromLocalDbUseCase @Inject constructor(private val listRepo: ListRepo) {

    fun execute(): Single<List<ListItem>> {
        return listRepo.getDataFromLocalDb()
            .observeOn(AndroidSchedulers.mainThread())
    }
}