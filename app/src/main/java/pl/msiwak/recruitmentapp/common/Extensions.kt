package pl.msiwak.recruitmentapp.common

import android.view.View
import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import pl.msiwak.recruitmentapp.data.ListItem
import pl.msiwak.recruitmentapp.data.ServerResponseItem
import pl.msiwak.recruitmentapp.util.config.HTTP_STRING
import pl.msiwak.recruitmentapp.util.error.Failure
import pl.msiwak.recruitmentapp.util.event.Event
import pl.msiwak.recruitmentapp.util.helpers.DateHelper


fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

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
    return ListItem(
        orderId,
        title,
        description.substringBefore(HTTP_STRING),
        DateHelper.parseDate(modificationDate),
        "$HTTP_STRING${description.substringAfter(HTTP_STRING)}",
        image_url
    )
}

fun ImageView.loadImage(imageUrl: String) {
    Glide.with(this.context)
        .load(imageUrl)
        .transition(DrawableTransitionOptions.withCrossFade())
        .error(android.R.drawable.stat_notify_error)
        .into(this)
}