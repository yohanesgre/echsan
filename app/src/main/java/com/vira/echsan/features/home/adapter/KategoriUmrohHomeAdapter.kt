package com.vira.echsan.features.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vira.echsan.databinding.ItemRvKategoriUmrohHomeBinding


class KategoriUmrohHomeAdapter : RecyclerView.Adapter<KategoriUmrohHomeAdapter.ViewHolder>() {

    private lateinit var listItem: List<String>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRvKategoriUmrohHomeBinding.inflate(
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

    fun setItemList(newItemList: List<String>) {
        listItem = newItemList
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemRvKategoriUmrohHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.tvDescription.text = item
        }
    }
}