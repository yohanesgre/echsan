package com.vira.echsan.adapters

import alirezat775.lib.carouselview.CarouselAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import com.vira.echsan.data.entities.Promo
import com.vira.echsan.databinding.ItemCarouselBinding

class PromoAdapter : CarouselAdapter() {

    var listItem = emptyList<Promo>()

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        when(holder){
            is ViewHolder ->{
                val item = listItem[position]
                holder.bind(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCarouselBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(
        val binding: ItemCarouselBinding
    ):CarouselViewHolder(binding.root){
        fun bind(item:Promo){
            binding.apply {
                binding.promo = item
            }
        }
    }

    fun setList(newList: List<Promo>){
        listItem = newList
        notifyDataSetChanged()
    }
    /*private var list: List<Promo> = listOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(ItemCarouselBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    fun setList(newList:List<Promo>){
        list = newList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(
        holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }
    class ViewHolder(
        private val binding:ItemCarouselBinding
    ):RecyclerView.ViewHolder(binding.root){
        fun bind(item:Promo){
            binding.apply {
                promo = item
                notifyChange()
            }
        }
    }*/
}