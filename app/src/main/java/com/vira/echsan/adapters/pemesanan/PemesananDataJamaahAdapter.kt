package com.vira.echsan.adapters.pemesanan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.vira.echsan.databinding.ItemPemesananJamaahBinding
import com.vira.echsan.features.umroh.view.UmrohPaketPemesananFragmentDirections

class PemesananDataJamaahAdapter : RecyclerView.Adapter<PemesananDataJamaahAdapter.ViewHolder>(){
    var itemList = emptyList<String>()
        get() = field
        set(value){
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPemesananJamaahBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
        holder.binding.setOnClick {
            val nav =
                UmrohPaketPemesananFragmentDirections.actionFragmentUmrohPaketPemesananToFragmentUmrohPaketInputJamaah(
                    position
                )
            holder.binding.root.findNavController().navigate(nav)
        }
    }

    class ViewHolder (val binding:ItemPemesananJamaahBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item:String){
            binding.apply {
                jamaah = item
            }
        }
    }
}