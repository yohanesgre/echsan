package com.vira.echsan.adapters.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.vira.echsan.data.entities.Booking
import com.vira.echsan.databinding.ItemBookingsBinding


class BookingsViewHolder (
    private val binding : ItemBookingsBinding
) : RecyclerView.ViewHolder(binding.root){
    fun bind(item: Booking){
        binding.apply {
            booking = item
            executePendingBindings()
        }
    }
}

