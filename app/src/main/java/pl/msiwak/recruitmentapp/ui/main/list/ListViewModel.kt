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
import pl.msiwak.recruitmentapp.util.error.ListFailure
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getDataUseCase: GetDataUseCase,
    private val getDataFromLocalDbUseCase: GetDataFromLocalDbUseCase,
    private val resourceProvider: ResourceProvider
) :
    BaseViewModel<ListEvents>() {

    val listData = MutableLiveData<List<ListItem>>()

    init {
        toolbarTitle.value = resourceProvider.getString(R.string.label_list)
        toolbarIcon.value = resourceProvider.getDrawable(R.drawable.ic_cross)
    }

    fun onInit() {
        getDataFromLocalDb()
    }

    fun onItemClicked(pos: Int) {
        val url = listData.value?.get(pos)?.url ?: return
        val isLargeScreen = resourceProvider.getBoolean(R.bool.isTablet)

        if (isLargeScreen){
            sendEvent(ListEvents.OpenBrowserForLargeScreen(url))

        } else {
            sendEvent(ListEvents.OpenBrowser(url))
        }
    }

    override fun onBackClicked() {
        super.onBackClicked()
        sendEvent(ListEvents.CloseApp)
    }

    fun onRefreshClicked() {
        getData()
    }

    private fun getDataFromLocalDb(){
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
                    sendError(ListFailure.GetDataFromLocalDbFailure)
                }).addTo(compositeDisposable)
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
                    sendError(ListFailure.GetDataFailure)
                }).addTo(compositeDisposable)
    }

}