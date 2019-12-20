package com.vira.echsan.features.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vira.echsan.databinding.ItemCarouselBinding


class TopCarouselAdapter : RecyclerView.Adapter<TopCarouselAdapter.ViewHolder>() {

    private lateinit var listItem: List<Int>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCarouselBinding.inflate(
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

    inner class ViewHolder(private val binding: ItemCarouselBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Int) {
            binding.cvTextTest.visibility = View.GONE
            binding.ivCarousel.setImageResource(item)
        }
    }
}