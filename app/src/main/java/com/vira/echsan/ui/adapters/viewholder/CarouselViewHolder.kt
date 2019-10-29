package com.vira.echsan.ui.adapters.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.vira.echsan.data.entities.Promo
import com.vira.echsan.databinding.ItemCardviewCarouselBinding

class CarouselViewHolder(
    private  val binding: ItemCardviewCarouselBinding
): RecyclerView.ViewHolder(binding.root){
    fun bind(item: Promo){
        binding.apply {
            promo = item
            executePendingBindings()
        }
    }
}
