package pl.msiwak.recruitmentapp.ui.main.list

import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.addTo
import pl.msiwak.recruitmentapp.data.ListItem
import pl.msiwak.recruitmentapp.domain.GetDataUseCase
import pl.msiwak.recruitmentapp.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val getDataUseCase: GetDataUseCase) :
    BaseViewModel<ListEvents>() {

    val listData = MutableLiveData<List<ListItem>>(emptyList())

    fun onInit() {
        getDataUseCase.getData()
            .doOnSubscribe {
                //todo add loader
            }
            .subscribe(
                {
                    listData.value = it
                }, {

                }).addTo(compositeDisposable)
    }

    fun onItemClicked(pos: Int) {
        val url = listData.value?.get(pos)?.url ?: return
        sendEvent(ListEvents.OpenBrowser(url))
    }


}