package com.vira.echsan.adapters.umroh

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vira.echsan.databinding.ItemUmrohPaketPenerbanganBinding

class PaketPenerbanganAdapter : ListAdapter<List<String>, PaketPenerbanganAdapter.ViewHolder>(
    PaketPenerbanganDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemUmrohPaketPenerbanganBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        //println("Item Size: " + item.size)
        holder.bind(item[0], item[1], item[2], item[3])
    }

    inner class ViewHolder(
        private val binding:ItemUmrohPaketPenerbanganBinding
    ):RecyclerView.ViewHolder(binding.root){

        fun bind(_status:String, _asal:String, _tujuan:String, _maskapai:String) {
            binding.apply{
                binding.tvPenerbanganStatus.text = _status
                binding.tvPenerbanganAsal.text = _asal
                binding.tvPenerbanganTujuan.text = _tujuan
                binding.tvPenerbanganMaskapai.text = _maskapai
                executePendingBindings()
            }
        }
    }
}

private class PaketPenerbanganDiffCallback : DiffUtil.ItemCallback<List<String>>() {
    override fun areItemsTheSame(oldItem: List<String>, newItem: List<String>): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: List<String>, newItem: List<String>): Boolean {
        return oldItem == newItem
    }
}