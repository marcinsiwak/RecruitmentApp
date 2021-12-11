package pl.msiwak.recruitmentapp.domain

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import pl.msiwak.recruitmentapp.data.ServerResponseItem
import pl.msiwak.recruitmentapp.util.api.list.ListRepo
import javax.inject.Inject

class GetDataUseCase @Inject constructor(private val listRepo: ListRepo) {

    fun getData(): Single<ArrayList<ServerResponseItem>> {
        return listRepo.getData().observeOn(AndroidSchedulers.mainThread())
    }

}