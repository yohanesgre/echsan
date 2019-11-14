package com.vira.echsan.adapters.umroh

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vira.echsan.data.entities.PaketUmroh
import com.vira.echsan.databinding.ItemUmrohPaketBinding
import com.vira.echsan.ui.fragments.umroh.UmrohHasilFragmentDirections
import com.vira.echsan.viewmodel.PaketUmrohSharedViewModel

class ListPaketUmrohAdapter(
    private val sharedViewModel: PaketUmrohSharedViewModel
) : ListAdapter<PaketUmroh, ListPaketUmrohAdapter.ViewHolder>(ListPaketUmrohDiffCallback()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemUmrohPaketBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    private fun createOnClickListener(paket:PaketUmroh): View.OnClickListener {
        return View.OnClickListener {
            sharedViewModel.setSelectedPaket(paket)
            val direction = UmrohHasilFragmentDirections.actionFragmentUmrohHasilToFragmentUmrohPaket()
            it.findNavController().navigate(direction)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val paket = getItem(position)
        holder.apply {
            bind(createOnClickListener(paket), paket)
        }
    }
    inner class ViewHolder(
        private val binding: ItemUmrohPaketBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(listener:View.OnClickListener, item:PaketUmroh){
            binding.apply {
                clickListener = listener
                paket = item
                executePendingBindings()
            }
        }
    }
}

private class ListPaketUmrohDiffCallback : DiffUtil.ItemCallback<PaketUmroh>() {
    override fun areItemsTheSame(oldItem: PaketUmroh, newItem: PaketUmroh): Boolean {
        return oldItem.paketId == newItem.paketId
    }
    override fun areContentsTheSame(oldItem: PaketUmroh, newItem: PaketUmroh): Boolean {
        return oldItem == newItem
    }
}