package com.example.diffutils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.diffutils.databinding.ListItemSampleBinding

class ListAdapter(private val inflater: LayoutInflater): RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    var items: List<SampleItem> = listOf(SampleItem(0))
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemSampleBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sampleItem = items[position]
        holder.binding.itemId.text = sampleItem.sampleId.toString()
        holder.binding.btnDelete.setOnClickListener {
            val new: MutableList<SampleItem> = items.toMutableList()
            for (item in new) {
                if (item.sampleId == sampleItem.sampleId) {
                    new.remove(item)
                    break
                }
            }
            update(new)
        }
    }

    fun update(new: List<SampleItem>) {
        // TODO: Coroutineを使って別スレッドで処理するべき
        val diffResult = DiffUtil.calculateDiff(SampleItemDiffCallback(items, new))
        items = new
        diffResult.dispatchUpdatesTo(this)
    }

    class ViewHolder(val binding: ListItemSampleBinding) : RecyclerView.ViewHolder(binding.root)
}