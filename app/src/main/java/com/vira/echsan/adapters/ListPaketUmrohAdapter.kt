package com.vira.echsan.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.vira.echsan.adapters.viewholder.ListPaketUmrohViewHolder
import com.vira.echsan.data.entities.PaketUmroh
import com.vira.echsan.databinding.ItemCardviewUmrohPaketBinding
import com.vira.echsan.ui.fragments.umroh.UmrohHasilFragmentDirections
import com.vira.echsan.viewmodel.PaketUmrohSharedViewModel

class ListPaketUmrohAdapter(
    private val sharedViewModel: PaketUmrohSharedViewModel
) : ListAdapter<PaketUmroh, ListPaketUmrohViewHolder>(ListPaketUmrohDiffCallback()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPaketUmrohViewHolder {
        return ListPaketUmrohViewHolder(
            ItemCardviewUmrohPaketBinding.inflate(
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

    override fun onBindViewHolder(holder: ListPaketUmrohViewHolder, position: Int) {
        val paket = getItem(position)
        holder.apply {
            bind(createOnClickListener(paket), paket)
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