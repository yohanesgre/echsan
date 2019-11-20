package com.vira.echsan.adapters.pembayaran

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vira.echsan.databinding.ItemUmrohPembayaranMetodePembayaranBinding
import com.vira.echsan.view.fragments.umroh.UmrohPaketPembayaranFragmentDirections

class MetodePembayaranAdapter(
    private val itemList:List<MetodePembayaranItem>
) : RecyclerView.Adapter<MetodePembayaranAdapter.ViewHolder>() {

    private val viewPool by lazy {  RecyclerView.RecycledViewPool() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemUmrohPembayaranMetodePembayaranBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position], viewPool)
    }

    class ViewHolder(
        val binding:ItemUmrohPembayaranMetodePembayaranBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item:MetodePembayaranItem, viewPool: RecyclerView.RecycledViewPool){
            binding.apply{
                tvMetodePembayaran.text = item.title
                rvImgMetodePembayaran.apply {
                    layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
                    adapter = ImgMetodePembayaranAdapter(item.children)
                    setRecycledViewPool(viewPool)
                }
                setOnClick {
                    val nav =
                    UmrohPaketPembayaranFragmentDirections.actionFragmentUmrohPaketPembayaranToFragmentUmrohPaketPembayaran2()
                    this.root.findNavController().navigate(nav)
                }
            }
        }
    }
}