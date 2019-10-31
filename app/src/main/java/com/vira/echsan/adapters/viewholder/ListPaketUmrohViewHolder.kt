package com.vira.echsan.adapters.viewholder

import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.vira.echsan.data.entities.PaketUmroh
import com.vira.echsan.databinding.ItemCardviewUmrohPaketBinding
import com.vira.echsan.ui.fragments.umroh.UmrohHasilFragmentDirections

class ListPaketUmrohViewHolder(
    private val binding: ItemCardviewUmrohPaketBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(listener:View.OnClickListener, item:PaketUmroh){
        binding.apply {
            clickListener = listener
            paket = item
            executePendingBindings()
        }
    }
}
