package com.vira.echsan.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.vira.echsan.data.entities.Booking
import com.vira.echsan.databinding.ItemCardviewBookingsBinding
import com.vira.echsan.ui.adapters.viewholder.BookingsViewHolder

class BookingAdapter: ListAdapter<Booking, BookingsViewHolder>(BookingDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingsViewHolder {
        return BookingsViewHolder(ItemCardviewBookingsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: BookingsViewHolder, position: Int) {
        val booking = getItem(position)
        holder.bind(booking)
    }
}
private class BookingDiffCallback : DiffUtil.ItemCallback<Booking>() {
    override fun areItemsTheSame(oldItem: Booking, newItem: Booking): Boolean {
        return oldItem.bookingId == newItem.bookingId
    }
    override fun areContentsTheSame(oldItem: Booking, newItem: Booking): Boolean {
        return oldItem == newItem
    }
}