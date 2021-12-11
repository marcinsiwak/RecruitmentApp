package pl.msiwak.recruitmentapp.common

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import pl.msiwak.recruitmentapp.data.ListItem
import pl.msiwak.recruitmentapp.data.ServerResponseItem
import pl.msiwak.recruitmentapp.util.error.Failure
import pl.msiwak.recruitmentapp.util.event.Event


inline fun <T : Any, L : LiveData<Event<T>>> LifecycleOwner.observeEvent(
    liveData: L,
    crossinline body: (T?) -> Unit
) {
    liveData.observe(this, { body(it.peekContent()) })
}

inline fun <T : Failure, L : LiveData<Event<T>>> LifecycleOwner.observeFailure(
    liveData: L,
    crossinline body: (T?) -> Unit
) {
    liveData.observe(this, { body(it.peekContent()) })
}

fun ServerResponseItem.toListItem(): ListItem {
    return ListItem(title, description, modificationDate, image_url)
}