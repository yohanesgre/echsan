package com.vira.echsan.adapters.member

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vira.echsan.databinding.ItemMemberBinding
import com.vira.echsan.features.umroh.data.PaketUmroh

class MemberAdapter : ListAdapter<List<String>, MemberAdapter.ViewHolder>(MemberDiffCallback()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMemberBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    private fun createOnClickListener(paket: PaketUmroh): View.OnClickListener {
        return View.OnClickListener {
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.apply {
            bind(item)
        }
    }
    inner class ViewHolder(
        private val binding: ItemMemberBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item:List<String>){
            binding.apply {
                binding.cost = item[0]
                binding.tipe = item[1]
                binding.voucher = item[2]
                binding.point = item[3]
                binding.doublePoint = item[4]
                binding.agentReward = item[5]
                binding.agentGathering = item[6]
                binding.agentRefferal = item[7]
                executePendingBindings()
            }
        }
    }
}

private class MemberDiffCallback : DiffUtil.ItemCallback<List<String>>() {
    override fun areContentsTheSame(oldItem: List<String>, newItem: List<String>): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: List<String>, newItem: List<String>): Boolean {
        return oldItem == newItem
    }
}