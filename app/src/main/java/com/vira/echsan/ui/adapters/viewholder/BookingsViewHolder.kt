package com.vira.echsan.ui.adapters.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.vira.echsan.data.entities.Booking
import com.vira.echsan.databinding.ItemCardviewBookingsBinding

class BookingsViewHolder (
    private val binding : ItemCardviewBookingsBinding
) : RecyclerView.ViewHolder(binding.root){
    fun bind(item: Booking){
        binding.apply {
            booking = item
            executePendingBindings()
        }
    }
}

