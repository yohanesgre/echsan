package com.vira.echsan.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.vira.echsan.data.entities.Promo
import com.vira.echsan.databinding.ItemCardviewCarouselBinding
import com.vira.echsan.adapters.viewholder.CarouselViewHolder

class CarouselAdapter : ListAdapter<Promo, CarouselViewHolder>(PromoDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        return CarouselViewHolder(
            ItemCardviewCarouselBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        val promo = getItem(position)
        holder.bind(promo)
    }
}


private class PromoDiffCallback : DiffUtil.ItemCallback<Promo>() {
    override fun areItemsTheSame(oldItem: Promo, newItem: Promo): Boolean {
        return oldItem.promoId == newItem.promoId
    }
    override fun areContentsTheSame(oldItem: Promo, newItem: Promo): Boolean {
        return oldItem == newItem
    }
}