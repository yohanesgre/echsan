package com.vira.echsan.adapters

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vira.echsan.R
import com.vira.echsan.databinding.ItemUmrohPaketFasilitasBinding
import com.vira.echsan.databinding.ItemUmrohPaketPilihTanggalBinding
import com.vira.echsan.viewmodel.PaketUmrohSharedViewModel


class PaketFasilitasAdapter : ListAdapter<String, PaketFasilitasAdapter.ViewHolder>(PaketFasilitasDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemUmrohPaketFasilitasBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding:ItemUmrohPaketFasilitasBinding
    ):RecyclerView.ViewHolder(binding.root){
        fun bind(_fasilitas:String) {
            binding.apply{
                fasilitas = _fasilitas
                executePendingBindings()
            }
        }
    }
}

private class PaketFasilitasDiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}