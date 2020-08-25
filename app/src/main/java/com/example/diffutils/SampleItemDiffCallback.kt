package com.example.diffutils

import androidx.recyclerview.widget.DiffUtil

class SampleItemDiffCallback(private val old: List<SampleItem>, private val new: List<SampleItem>): DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition] == new[newItemPosition]
    }

    override fun getOldListSize(): Int {
        return old.size
    }

    override fun getNewListSize(): Int {
        return new.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition].sampleId == new[newItemPosition].sampleId
    }
}