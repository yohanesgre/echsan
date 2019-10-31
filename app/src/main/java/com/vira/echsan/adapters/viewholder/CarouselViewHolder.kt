package com.vira.echsan.adapters.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.vira.echsan.data.entities.Promo
import com.vira.echsan.databinding.ItemCarouselBinding

class CarouselViewHolder(
    private  val binding: ItemCarouselBinding
): RecyclerView.ViewHolder(binding.root){
    fun bind(item: Promo){
        binding.apply {
            promo = item
            executePendingBindings()
        }
    }
}
