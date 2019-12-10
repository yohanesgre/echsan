package com.vira.echsan.adapters.umroh

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vira.echsan.databinding.ItemUmrohPaketFasilitasNewBinding


class PaketFasilitasNewAdapter : ListAdapter<String, PaketFasilitasNewAdapter.ViewHolder>(
    PaketFasilitasNewDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemUmrohPaketFasilitasNewBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemUmrohPaketFasilitasNewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(_fasilitas: String) {
            binding.apply {
                fasilitas = _fasilitas
                executePendingBindings()
            }
        }
    }
}

private class PaketFasilitasNewDiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}