package pl.msiwak.recruitmentapp.ui.base

import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import pl.msiwak.recruitmentapp.R
import pl.msiwak.recruitmentapp.util.error.Failure
import pl.msiwak.recruitmentapp.util.event.Event

abstract class BaseViewModel<T: BaseEvent>: ViewModel() {

    protected val compositeDisposable = CompositeDisposable()

    val progressVisibility = MutableLiveData(false)
    val toolbarTitle = MutableLiveData("")
    val toolbarIcon = MutableLiveData<Drawable>()

    protected val mErrorEvent: MutableLiveData<Event<Failure>> = MutableLiveData()
    val errorEvent : LiveData<Event<Failure>> = mErrorEvent

    protected val mEvent: MutableLiveData<Event<T>> = MutableLiveData()
    val event: LiveData<Event<T>> = mEvent

    fun sendEvent(event: T){
        mEvent.value = Event(event)
    }

    fun sendError(event: Failure){
        mErrorEvent.value = Event(event)
    }

    open fun onBackClicked() = Unit

}