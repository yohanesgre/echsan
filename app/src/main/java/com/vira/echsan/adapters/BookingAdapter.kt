package com.vira.echsan.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vira.echsan.data.entities.Booking
import com.vira.echsan.databinding.ItemBookingsBinding

class BookingAdapter: ListAdapter<Booking, BookingAdapter.BookingsViewHolder>(BookingDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingsViewHolder {
        return BookingsViewHolder(
            ItemBookingsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: BookingsViewHolder, position: Int) {
        val booking = getItem(position)
        holder.bind(booking)
    }
    inner class BookingsViewHolder (
        private val binding : ItemBookingsBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: Booking){
            binding.apply {
                booking = item
                executePendingBindings()
            }
        }
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