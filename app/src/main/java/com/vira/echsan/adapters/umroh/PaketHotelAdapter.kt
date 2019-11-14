package com.vira.echsan.adapters.umroh

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vira.echsan.databinding.ItemUmrohPaketHotelBinding

class PaketHotelAdapter : ListAdapter<List<String>, PaketHotelAdapter.ViewHolder>(
    PaketHotelDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemUmrohPaketHotelBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        //println("Item Size: " + item.size)
        holder.bind(item[0], item[1])
    }

    inner class ViewHolder(
        private val binding:ItemUmrohPaketHotelBinding
    ):RecyclerView.ViewHolder(binding.root){

        fun bind(kota:String, hotel:String) {
            binding.apply{
                binding.kota = kota
                binding.hotel = hotel
                executePendingBindings()
            }
        }
    }
}

private class PaketHotelDiffCallback : DiffUtil.ItemCallback<List<String>>() {
    override fun areItemsTheSame(oldItem: List<String>, newItem: List<String>): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: List<String>, newItem: List<String>): Boolean {
        return oldItem == newItem
    }
}