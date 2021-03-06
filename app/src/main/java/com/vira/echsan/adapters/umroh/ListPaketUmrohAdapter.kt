package com.vira.echsan.adapters.umroh

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vira.echsan.databinding.ItemUmrohPaketBinding
import com.vira.echsan.features.umroh.data.PaketUmroh
import com.vira.echsan.features.umroh.view.UmrohHasilFragmentDirections
import com.vira.echsan.utils.CalendarHelper

class ListPaketUmrohAdapter :
    PagedListAdapter<PaketUmroh, ListPaketUmrohAdapter.ViewHolder>(ListPaketUmrohDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemUmrohPaketBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    private fun createOnClickListener(id: Int): View.OnClickListener {
        return View.OnClickListener {
            val direction =
                UmrohHasilFragmentDirections.actionFragmentUmrohHasilToFragmentUmrohPaket(id)
            it.findNavController().navigate(direction)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val paket = getItem(position)
        println("Adapter Positon: $position")
        paket?.let {
            holder.apply {
                bind(createOnClickListener(paket.id), paket)
            }
        }
    }
    inner class ViewHolder(
        private val binding: ItemUmrohPaketBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        @SuppressLint("SetTextI18n")
        fun bind(listener: View.OnClickListener, item: PaketUmroh) {
            binding.apply {
                clickListener = listener
                tvUmrohPaketCvPaket.text = item.name
                tvUmrohPaketCvBerangkat.text =
                    CalendarHelper.convertDateStringToLocalFormat(item.date_of_departure)
                tvUmrohPaketCvDurasi.text = item.day_amount.toString()
                tvUmrohPaketCvHotel.text = item.madinah_hotel + " | " + item.makkah_hotel
                tvUmrohPaketCvPenerbangan.text = item.departure_plane.dep_plane_name
                tvUmrohPaketCvHarga2.text = item.price
                executePendingBindings()
            }
        }
    }
}

private class ListPaketUmrohDiffCallback : DiffUtil.ItemCallback<PaketUmroh>() {
    override fun areItemsTheSame(oldItem: PaketUmroh, newItem: PaketUmroh): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: PaketUmroh, newItem: PaketUmroh): Boolean {
        return oldItem == newItem
    }
}