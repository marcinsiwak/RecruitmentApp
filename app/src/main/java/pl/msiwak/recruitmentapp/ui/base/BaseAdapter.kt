package pl.msiwak.recruitmentapp.ui.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T> : RecyclerView.Adapter<BaseHolder<T>>() {

    protected open var items: List<T> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    protected var onRecyclerListener: OnRecyclerListener? = null

    fun setData(items: List<T>) {
        this.items = items
    }

    fun setListener(listener: OnRecyclerListener?) {
        this.onRecyclerListener = listener
    }

    override fun getItemCount(): Int = items.size
}