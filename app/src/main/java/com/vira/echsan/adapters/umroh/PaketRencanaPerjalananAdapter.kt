package com.vira.echsan.adapters.umroh

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vira.echsan.databinding.ItemUmrohPaketPenerbanganBinding
import com.vira.echsan.databinding.ItemUmrohPaketRencanaPerjalananBinding

class PaketRencanaPerjalananAdapter : ListAdapter<List<String>, PaketRencanaPerjalananAdapter.ViewHolder>(
    PaketRencanaPerjalananDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemUmrohPaketRencanaPerjalananBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        //println("Item Size: " + item.size)
        holder.bind(item[0], item[1])
    }

    inner class ViewHolder(
        private val binding:ItemUmrohPaketRencanaPerjalananBinding
    ):RecyclerView.ViewHolder(binding.root){

        fun bind(hari:String, kegiatan:String) {
            binding.apply{
                binding.hari = hari
                binding.kegiatan = kegiatan
                executePendingBindings()
            }
        }
    }
}

private class PaketRencanaPerjalananDiffCallback : DiffUtil.ItemCallback<List<String>>() {
    override fun areItemsTheSame(oldItem: List<String>, newItem: List<String>): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: List<String>, newItem: List<String>): Boolean {
        return oldItem == newItem
    }
}