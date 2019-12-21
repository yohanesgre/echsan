package com.vira.echsan.features.bookings

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vira.echsan.api.resp.TransactionResp
import com.vira.echsan.databinding.ItemBookingsBinding
import com.vira.echsan.utils.CalendarHelper

class BookingAdapter : PagedListAdapter<TransactionResp, BookingAdapter.BookingsViewHolder>(
    BookingDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingsViewHolder {
        return BookingsViewHolder(
            ItemBookingsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: BookingsViewHolder, position: Int) {
        val booking = getItem(position)
        booking?.let {
            holder.bind(it)
        }
    }
    inner class BookingsViewHolder (
        private val binding : ItemBookingsBinding
    ) : RecyclerView.ViewHolder(binding.root){
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(item: TransactionResp) {
            binding.apply {
                outputProduct.text = item.product.name
                outputTujuan.text = "${item.peopleAmount} jamaah"
                outputHotel.text = "${item.product.makkah_hotel} | ${item.product.madinah_hotel}"
                outputTanggalTransaksi.text =
                    CalendarHelper.convertDateStringToLocalFormat(item.product.date_of_departure)
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