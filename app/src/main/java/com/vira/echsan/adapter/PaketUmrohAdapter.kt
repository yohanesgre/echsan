package com.vira.echsan.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vira.echsan.R
import com.vira.echsan.model.recyclerview.PaketUmrohModel
import com.vira.echsan.viewholder.PaketUmrohViewHolder

class PaketUmrohAdapter(val paket:ArrayList<PaketUmrohModel>) : RecyclerView.Adapter<PaketUmrohViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaketUmrohViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.cardview_umroh_paket, parent, false)
        return PaketUmrohViewHolder(view)
    }

    override fun getItemCount() = paket.size

    override fun onBindViewHolder(holder: PaketUmrohViewHolder, position: Int) {
        holder.paket.text = paket[position].paket
        holder.tanggal.text = paket[position].tanggal
        holder.durasi.text = paket[position].durasi
        holder.hotel.text = paket[position].hotel
        holder.penerbangan.text = paket[position].penerbangan
        holder.travel.text = paket[position].travel
        holder.harga.text = paket[position].harga
    }

}