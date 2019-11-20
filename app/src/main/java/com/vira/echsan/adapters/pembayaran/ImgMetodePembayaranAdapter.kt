package com.vira.echsan.adapters.pembayaran

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vira.echsan.databinding.ItemImgMetodePembayaranBinding

class ImgMetodePembayaranAdapter (
    val itemList:List<ImageMetodePembayaranItem>
) : RecyclerView.Adapter<ImgMetodePembayaranAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemImgMetodePembayaranBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    class ViewHolder (
        val binding: ItemImgMetodePembayaranBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item:ImageMetodePembayaranItem){
            binding.ivMetodePembayaran.setImageResource(item.image)
        }
    }
}