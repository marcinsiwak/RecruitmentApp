package pl.msiwak.recruitmentapp.ui.main.list

import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.addTo
import pl.msiwak.recruitmentapp.domain.GetDataUseCase
import pl.msiwak.recruitmentapp.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val getDataUseCase: GetDataUseCase) :
    BaseViewModel<ListEvents>() {

    fun onInit() {
        getDataUseCase.getData().subscribe(
            {
                sendEvent(ListEvents.InitAdapter(it))
            }, {
                val out = it

            }).addTo(compositeDisposable)
    }


}