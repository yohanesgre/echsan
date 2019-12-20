package com.vira.echsan.features.bookings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vira.echsan.api.resp.TransactionResp
import com.vira.echsan.databinding.ItemBookingsBinding

class BookingAdapter : PagedListAdapter<TransactionResp, BookingAdapter.BookingsViewHolder>(
    BookingDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingsViewHolder {
        return BookingsViewHolder(
            ItemBookingsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: BookingsViewHolder, position: Int) {
        val booking = getItem(position)
        booking?.let {
            holder.bind(it)
        }
    }
    inner class BookingsViewHolder (
        private val binding : ItemBookingsBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: TransactionResp) {
            binding.apply {
                bookingContentBodyDuration.text = item.product.day_amount.toString()
                bookingContentHeaderTitle.text = item.product.name
                bookingContentHeaderDate.text = item.updatedAt
                executePendingBindings()
            }
        }
    }


}

private class BookingDiffCallback : DiffUtil.ItemCallback<TransactionResp>() {
    override fun areItemsTheSame(oldItem: TransactionResp, newItem: TransactionResp): Boolean {
        return oldItem.transactionId == newItem.transactionId
    }

    override fun areContentsTheSame(oldItem: TransactionResp, newItem: TransactionResp): Boolean {
        return oldItem == newItem
    }
}