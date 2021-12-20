package pl.msiwak.recruitmentapp.ui.main.list

import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.addTo
import pl.msiwak.recruitmentapp.R
import pl.msiwak.recruitmentapp.data.ListItem
import pl.msiwak.recruitmentapp.domain.GetDataFromLocalDbUseCase
import pl.msiwak.recruitmentapp.domain.GetDataUseCase
import pl.msiwak.recruitmentapp.ui.base.BaseViewModel
import pl.msiwak.recruitmentapp.util.config.ResourceProvider
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getDataUseCase: GetDataUseCase,
    private val getDataFromLocalDbUseCase: GetDataFromLocalDbUseCase,
    resourceProvider: ResourceProvider
) :
    BaseViewModel<ListEvents>() {

    val listData = MutableLiveData<List<ListItem>>()

    init {
        toolbarTitle.value = "List"
        toolbarIcon.value = resourceProvider.getDrawable(R.drawable.ic_cross)
    }

    fun onInit() {
        getDataFromLocalDbUseCase.execute()
            .doOnSubscribe {
                progressVisibility.value = true
            }
            .subscribe(
                {
                    listData.value = it
                    progressVisibility.value = false

                }, {
                    progressVisibility.value = false

                }).addTo(compositeDisposable)
    }

    fun onItemClicked(pos: Int) {
        val url = listData.value?.get(pos)?.url ?: return
        sendEvent(ListEvents.OpenBrowser(url))
    }

    override fun onBackClicked() {
        super.onBackClicked()
        sendEvent(ListEvents.CloseApp)
    }

    fun onRefreshClicked() {
        getData()
    }

    private fun getData(){
        getDataUseCase.execute()
            .doOnSubscribe {
                progressVisibility.value = true
            }
            .subscribe(
                {
                    listData.value = it
                    progressVisibility.value = false

                }, {
                    progressVisibility.value = false

                }).addTo(compositeDisposable)
    }

}