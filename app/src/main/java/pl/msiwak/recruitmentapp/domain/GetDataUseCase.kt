package pl.msiwak.recruitmentapp.domain

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import pl.msiwak.recruitmentapp.common.toListItem
import pl.msiwak.recruitmentapp.data.ListItem
import pl.msiwak.recruitmentapp.util.api.list.ListRepo
import javax.inject.Inject

class GetDataUseCase @Inject constructor(private val listRepo: ListRepo) {

    fun execute(): Single<List<ListItem>> {
        return listRepo.getData()
            .observeOn(AndroidSchedulers.mainThread())
    }

}