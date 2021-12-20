package pl.msiwak.recruitmentapp.util.binding

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.Button
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import pl.msiwak.recruitmentapp.common.hide
import pl.msiwak.recruitmentapp.common.show
import pl.msiwak.recruitmentapp.ui.base.BaseAdapter
import pl.msiwak.recruitmentapp.ui.base.OnRecyclerListener

@BindingAdapter("data")
fun <T> setRecyclerData(recyclerView: RecyclerView, items: List<T>?) {
    if (recyclerView.adapter is BaseAdapter<*>) {
        items?.let {
            (recyclerView.adapter as BaseAdapter<T>).setData(it)
        }
    }
}

@BindingAdapter("onRecyclerClick")
fun setRecyclerListener(recyclerView: RecyclerView, onRecyclerListener: OnRecyclerListener?) {
    if (recyclerView.adapter is BaseAdapter<*>) {
        (recyclerView.adapter as BaseAdapter<*>).setListener(onRecyclerListener)
    }
}

@BindingAdapter("isVisible")
fun setIsVisible(view: View, isVisible: Boolean) {
    if (isVisible) {
        view.show()
    } else {
        view.hide()
    }
}

@BindingAdapter("iconDrawable")
fun setIcon(button: MaterialButton, drawable: Drawable?) {
    button.icon = drawable
}