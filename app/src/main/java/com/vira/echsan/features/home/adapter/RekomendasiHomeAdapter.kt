package com.vira.echsan.features.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vira.echsan.databinding.ItemRvRekomendasiHomeBinding


class RekomendasiHomeAdapter : RecyclerView.Adapter<RekomendasiHomeAdapter.ViewHolder>() {

    private lateinit var listItem: List<Int>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRvRekomendasiHomeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = listItem.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listItem[position])
    }

    fun setItemList(newItemList: List<Int>) {
        listItem = newItemList
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemRvRekomendasiHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Int) {

        }
    }
}