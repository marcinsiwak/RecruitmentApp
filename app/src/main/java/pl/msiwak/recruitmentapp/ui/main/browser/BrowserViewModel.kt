package pl.msiwak.recruitmentapp.ui.main.browser

import dagger.hilt.android.lifecycle.HiltViewModel
import pl.msiwak.recruitmentapp.R
import pl.msiwak.recruitmentapp.domain.GetDataUseCase
import pl.msiwak.recruitmentapp.ui.base.BaseViewModel
import pl.msiwak.recruitmentapp.ui.main.list.ListEvents
import pl.msiwak.recruitmentapp.util.config.ResourceProvider
import javax.inject.Inject

@HiltViewModel
class BrowserViewModel @Inject constructor(resourceProvider: ResourceProvider) :
    BaseViewModel<BrowserEvents>() {

    init {
        toolbarTitle.value = resourceProvider.getString(R.string.label_browser)
        toolbarIcon.value = resourceProvider.getDrawable(R.drawable.ic_back)
    }

    fun onInit(){
        progressVisibility.value = true
    }

    fun onPageLoaded(){
        progressVisibility.value = false
    }

    override fun onBackClicked() {
        super.onBackClicked()
        sendEvent(BrowserEvents.NavigateBack)
    }
}