package pl.msiwak.recruitmentapp.ui.main.list

import android.view.LayoutInflater
import android.view.ViewGroup
import pl.msiwak.recruitmentapp.common.loadImage
import pl.msiwak.recruitmentapp.data.ListItem
import pl.msiwak.recruitmentapp.databinding.ItemListBinding
import pl.msiwak.recruitmentapp.ui.base.BaseAdapter
import pl.msiwak.recruitmentapp.ui.base.BaseHolder

class ListAdapter : BaseAdapter<ListItem>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<ListItem> {
        return ListHolder(
            ItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BaseHolder<ListItem>, position: Int) {
        holder.bind(items[position], onRecyclerListener)
    }

    class ListHolder(private val binding: ItemListBinding) :
        BaseHolder<ListItem>(binding.root) {

        override fun bind(item: ListItem, listener: ((Int) -> Unit)?) {
            binding.apply {
                itemListTv.text = item.title
                itemListDescriptionTv.text = item.description
                itemListDateTv.text = item.date
                itemListIv.loadImage(item.imageUrl)
                root.setOnClickListener { listener?.invoke(adapterPosition) }
            }
        }
    }

}